package menu;

import entity.User;
import service.UserService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final UserService userService;
    private final Scanner scanner;

    public UserMenu(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("========== USER MENU ==========");
            System.out.println("1. Add User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Get All Users");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    addUser();
                    break;
                case 2:
                    getUserById();
                    break;
                case 3:
                    getAllUsers();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
    private void addUser() {
        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            if (username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty.");
            }
            System.out.print("Enter national code: ");
            String nationalCode = scanner.nextLine();
            System.out.print("Enter birthday (YYYY-MM-DD): ");
            String birthdayStr = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayStr);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            User user = new User(username, nationalCode, birthday, password);
            int id = userService.addUser(user);
            System.out.println("User added with ID: " + id);
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getUserById() {
        try {
            System.out.print("Enter user ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            User user = userService.getUserById(id);
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error getting user: " + e.getMessage());
        }
    }

    private void getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Error getting users: " + e.getMessage());
        }
    }
}
