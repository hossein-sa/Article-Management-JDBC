package repository;

import entity.User;
import util.DatabaseUtil;

import java.sql.*;

public class UserRepository {

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, national_code, birthday, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNationalCode());
            ps.setDate(3, user.getBirthday());
            ps.setString(4, user.getPassword());

            int rowInserted = ps.executeUpdate();

            if (rowInserted == 0) {
                throw new SQLException("Creating user failed, no rows affected");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    user.setId(id);
                } else {
                    throw new SQLException("creating user failed, no ID obtained.");
                }
            }
        }
    }



    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setNationalCode(resultSet.getString("national_code"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }


}
