package com.project.dao;

import com.project.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourse {

    public static void main(String[] args) throws SQLException {

        Class<Driver> driverClass = Driver.class;
        String sql = "SELECT * FROM book";

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement();
        ) {
            System.out.println(connection.getTransactionIsolation());
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
    }
}