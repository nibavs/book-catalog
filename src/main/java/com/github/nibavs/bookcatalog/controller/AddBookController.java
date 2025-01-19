package com.github.nibavs.bookcatalog.controller;

import com.github.nibavs.bookcatalog.model.Book;
import com.github.nibavs.bookcatalog.model.Status;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddBookController {
    private Stage dialogStage;
    private Book newBook;

    @FXML
    private Label modalTitleLabel;

    @FXML
    private TextField bookTitleField;

    @FXML
    private TextField bookAuthorField;

    @FXML
    private TextField bookYearField;

    @FXML
    private TextField bookPagesField;

    @FXML
    private Label bookStatusLabel;

    @FXML
    private ChoiceBox<Status> bookStatusChoiceBox;


    @FXML
    protected void initialize() {
        modalTitleLabel.setFont(new Font("Arial", 24));
        // Input only integer handling
        // For year
        bookYearField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                bookYearField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // For pages
        bookPagesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                bookPagesField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        bookStatusChoiceBox.getItems().addAll(Status.values());
    }

    @FXML
    protected void onConfirmButtonClicked() {
        // Error handling here
        String bookTitle = bookTitleField.getText();
        String bookAuthor = bookAuthorField.getText();
        int bookYear = Integer.parseInt(bookYearField.getText());
        int bookPages = Integer.parseInt(bookPagesField.getText());
        Status bookStatus = bookStatusChoiceBox.getValue();

        newBook = new Book(bookTitle, bookAuthor, bookYear, bookPages, bookStatus);

        dialogStage.close();
    }

    @FXML
    protected void onCancelButtonClicked() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Book getNewBook() {
        return newBook;
    }


}
