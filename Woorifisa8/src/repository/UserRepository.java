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
		userList = new HashMap<>();
	}

	public static UserRepository getInstance() {
		return instance;
	}

	public List<User> getAllUsers() {
		return new ArrayList<>(userList.values());
	}

	public User addUser(User user) {
		String userId = user.getId();
		return userList.put(userId, user);
	}

	public boolean deleteUser(String userId) {
		return userList.remove(userId) != null;
	}

	public User getUser(String userId) {
		return userList.get(userId); // nullable
	}

	public boolean isLoginSuccess(String userId, String password) {
		User user = userList.get(userId);

		if (user != null && user.getPw().equals(password)) {
			return false;
		}
		return true;
	}

	public Book borrowBook(Book book, User user) {
		User foundUser =  getUser(user.getId());
    	ArrayList<Book> userBookList = foundUser.getBookList();
    	userBookList.add(book);
    	return book;	
    }
	
	public Book returnBook(Book book, User user) {
		User foundUser =  getUser(user.getId());
    	ArrayList<Book> userBookList = foundUser.getBookList();
    	
    	for (int i=0;i<userBookList.size();i++) {
    		if (userBookList.get(i).getIsbn().equals(book.getIsbn())) {
    			return userBookList.remove(i);
    		}
    	}
    	return null;	
	}
}
