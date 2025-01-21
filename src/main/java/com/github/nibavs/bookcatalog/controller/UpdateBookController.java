package com.github.nibavs.bookcatalog.controller;

import com.github.nibavs.bookcatalog.model.Book;
import com.github.nibavs.bookcatalog.model.Status;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UpdateBookController {
    private Stage dialogStage;
    private Book updatedBook;
    private boolean isEdited;

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
    private Label errorOutputLabel;


    @FXML
    protected void initialize() {
        setStyles();
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
        try {
            String bookTitle = bookTitleField.getText();
            String bookAuthor = bookAuthorField.getText();
            int bookYear = Integer.parseInt(bookYearField.getText());
            int bookPages = Integer.parseInt(bookPagesField.getText());
            Status bookStatus = bookStatusChoiceBox.getValue();

            if (bookTitle.isEmpty() || bookAuthor.isEmpty() || bookStatus == null) {
                throw new Exception();
            }

            updatedBook.setTitle(bookTitle);
            updatedBook.setAuthor(bookAuthor);
            updatedBook.setYear(bookYear);
            updatedBook.setPages(bookPages);
            updatedBook.setStatus(bookStatus);

            isEdited = true;
            dialogStage.close();
        } catch (Exception e) {
            errorOutputLabel.setText("Fill in fields correctly!");
        }
    }

    @FXML
    protected void onCancelButtonClicked() {
        isEdited = false;
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUpdatedBook(Book updatedBook) {
        this.updatedBook = updatedBook;
        bookTitleField.setText(updatedBook.getTitle());
        bookAuthorField.setText(updatedBook.getAuthor());
        bookYearField.setText(updatedBook.getYear() + "");
        bookPagesField.setText(updatedBook.getPages() + "");
        bookStatusChoiceBox.getSelectionModel().select(updatedBook.getStatus());
    }

    public Book getUpdatedBook() {
        return updatedBook;
    }

    public boolean isEdited() {
        return isEdited;
    }

    protected void setStyles() {
        modalTitleLabel.setFont(new Font("Arial", 24));
        errorOutputLabel.setFont(new Font("Arial", 15));
        errorOutputLabel.setStyle("-fx-text-fill: red;");
    }
}
