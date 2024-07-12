package service;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import model.domain.Book;
import model.domain.User;
import repository.UserRepository;


public class UserService {
	UserRepository userRepository = UserRepository.getInstance();
	
    private boolean validateAdmin(String userId, String password) {    	
        User user = userRepository.getUser(userId);
        return user != null && user.getRole().equals("ADMIN") && user.getPw().equals(password);
    }
    
    
    //유저 추가
    public User addUser(String adminId, String adminPw, User user) {
    	if (validateAdmin(adminId, adminPw)) {
    		return userRepository.addUser(user);
    	}
    	return null;
    }
    
    //유저 삭제
    public boolean deleteUser(String adminId, String adminPw, User user) {
    	if (validateAdmin(adminId, adminPw)) {
    		return userRepository.deleteUser(user.getId());
    	}
    	return false;
    }
    
    //유저가 도서 대출
    public Book borrowBook(Book book, User user) {
    	return userRepository.borrowBook(book, user);
    }
    
    
    //유저가 도서 반납
    public Book returnBook(Book book, User user) {
    	return userRepository.returnBook(book, user);
    }
}
