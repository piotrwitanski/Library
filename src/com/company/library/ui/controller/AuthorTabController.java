package com.company.library.ui.controller;

import com.company.library.dto.Author;
import com.company.library.dto.Book;
import com.company.library.ui.datamodel.AuthorModel;
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

public class AuthorTabController {

    @FXML
    private TableView<AuthorModel> authorsTableView;

    @FXML
    private BorderPane authorsBorderPane;

    public void initialize() {
        UIData.getInstance().loadAuthors();
        authorsTableView.setItems(UIData.getInstance().getAuthorModels());

    }

    @FXML
    public void showNewAuthorDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(authorsBorderPane.getScene().getWindow());
        dialog.setTitle(ADD_NEW_AUTHOR);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/company/library/ui/view/authorDialog.fxml"));

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

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            saveAuthor(fxmlLoader);
        }
    }

    private void saveAuthor(FXMLLoader fxmlLoader) {
        AuthorDialogController authorDialogController = fxmlLoader.getController();
        Author author = authorDialogController.getNewAuthor();
        if(author != null) {
            int authorId = UIData.getInstance().saveAuthor(author);
            UIData.getInstance().addAuthorModel(UIData.getInstance().downloadAuthor(authorId));
            authorsTableView.setItems(UIData.getInstance().getAuthorModels());
        }
        else {
            showEmptyFieldsAlert();
            showNewAuthorDialog();
        }
    }

    private void showEmptyFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(EMPTY);
        alert.setHeaderText(null);
        alert.setContentText(EMPTY_FIELD);
        alert.showAndWait();
    }

    @FXML
    public void showNewBookDialog() {
        AuthorModel selectedAuthorModel = authorsTableView.getSelectionModel().getSelectedItem();
        if(selectedAuthorModel == null) {
            showNoAuthorSelectedInformationAlert();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(authorsBorderPane.getScene().getWindow());
        dialog.setTitle(ADD_NEW_BOOK);
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

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            saveBook(fxmlLoader, Integer.parseInt(selectedAuthorModel.getAuthorId()));
        }
    }

    private void saveBook(FXMLLoader fxmlLoader, int authorId) {
        BookDialogController bookDialogController = fxmlLoader.getController();
        Book book = bookDialogController.getNewBook();
        if(book != null) {
            int bookId = UIData.getInstance().saveBook(book, authorId);
            UIData.getInstance().addBookModel(UIData.getInstance().downloadBook(bookId));
        }
        else {
            showEmptyFieldsAlert();
            showNewBookDialog();
        }
    }

    @FXML
    public void showEditAuthorDialog() {
        AuthorModel selectedAuthorModel = authorsTableView.getSelectionModel().getSelectedItem();
        if(selectedAuthorModel == null) {
            showNoAuthorSelectedInformationAlert();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(authorsBorderPane.getScene().getWindow());
        dialog.setTitle(EDIT_AUTHOR);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/company/library/ui/view/authorDialog.fxml"));

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

        AuthorDialogController authorDialogController = fxmlLoader.getController();
        authorDialogController.editAuthor(selectedAuthorModel);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Author author = authorDialogController.getUpdatedAuthor(Integer.parseInt(selectedAuthorModel.getAuthorId()));
            if(author != null) {
                UIData.getInstance().updateAuthor(author);
                UIData.getInstance().updateAuthorModels(author);
                authorsTableView.setItems(UIData.getInstance().getAuthorModels());
            }
            else {
                showEmptyFieldsAlert();
                showEditAuthorDialog();
            }
        }
    }

    @FXML
    public void showDeleteAuthorDialog() {
        AuthorModel selectedAuthorModel = authorsTableView.getSelectionModel().getSelectedItem();
        if(selectedAuthorModel == null) {
            showNoAuthorSelectedInformationAlert();
            return;
        }

        showDeleteAlert(selectedAuthorModel);
    }

    private void showNoAuthorSelectedInformationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(NO_AUTHOR_SELECTED);
        alert.setHeaderText(null);
        alert.setContentText(SELECT_OBJECT);
        alert.showAndWait();
    }

    private void showDeleteAlert(AuthorModel selectedAuthorModel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(DELETE_OBJECT);
        alert.setHeaderText(null);
        alert.setContentText(DELETE_OBJECT_CONTENT + getAuthorName(selectedAuthorModel));

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UIData.getInstance().removeBooksWithAuthor(Integer.parseInt(selectedAuthorModel.getAuthorId()));
            UIData.getInstance().deleteAuthor(selectedAuthorModel);

        }
    }

    private String getAuthorName(AuthorModel authorModel) {
        return authorModel.getFirstName() + " " + authorModel.getLastName();
    }
}
