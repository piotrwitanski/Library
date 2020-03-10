package com.company.library.ui.controller;

import com.company.library.dto.Author;
import com.company.library.ui.datamodel.AuthorModel;
import com.company.library.validators.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AuthorDialogController implements Validator {

    @FXML
    private TextField firstNameTextField, lastNameTextField, nationalityTextField;

    public Author getNewAuthor() {
        if(validate()) {
            Author author = new Author();
            author.setFirstName(firstNameTextField.getText());
            author.setLastName(lastNameTextField.getText());
            author.setNationality(nationalityTextField.getText());

            return author;
        }
        else {
            return null;
        }

    }

    public void editAuthor(AuthorModel authorModel) {
        firstNameTextField.setText(authorModel.getFirstName());
        lastNameTextField.setText(authorModel.getLastName());
        nationalityTextField.setText(authorModel.getNationality());
    }

    public Author getUpdatedAuthor(int authorId) {
        if(validate()) {
            Author author = new Author();

            author.setId(authorId);
            author.setFirstName(firstNameTextField.getText());
            author.setLastName(lastNameTextField.getText());
            author.setNationality(nationalityTextField.getText());

            return author;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean validate() {
        if(firstNameTextField.getText().trim().isEmpty() || lastNameTextField.getText().trim().isEmpty() ||
                nationalityTextField.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

}
