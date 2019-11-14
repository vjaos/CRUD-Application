package com.example.service;

import com.example.entity.Stuff;
import com.example.dao.StuffDao;
import com.example.util.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StuffService implements StuffDao {

    private StuffService() {
    }

    public static StuffService getInstance() {
        return StuffServiceSingleton.INSTANCE;
    }

    @Override
    public Optional<Stuff> find(Integer id) throws SQLException {
        String sqlReq = "SELECT stuff_id, name, description, quantity FROM stuffs WHERE stuff_id = ?";
        int id_stuff = 0, quantity = 0;
        String name = "", description = "";
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlReq);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id_stuff = resultSet.getInt("stuff_id");
            quantity = resultSet.getInt("quantity");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
        }
        return Optional.of(new Stuff(id_stuff, name, description, quantity));
    }


    @Override
    public List<Stuff> findAll() throws SQLException {
        List<Stuff> result = new ArrayList<>();
        String sqlReq = "SELECT stuff_id, name, description, quantity FROM stuffs";
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlReq);

        while (resultSet.next()) {
            int id_stuff = resultSet.getInt("stuff_id");
            int quantity = resultSet.getInt("quantity");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            result.add(new Stuff(id_stuff, name, description, quantity));
        }
        return result;
    }

    @Override
    public boolean save(Stuff stuff) throws SQLException {
        String sqlReq = "INSERT INTO stuffs (name, description, quantity) VALUES (?, ?, ?)";
        boolean isSaved;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlReq);
        statement.setString(1, stuff.getName());
        statement.setString(2, stuff.getDescription());
        statement.setInt(3, stuff.getQuantity());
        isSaved = statement.executeUpdate() > 0;
        return isSaved;
    }

    @Override
    public boolean update(Stuff stuff) throws SQLException {
        String sqlReq = "UPDATE stuffs SET name = ?, description = ?, quantity = ? WHERE stuff_id = ?";
        boolean isUpdated;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlReq);
        statement.setString(1, stuff.getName());
        statement.setString(2, stuff.getDescription());
        statement.setInt(3, stuff.getQuantity());
        statement.setInt(4, stuff.getId());
        isUpdated = statement.executeUpdate() > 0;
        return isUpdated;
    }

    @Override
    public boolean delete(Stuff stuff) throws SQLException {
        String sqlReq = "DELETE FROM stuffs WHERE stuff_id = ?";
        boolean isDeleted;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlReq);
        statement.setInt(1, stuff.getId());
        isDeleted = statement.executeUpdate() > 0;
        return isDeleted;
    }


    private static class StuffServiceSingleton {
        private static final StuffService INSTANCE = new StuffService();
    }
}
