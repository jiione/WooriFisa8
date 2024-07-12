package service;

import lombok.RequiredArgsConstructor;
import model.domain.Book;
import model.domain.User;
import repository.BookRepository;

@RequiredArgsConstructor
public class LibraryService {
	private final BookRepository bookRepository;

	public Book borrowBook (String Isbn, User user) {
		if(user.getRole().equals("USER")==true) {
			Book book = bookRepository.findBookByIsbn(Isbn)
			if(book!=null) {
				user.
			}
			else return null;
		}
		return null;
		
	}
}
