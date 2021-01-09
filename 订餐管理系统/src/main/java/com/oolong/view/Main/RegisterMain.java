package com.oolong.view.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author oolong
 */
public class RegisterMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("注册");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
