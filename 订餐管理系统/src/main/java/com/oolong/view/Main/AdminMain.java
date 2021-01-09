package com.oolong.view.Main;

import com.oolong.model.bean.Dish;
import com.oolong.view.monitor.Admin;
import com.oolong.view.utils.JdbcCrud;
import com.oolong.view.utils.JdbcUtil;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Iterator;
import java.util.List;

/**
 * @author oolong
 */
public class AdminMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/admin.fxml"));
        stage.setTitle("管理端");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
