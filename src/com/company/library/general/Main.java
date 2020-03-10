package com.company.library.general;

import com.company.library.dto.Author;
import com.company.library.dto.Book;
import com.company.library.dto.User;
import com.company.library.jdbc.TestJdbc;
import com.company.library.tools.EncryptPassword;
import com.company.library.utils.AuthorUtils;
import com.company.library.utils.BookUtils;
import com.company.library.utils.UserUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/company/library/ui/view/loginWindow.fxml"));
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Library");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
