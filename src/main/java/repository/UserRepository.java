package repository;

import entity.User;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public int addUser(User user) throws SQLException {
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
                    return id;
                } else {
                    throw new SQLException("creating user failed, no ID obtained.");
                }
            }
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = """
                SELECT * FROM users
                """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapUser(rs));
            }
            return users;
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
