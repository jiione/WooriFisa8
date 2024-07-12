package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.domain.Book;
import model.domain.User;

public class UserRepository {
	private Map<String, User> userList;
	private static UserRepository instance = new UserRepository();

	private UserRepository() {
		User user1 = User.builder().role("ADMIN").name("박정주").id("10").pw("10").bookList(new ArrayList<>()).build();
		User user2 = User.builder().role("USER").name("박지원").id("11").pw("11").bookList(new ArrayList<>()).build();
		User user3 = User.builder().role("USER").name("박현서").id("12").pw("12").bookList(new ArrayList<>()).build();
		User user4 = User.builder().role("USER").name("박장우").id("9").pw("9").bookList(new ArrayList<>()).build();

		userList = new HashMap<>();
		userList.put(user1.getId(), user1);
		userList.put(user2.getId(), user2);
		userList.put(user3.getId(), user3);
		userList.put(user4.getId(), user4);

	}

	public static UserRepository getInstance() {
		return instance;
	}

	public Map<String, User> getAllUsers() {
		return userList;
	}

	public User addUser(User user) {
		
		String userId = user.getId();
		userList.put(userId, user);
		return user;
		
	}

	public boolean deleteUser(String userId) {
		return userList.remove(userId) != null;
	}

	public User getUserById(String userId) {
		return userList.get(userId); // nullable
	}

	public Book borrowBook(Book book, User user) {
		User foundUser = getUserById(user.getId());
		ArrayList<Book> userBookList = foundUser.getBookList();
		userBookList.add(book);
		return book;
	}

	public Book returnBook(Book book, User user) {
		User foundUser = getUserById(user.getId());
		ArrayList<Book> userBookList = foundUser.getBookList();

		for (int i = 0; i < userBookList.size(); i++) {
			if (userBookList.get(i).getIsbn().equals(book.getIsbn())) {
				return userBookList.remove(i);
			}
		}
		return null;
	}
}
