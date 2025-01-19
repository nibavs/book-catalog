package com.github.nibavs.bookcatalog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, year, pages, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setString(5, book.getStatus().toString());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, year = ?, pages = ?, status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setString(5, book.getStatus().toString());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("pages"),
                        Status.valueOf(resultSet.getString("status"))
                ));
            }

        }
        return books;
    }

    public List<Book> getSearchedBooks(String search) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title LIKE ? UNION SELECT * FROM book WHERE author LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getInt("year"),
                            resultSet.getInt("pages"),
                            Status.valueOf(resultSet.getString("status"))
                            ));
                }
            }
        }
        return books;
    }

}
