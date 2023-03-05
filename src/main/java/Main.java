import menu.UserMenu;
import repository.UserRepository;
import service.UserService;
import util.DatabaseUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a UserService object
        UserService userService = new UserService(new UserRepository());
        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        // Create a UserMenu object
        UserMenu userMenu = new UserMenu(userService, scanner);
        // Start the menu
        userMenu.start();
        // Close the Scanner object
        scanner.close();


    }
}
