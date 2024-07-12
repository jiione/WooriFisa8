package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BookController;
import controller.UserController;
import model.domain.Book;
import model.domain.User;
import repository.UserRepository;
import service.UserService;

public class StartView {
	
    private static Scanner scanner = new Scanner(System.in);
  
    private static UserService userService = new UserService();
    private static BookController BookController = new BookController();
    private static UserController userController = new UserController();

	public static void main(String[] args) {


		
		 boolean loggedIn = false;
	        User currentUser = null;

	        while (!loggedIn) {
	            System.out.println("로그인");
	            System.out.print("아이디: ");
	            String userId = scanner.nextLine();
	            System.out.print("비밀번호: ");
	            String password = scanner.nextLine();

	            if (userService.validateUser(userId, password)) {
	                currentUser = userService.getUser(userId);
	                System.out.println("로그인 성공!");
	                loggedIn = true;
	                showMenu(currentUser);
	            } else {
	                System.out.println("로그인 실패. 아이디와 비밀번호를 다시 확인해주세요.");
	            }
	        }

	}

	 private static void showMenu(User user) {
	        while (true) {
	            System.out.println("\n메뉴를 선택하세요:");
	            System.out.println("1. 전체 도서 조회");
	            System.out.println("2. 도서 대출");
	            System.out.println("3. 도서 반납");
	            if ("ADMIN".equals(user.getRole())) {
	                System.out.println("4. 유저 추가");
	                System.out.println("5. 유저 삭제");
	                System.out.println("6. 도서 추가");
	                System.out.println("7. 도서 삭제");
	            }
	            System.out.println("0. 로그아웃");
	            System.out.print("선택: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // 버퍼 비우기

	            switch (choice) {
	                case 1:
	                    BookController.viewAllBooks();
	                    break;
	                case 2:
	                    borrowBook(user);
	                    break;
	                case 3:
	                    returnBook(user);
	                    break;
	                case 4:
	                    if ("ADMIN".equals(user.getRole())) {
	                        addUser(user);
	                    } else {
	                        System.out.println("권한이 없습니다.");
	                    }
	                    break;
	                case 5:
	                    if ("ADMIN".equals(user.getRole())) {
	                        removeUser(user);
	                    } else {
	                        System.out.println("권한이 없습니다.");
	                    }
	                    break;
	                case 6:
	                    if ("ADMIN".equals(user.getRole())) {
	                        addBook(user);
	                    } else {
	                        System.out.println("권한이 없습니다.");
	                    }
	                    break;
	                case 7:
	                    if ("ADMIN".equals(user.getRole())) {
	                        removeBook(user);
	                    } else {
	                        System.out.println("권한이 없습니다.");
	                    }
	                    break;
	                case 0:
	                    System.out.println("로그아웃 되었습니다.");
	                    return;
	                default:
	                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
	            }
	        }
	    }



    private static void borrowBook(User user) {
        System.out.print("대출할 도서 ISBN을 입력하세요: ");
        String isbn = scanner.nextLine();
        BookController.borrowBook(isbn, user);
    }

    private static void returnBook(User user) {
        System.out.print("반납할 도서 ISBN을 입력하세요: ");
        String isbn = scanner.nextLine();
        BookController.returnBook(isbn, user);
    }

    private static void addUser(User admin) {
        System.out.print("추가할 유저 이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.print("추가할 유저 ID를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.print("추가할 유저 비밀번호를 입력하세요: ");
        String pw = scanner.nextLine();
        System.out.print("추가할 유저 역할을 입력하세요 (USER/ADMIN): ");
        String role = scanner.nextLine();

        User newUser = new User(role, name, id, pw, new ArrayList<>());
        userController.addUser(admin.getId(), admin.getPw(), newUser);
    }

    private static void removeUser(User admin) {
        System.out.print("삭제할 유저 ID를 입력하세요: ");
        String userId = scanner.nextLine();
        User user = userService.getUser(userId);
        if (user != null) {
            userController.removeUser(admin.getId(), admin.getPw(), user);
        } else {
            System.out.println("해당 ID의 유저가 존재하지 않습니다.");
        }
    }

    private static void addBook(User admin) {
        System.out.print("추가할 도서 ISBN을 입력하세요: ");
        String isbn = scanner.nextLine();
        System.out.print("추가할 도서 제목을 입력하세요: ");
        String title = scanner.nextLine();
        System.out.print("추가할 도서 저자를 입력하세요: ");
        String author = scanner.nextLine();

        Book newBook = new Book(isbn, title, author, false);
        BookController.addBook(newBook, admin);
    }

    private static void removeBook(User admin) {
        System.out.print("삭제할 도서 ISBN을 입력하세요: ");
        String isbn = scanner.nextLine();
        BookController.removeBook(isbn, admin);
    }
}

