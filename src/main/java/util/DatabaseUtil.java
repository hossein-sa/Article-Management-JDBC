package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/article_management";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, user, password);
    }
}
