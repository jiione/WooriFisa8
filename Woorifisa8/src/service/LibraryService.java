package service;

import java.util.ArrayList;

import model.domain.Book;
import model.domain.User;
import repository.BookRepository;
import repository.UserRepository;

public class LibraryService {
	private final BookRepository bookRepository = BookRepository.getInstance();
	private final UserRepository userRepository = UserRepository.getInstance();

	public Book borrowBook(String Isbn, User user) {

		Book book = bookRepository.findBookByIsbn(Isbn);
		if (book != null && book.isStatus() == false) {

			book.setStatus(true);
			userRepository.borrowBook(book, user);
			return book;

		}
		return null;
	}

	public Book returnBook(String Isbn, User user) {
		
		Book book = bookRepository.findBookByIsbn(Isbn);
		if(book.isStatus()==true) {
			book.setStatus(false);
			userRepository.returnBook(book, user);
			return book;
		}
		
		return null;
	}

	public ArrayList<Book> searchAllBook() {
		return bookRepository.findAll();
	}

	public Book insertBook(Book book, User user) {
		if (user.getRole().equals("ADMIN") == true) {
			return bookRepository.insertBook(book);
		}
		
		return null;
	}

	public boolean deleteBook(String isbn, User user) {
		if (user.getRole().equals("ADMIN") == true) {
			return bookRepository.deleteBookByIsbn(isbn);
		}
		return false;
	}
}
