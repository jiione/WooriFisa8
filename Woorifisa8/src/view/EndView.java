package view;

import model.domain.Book;
import model.domain.User;

import java.util.ArrayList;
import java.util.Map;

public class EndView {
	public static void bookListView(ArrayList<Book> books) {
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}
	
	public static void userListView(Map<String, User> users) {
		for (User user : users.values()) {
			System.out.println(user.toString());
		}
	}

	public static void successMessage(String message) {
		System.out.println(message);
	}
}
