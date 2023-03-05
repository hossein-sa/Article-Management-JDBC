package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    public static void createTable() throws SQLException{
        try(Connection conn = getConnection();
        Statement stm = conn.createStatement()){
            String createUserTableSql = "CREATE TABLE IF not EXISTS users (id SERIAL PRIMARY KEY, username VARCHAR(50) NOT NULL, national_code VARCHAR(10) NOT NULL, birthday DATE NOT NULL, password VARCHAR(50) NOT NULL)";
            stm.executeUpdate(createUserTableSql);
            String createArticleTableSql = "CREATE TABLE IF not EXISTS articles (id SERIAL PRIMARY KEY, title VARCHAR(50) NOT NULL, content TEXT NOT NULL, user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE)";
            stm.executeUpdate(createArticleTableSql);
        }
    }


    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/article_management";
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, user, password);
    }
}
