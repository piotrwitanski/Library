package com.company.library.ui.controller;

import com.company.library.dto.User;
import com.company.library.tools.EncryptPassword;
import com.company.library.ui.datamodel.UIData;
import com.company.library.validators.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.company.library.dictonaries.Dictonary.MAIN_WINDOW_TITLE;
import static com.company.library.dictonaries.Dictonary.USER_ID;
import static com.company.library.dictonaries.Errors.*;

public class LoginWindowController implements Validator {

    private EncryptPassword encryptPassword;
    private String login, password;
    private User user;
    private boolean textFieldEmpty;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private GridPane loginGridPane;

    public void initialize() {
        encryptPassword = new EncryptPassword();
    }

    @FXML
    public void handlePasswordField() {

        user = UIData.getInstance().downloadUser(USER_ID);
        login = loginTextField.getText();
        password = passwordTextField.getText();
        textFieldEmpty = false;

        if(validate()) {
            if(textFieldEmpty) {
                createAlert(EMPTY_LOGIN_PASS);
            }
            else {
                createAlert(LOGIN_PASS_ERROR);
            }
            return;
        }
        else {
            showMainWindow();
        }
    }

    private void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(WRONG_DATA);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        loginTextField.clear();
        passwordTextField.clear();
    }


    public void showMainWindow() {

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/com/company/library/ui/view/main.fxml"));
            Stage stage = (Stage) loginGridPane.getScene().getWindow();
            stage.close();
            stage.setTitle(MAIN_WINDOW_TITLE);
            stage.setScene(new Scene(root, 500, 500));
            stage.show();
        }
        catch(IOException e) {
            e.getStackTrace();
            System.out.println(WINDOW_LOAD_ERROR);
        }
    }

    @Override
    public boolean validate() {
        return validate(user, login, password);
    }

    private boolean validate(User user, String login, String password) {
        if(login.trim().isEmpty() || password.trim().isEmpty()) {
            textFieldEmpty = true;
            return true;
        }
        textFieldEmpty = false;
        return !encryptPassword.checkPassLogin(user, login, password);
    }
}
