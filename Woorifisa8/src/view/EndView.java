package view;

import model.domain.Book;

import java.util.ArrayList;

public class EndView {
	public static void bookListView(ArrayList<Book> books) {
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}

	public static void successMessage(String message) {
		System.out.println(message);
	}
}
