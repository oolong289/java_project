package com.oolong.view.Main;

import com.oolong.controller.service.EnterInto;
import com.oolong.model.bean.RecordBean;
import com.oolong.view.monitor.Record;
import com.oolong.view.utils.JdbcCrud;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecordMain extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/record.fxml"));
        stage.setTitle("订餐管理系统");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        // stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        //     @Override
        //     public void handle(WindowEvent windowEvent) {
        //         Stage stage1 = new Stage();
        //         try {
        //             new CustomersMain().start(stage1);
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });
        stage.show();
    }
}
