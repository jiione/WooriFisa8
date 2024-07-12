package service;

import lombok.Getter;
import lombok.Setter;
import model.domain.User;
import repository.UserRepository;


public class UserService {
	UserRepository userRepository = UserRepository.getInstance();
	
    private boolean validateAdmin(String userId, String password) {    	
        User user = userRepository.getUser(userId);
        return user != null && user.getRole().equals("ADMIN") && user.getPw().equals(password);
    }
    
    
    
}
