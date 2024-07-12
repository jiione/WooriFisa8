package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.domain.User;

public class UserRepository {
	private Map<String, User> userList;
	private static UserRepository instance = new UserRepository();

	private UserRepository() {
		userList = new HashMap<>();
	}

	private static UserRepository getInstance() {
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

}
