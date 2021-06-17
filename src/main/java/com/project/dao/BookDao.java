package com.project.dao;

import com.project.domain.Book;
import com.project.util.ConnectionManager;

import java.sql.*;
import java.util.List;

public class BookDao implements CrudDao<Book> {
    private static final String DELETE_SQL = "DELETE FROM book WHERE id = ?";
    private static final String SAVE_SQL = "INSERT INTO book(title, author, quantity)\n" +
            "VALUES (?,?,?)";

    @Override
    public Book get(Long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book update(Long id) {
        return null;
    }

    @Override
    public Book create(Book book) {
        try (Connection connection = ConnectionManager.open();
             final PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getQuantity());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                book.setId(generatedKey.getInt("id"));
            }
            return book;
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
