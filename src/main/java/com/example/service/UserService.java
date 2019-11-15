package com.example.service;

import com.example.entity.User;
import com.example.util.DataSourceFactory;

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


    public boolean isExist(String login, String password) throws SQLException {
        String sql = "SELECT id, first_name, last_name, password, username FROM user WHERE username = ? AND password = ?";
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }


    public boolean save(User user) throws SQLException {
        String sql = "INSERT INTO user (first_name,last_name,password, username) VALUES (?,?,?,?)";
        boolean isSaved;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getUsername());
        isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

    private static class UserServiceSingleton {
        private static final UserService INSTANCE = new UserService();
    }
}
