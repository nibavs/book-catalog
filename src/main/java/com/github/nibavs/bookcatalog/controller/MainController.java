package com.github.nibavs.bookcatalog.controller;

import com.github.nibavs.bookcatalog.model.Book;
import com.github.nibavs.bookcatalog.model.BookDAO;
import com.github.nibavs.bookcatalog.model.Status;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            // Init modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/github/nibavs/bookcatalog/AddBookModal.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(loader.load(), 700, 500);
            dialogStage.setScene(scene);


            AddBookController controller = loader.getController();
//            AUTH add maybe
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            // Get new book after modal closing
            Book newBook = controller.getNewBook();
            if (newBook != null) {
                bookDAO.addBook(newBook);
                console.setText("Book added successfully!");
                refreshTable();
            }
        } catch (SQLException e) {
            console.setText("Book was not added! Error: " + e.getMessage());
        } catch (Exception e) {
            console.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void onDeleteBookButtonClicked() {
        try {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                bookDAO.deleteBook(selectedBook);
                console.setText("Book deleted successfully!");
                refreshTable();
            }
        } catch (SQLException e) {
            console.setText("Book was not deleted! Error: " + e.getMessage());
        }

    }

    @FXML
    protected void onGetAllBooksButtonClicked() {
        try {
            List<Book> allBooks = bookDAO.getAllBooks();
            bookTable.getItems().setAll(allBooks);

        } catch (SQLException e) {
            console.setText("Error: " + e.getMessage());
        }
    }

    protected void refreshTable() throws SQLException {
        bookTable.getItems().clear();
        List<Book> allBooks = bookDAO.getAllBooks();
        bookTable.getItems().setAll(allBooks);
    }

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<>("pages"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshTable();
        } catch (SQLException e) {
            console.setText("Error: " + e.getMessage());
        }

    }
}