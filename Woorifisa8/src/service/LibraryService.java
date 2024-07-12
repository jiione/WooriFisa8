package service;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import model.domain.Book;
import model.domain.User;
import repository.BookRepository;

@RequiredArgsConstructor
public class LibraryService {
	private final BookRepository bookRepository;

	public Book borrowBook (String Isbn, User user) {
		if(user.getRole().equals("USER")==true) {
			Book book = bookRepository.findBookByIsbn(Isbn);
			if(book!=null) {
				book.setStatus(true);
			}
			else return null;
		}
		return null;
	}
	
	public Book returnBook (String Isbn, User user) {
		if(user.getRole().equals("USER")==true) {
			Book book = bookRepository.findBookByIsbn(Isbn);
			book.setStatus(false);
		}
		return null;
	}
	
	public ArrayList<Book> searchAllBook() {
		return bookRepository.findAll();
	}
	
	public Book insertBook (Book book, User user) {
		if(user.getRole().equals("ADMIN")==true) {
			return bookRepository.insertBook(book);
		}
		return null;
	}
	
	public boolean deleteBook(String isbn, User user) {
		if(user.getRole().equals("ADMIN")==true) {
			return bookRepository.deleteBookByIsbn(isbn);
		}
		return false;
	}
}
