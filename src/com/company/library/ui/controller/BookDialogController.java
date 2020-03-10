package com.company.library.ui.controller;

import com.company.library.dto.Book;
import com.company.library.ui.datamodel.BookModel;
import com.company.library.validators.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookDialogController implements Validator {

    @FXML
    private TextField titleTextField;

    public Book getNewBook() {
        if(validate()) {
            Book book = new Book();
            book.setTitle(titleTextField.getText());

            return book;
        }
        else {
            return null;
        }
    }

    public void editBook(BookModel bookModel) {
        titleTextField.setText(bookModel.getTitle());
    }

    public Book getUpdatedBook(int bookId) {
        if(validate()) {
            Book book = new Book();

            book.setId(bookId);
            book.setTitle(titleTextField.getText());

            return book;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean validate() {
        if(titleTextField.getText().trim().isEmpty()) {

            return false;
        }
        return true;
    }
}
