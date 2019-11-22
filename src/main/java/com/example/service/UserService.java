package com.example.service;

import com.example.entity.User;
import com.example.util.DataSourceFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private UserService() {
    }

    public static UserService getInstance() {
        return UserServiceSingleton.INSTANCE;
    }

    public Optional<User> find(String login, String password) throws SQLException {
        String sql = "SELECT id, first_name, last_name, username, password FROM user WHERE username = ?";
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String passwordHash = resultSet.getString("password");
            return (BCrypt.checkpw(password, passwordHash)) ?
                    Optional.of(new User(resultSet.getInt("id"), firstName, lastName, username, passwordHash)) :
                    Optional.empty();
        }
        return Optional.empty();
    }


    public boolean save(User user) throws SQLException {
        String sql = "INSERT INTO user (first_name,last_name,password, username) VALUES (?,?,?,?)";
        boolean isSaved;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        String passwordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        statement.setString(3, passwordHash);
        statement.setString(4, user.getUsername());
        isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

    private static class UserServiceSingleton {
        private static final UserService INSTANCE = new UserService();
    }
}
