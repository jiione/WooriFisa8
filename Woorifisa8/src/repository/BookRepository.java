package repository;

import java.util.ArrayList;

import model.domain.Book;

public class BookRepository {
	private static BookRepository instance = new BookRepository();
	private ArrayList<Book> bookList = new ArrayList<>();

	private BookRepository() {

		Book book1 = Book.builder().isbn("1234").title("도둑맞은 집중력").author("요한 하리").status(false).build();
		Book book2 = Book.builder().isbn("2345").title("수학도둑 100").author("송도수").status(false).build();
		Book book3 = Book.builder().isbn("3456").title("빵도둑").author("시바타 케이코").status(false).build();
		Book book4 = Book.builder().isbn("4567").title("밥도둑 약선요리왕").author("가프").status(false).build();

		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);
	}

	public static BookRepository getInstance() {
		return instance;
	}

	public boolean deleteBookByIsbn(String Isbn) {
		for (Book book : bookList) {
			if (Isbn.equals(book.getIsbn()) == true) {
				bookList.remove(book);
				return true;
			}
		}
		return false;
	}

	public Book insertBook(Book book) {
		bookList.add(book);
		return book;
	}

	public ArrayList<Book> findAll() {
		return bookList;
	}

	public Book findBookByIsbn(String Isbn) {
		for (Book book : bookList) {
			if (Isbn.equals(book.getIsbn()) == true) {
				return book;
			}
		}
		return null;
	}
}