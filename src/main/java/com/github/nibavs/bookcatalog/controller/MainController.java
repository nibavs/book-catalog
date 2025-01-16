package com.github.nibavs.bookcatalog.controller;

import com.github.nibavs.bookcatalog.model.Book;
import com.github.nibavs.bookcatalog.model.BookDAO;
import com.github.nibavs.bookcatalog.model.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.List;

public class MainController {
    private BookDAO bookDAO = new BookDAO();

    @FXML
    private Label console;

    @FXML
    private Button getAllBooksButton;

    @FXML
    private Button addBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, Integer> idColumn;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, Integer> yearColumn;

    @FXML
    private TableColumn<Book, Integer> pagesColumn;

    @FXML
    private TableColumn<Book, String> statusColumn;


    @FXML
    protected void onAddBookButtonClicked() {
        try {
            bookDAO.addBook(new Book(12, "Title", "NikBavs", 2025, 229, Status.AVAILABLE));
            console.setText("Book added successfully!");
        } catch (SQLException e) {
            console.setText("Book was not added! Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteBookButtonClicked() {
        try {
//            bookDAO.deleteBook(bookTable.getSelectionModel().getSelectedItem());
            // Hardcode
            Book book = new Book(23,"Title", "NikBavs", 2025, 229, Status.AVAILABLE);
            bookDAO.deleteBook(book);
            console.setText("Book deleted successfully!");
        } catch (SQLException e) {
            console.setText("Book was not deleted! Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onGetAllBooksButtonClicked() {
        try {
            List<Book> allBooks = bookDAO.getAllBooks();
            bookTable.getItems().setAll(allBooks);
            StringBuilder sb = new StringBuilder();
            for (Book book : allBooks) {
                sb.append(book.getTitle() + "\n");
            }
            console.setText(sb.toString());
        } catch (SQLException e) {
            console.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {

    }
}