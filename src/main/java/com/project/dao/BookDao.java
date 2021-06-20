package com.project.dao;

import com.project.domain.Book;
import com.project.domain.Reader;
import com.project.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements CrudDao<Long, Book> {
    private static final String SAVE_SQL = "INSERT INTO book(title, author, reader_id) VALUES (?,?,?)";

    private static final String FIND_ALL_SQL = "SELECT b.id as bookID, b.title, b.author, r.name, r.id  as readerID" +
            " FROM book b left join reader r on b.reader_id = r.id";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE book SET title = ?, author = ?, reader_id = ?  WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM book WHERE id = ?";
    private static final String FIND_ALL_READERS_WITH_BOOKS_SQL =
            "SELECT b.title, b.author, r.id, r.name FROM reader r join book b on r.id = b.reader_id " +
                    "WHERE b.reader_id  NOTNULL ";
    private static final String UPDATE_BOOK_BY_TITLE_SQL = "UPDATE book SET reader_id = ? WHERE title = ? ";

    @Override
    public Book create(Book book) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setObject(3, book.getReader());
            preparedStatement.executeUpdate();
            ResultSet generatedKey = preparedStatement.getGeneratedKeys();
            if (generatedKey.next()) {
                book.setId(generatedKey.getLong("id"));
            }
            return book;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Book get(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = new Book(resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"), new Reader(
                        resultSet.getLong("readerID"),
                        resultSet.getString("name"))
                );
            }
            return book;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getLong("bookId"),
                        resultSet.getString("title"),
                        resultSet.getString("author"), new Reader(
                        resultSet.getLong("readerID"),
                        resultSet.getString("name")))
                );
            }
            return books;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Book update(Book book) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setObject(3, book.getReader());
            preparedStatement.setLong(4, book.getId());
            preparedStatement.executeUpdate();
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

    public static List<Book> getAllReadersWithBooks() {

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_READERS_WITH_BOOKS_SQL)) {
            ArrayList<Book> readersWithBooks = new ArrayList<>();

            while (resultSet.next()) {
                readersWithBooks.add(new Book(resultSet.getLong("id"), resultSet.getString("title"),
                        resultSet.getString("author"),
                        new Reader(resultSet.getLong("id"),
                                resultSet.getString("name"))));
            }
            return readersWithBooks;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public static boolean handOverBook(String bookName) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_TITLE_SQL)) {
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, bookName);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }

    public static boolean giveOutBook(Long readerId, String bookName) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_BY_TITLE_SQL)) {
            preparedStatement.setLong(1, readerId);
            preparedStatement.setString(2, bookName);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }
}