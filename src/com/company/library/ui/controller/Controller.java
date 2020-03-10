package com.company.library.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.company.library.dictonaries.Dictonary.LOGIN_WINDOW_TITLE;
import static com.company.library.dictonaries.Errors.WINDOW_LOAD_ERROR;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;

public class Controller {

    @FXML
    BorderPane mainBorderPane;

    @FXML
    public void logout() {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("/com/company/library/ui/view/loginWindow.fxml"));
            setUserAgentStylesheet(STYLESHEET_CASPIAN);
            Stage stage = (Stage) mainBorderPane.getScene().getWindow();
            stage.close();
            stage.setTitle(LOGIN_WINDOW_TITLE);
            stage.setResizable(true);
            stage.setScene(new Scene(root, 300, 275));
            stage.setResizable(false);
            stage.show();


        }
        catch(IOException e) {
            e.getStackTrace();
            System.out.println(WINDOW_LOAD_ERROR);
        }
    }

    @FXML
    public void showAboutProgramDialog() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Author: Piotr Witański");
        alert.setContentText("This is basic app to add Authors and their books.");
        alert.showAndWait();

    }

    @FXML
    public void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("If you got any question or if you found any error please send me a message.");
        alert.showAndWait();
    }
}
