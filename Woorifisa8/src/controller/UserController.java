package controller;

import model.domain.User;
import service.UserService;
import view.EndView;
import view.FailView;

public class UserController {
    private final UserService userService = new UserService();

    public void addUser(String adminId, String adminPw, User user) {
        User newUser = userService.addUser(adminId, adminPw, user);
        if (newUser != null) {
            EndView.successMessage("User added successfully: " + newUser.getName());
        } else {
            FailView.failMessage("Failed to add user. Only admins can add users.");
        }
    }

    public void removeUser(String adminId, String adminPw, User user) {
        boolean success = userService.deleteUser(adminId, adminPw, user);
        if (success) {
            EndView.successMessage("User removed successfully.");
        } else {
            FailView.failMessage("Failed to remove user. Only admins can remove users.");
        }
    }
    
    public void viewAllUsers() {
        EndView.userListView(userService.getAllUsers());
    }
}