package com.company.library.ui.controller;

import com.company.library.dto.Book;
import com.company.library.ui.datamodel.BookModel;
import com.company.library.ui.datamodel.UIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

import static com.company.library.dictonaries.Dictonary.*;
import static com.company.library.dictonaries.Errors.EMPTY;
import static com.company.library.dictonaries.Errors.EMPTY_FIELD;
import static com.company.library.dictonaries.Errors.WINDOW_LOAD_ERROR;

public class BookTabController {

    @FXML
    private TableView<BookModel> booksTableView;

    @FXML
    private BorderPane booksBorderPane;


    public void initialize() {
        UIData.getInstance().loadBooks();
        booksTableView.setItems(UIData.getInstance().getBookModels());

    }

    public TableView<BookModel> getBooksTableView() {
        return booksTableView;
    }

    @FXML
    public void showEditBookDialog() {
        BookModel selectedBookModel = booksTableView.getSelectionModel().getSelectedItem();
        if(selectedBookModel == null) {
            showNoSelectedBookAlert();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(booksBorderPane.getScene().getWindow());
        dialog.setTitle(EDIT_BOOK);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/company/library/ui/view/bookDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println(WINDOW_LOAD_ERROR);
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        BookDialogController bookDialogController = fxmlLoader.getController();
        bookDialogController.editBook(selectedBookModel);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Book book = bookDialogController.getUpdatedBook(Integer.parseInt(selectedBookModel.getBookId()));
            if(book != null) {
                UIData.getInstance().updateBook(book);
                UIData.getInstance().updateBookModels(book);
                booksTableView.setItems(UIData.getInstance().getBookModels());
            }
            else {
                showEmptyFieldsAlert();
                showEditBookDialog();
            }
        }
    }

    private void showEmptyFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(EMPTY);
        alert.setHeaderText(null);
        alert.setContentText(EMPTY_FIELD);
        alert.showAndWait();
    }

    private void showNoSelectedBookAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(NO_BOOK_SELECTED);
        alert.setHeaderText(null);
        alert.setContentText(SELECT_OBJECT);
        alert.showAndWait();
    }

    @FXML
    public void showDeleteBookDialog() {
        BookModel selectedBookModel = booksTableView.getSelectionModel().getSelectedItem();
        if(selectedBookModel == null) {
            showNoSelectedBookAlert();
            return;
        }

        showDeleteAlert(selectedBookModel);
    }

    private void showDeleteAlert(BookModel selectedBookModel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(DELETE_OBJECT);
        alert.setHeaderText(null);
        alert.setContentText(DELETE_OBJECT_CONTENT + getBookName(selectedBookModel));

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UIData.getInstance().deleteBook(selectedBookModel);
        }
    }

    private String getBookName(BookModel bookModel) {
        return bookModel.getTitle();
    }
}
