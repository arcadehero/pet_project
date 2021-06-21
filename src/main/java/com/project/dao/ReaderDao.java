package com.project.dao;

import com.project.domain.Reader;
import com.project.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReaderDao implements CrudDao<Long, Reader> {
    private static final String SAVE_SQL = "INSERT INTO reader(name) VALUES (?)";
    private static final String FIND_ALL_SQL = "SELECT id, name  FROM reader";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE reader SET name = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM reader WHERE id = ?";

    @Override
    public Reader create(Reader reader) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, reader.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reader.setId(generatedKeys.getLong("id"));
            }
            return reader;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Reader get(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Reader reader = null;
            while (resultSet.next()) {
                reader = new Reader(resultSet.getString("name")
                );
            }
            return reader;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Reader> getAll() {
        ArrayList<Reader> readers = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Reader reader;
            while (resultSet.next()) {
                reader = new Reader(resultSet.getLong("id"),
                        resultSet.getString("name")
                );
                readers.add(reader);
            }
            return readers;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public Reader update(Reader reader) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, reader.getName());
            preparedStatement.setLong(2, reader.getId());
            preparedStatement.executeUpdate();
            return reader;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}