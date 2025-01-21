package com.github.nibavs.bookcatalog.controller;

import com.github.nibavs.bookcatalog.model.Book;
import com.github.nibavs.bookcatalog.model.BookDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;
import java.util.List;

public class MainController {
    private BookDAO bookDAO = new BookDAO();

    @FXML
    private Label mainViewTitle;

    @FXML
    private Label outputInfoLabel;

    @FXML
    private Button updateBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private TextField searchBookTextField;

    @FXML
    private Button searchBooksButton;

    @FXML
    private Button addBookButton;

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
    public void initialize() {
        setStyles();


        initTableView();

        try {
            refreshTable();
        } catch (SQLException e) {
            printErrorOutput("Error: " + e.getMessage());
        }

    }

    @FXML
    protected void onAddBookButtonClicked() {
        try {
            // Init modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/github/nibavs/bookcatalog/AddBookModal.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(loader.load(), 400, 500);
            dialogStage.setScene(scene);


            AddBookController controller = loader.getController();
//            AUTH add maybe
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            // Get new book after modal closing
            Book newBook = controller.getNewBook();
            if (newBook != null) {
                bookDAO.addBook(newBook);
                printInfoOutput("Book added successfully!");
                refreshTable();
            }
        } catch (Exception e) {
            printErrorOutput("Book was not added! Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onUpdateBookButtonClicked() {
        try {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook == null) return;

            // Init modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/github/nibavs/bookcatalog/UpdateBookModal.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Update book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(loader.load(), 400, 500);
            dialogStage.setScene(scene);


            UpdateBookController controller = loader.getController();
//            AUTH add maybe
            controller.setDialogStage(dialogStage);

            // Selected book into controller
            controller.setUpdatedBook(selectedBook);
            dialogStage.showAndWait();

            // Get updated book after modal closing
            Book updatedBook = controller.getUpdatedBook();

            if (updatedBook != null && controller.isEdited()) {
                bookDAO.updateBook(updatedBook);
                printInfoOutput("Book updated successfully!");
                refreshTable();
            }
        } catch (Exception e) {
            printErrorOutput("Book was not updated! Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onDeleteBookButtonClicked() {
        try {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookDAO.deleteBook(selectedBook);
                printInfoOutput("Book deleted successfully!");
                refreshTable();
            }
        } catch (SQLException e) {
            printErrorOutput("Book was not deleted! Error: " + e.getMessage());
        }

    }

    @FXML
    protected void onSearchBooksButtonClicked() {
        try {
            List<Book> books;
            if (searchBookTextField.getText().isEmpty()) {
                books = bookDAO.getAllBooks();
            } else {
                books = bookDAO.getSearchedBooks(searchBookTextField.getText());
            }
                bookTable.getItems().setAll(books);
        } catch (SQLException e) {
            printErrorOutput("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void initTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    protected void setStyles() {
        mainViewTitle.setFont(new Font("Arial", 30));
        outputInfoLabel.setFont(new Font("Arial", 15));
        updateBookButton.setMinWidth(60);
        deleteBookButton.setMinWidth(60);
    }

    protected void refreshTable() throws SQLException {
        bookTable.getItems().clear();
        List<Book> allBooks = bookDAO.getAllBooks();
        bookTable.getItems().setAll(allBooks);
    }

    protected void printErrorOutput(String message) {
        outputInfoLabel.setStyle("-fx-text-fill: red;");
        outputInfoLabel.setText(message);
    }

    protected void printInfoOutput(String message) {
        outputInfoLabel.setStyle("-fx-text-fill: green;");
        outputInfoLabel.setText(message);
    }

}