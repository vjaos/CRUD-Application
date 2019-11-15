package com.example.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DataSourceFactory {
    private final DataSource dataSource;

     private DataSourceFactory() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        //here your database properties
        try {
            mysqlDataSource.setDatabaseName("dbname");
            mysqlDataSource.setServerName("servername");
            mysqlDataSource.setPort(3306);
            mysqlDataSource.setUser("user");
            mysqlDataSource.setPassword("password");
            mysqlDataSource.setServerTimezone("UTC");
        } catch (SQLException sqle) {
            System.err.println("SQL Exception: " + sqle.getMessage());
        } finally {
            this.dataSource = mysqlDataSource;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DataSourceFactorySingleton.INSTANCE.dataSource.getConnection();
    }

    private static class DataSourceFactorySingleton {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }

}
