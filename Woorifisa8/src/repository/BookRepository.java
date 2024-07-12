package repository;

import java.util.ArrayList;

import model.domain.Book;

public class BookRepository {
	private static ArrayList<Book> bookList = new ArrayList<>();
	
	public boolean deleteBookByIsbn(String Isbn) {
		for(Book book : bookList) {
			if(Isbn.equals(book.getIsbn()) == true) {
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
		for(Book book : bookList) {
			if(Isbn.equals(book.getIsbn()) == true) {
				return book;
			}
		}
		return null;
	}
}