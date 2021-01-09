package com.oolong.view.monitor;


import com.oolong.controller.service.EnterInto;
import com.oolong.controller.service.TableServe;
import com.oolong.controller.service.DishServer;
import com.oolong.model.bean.Dish;
import com.oolong.view.Main.LogMain;
import com.oolong.view.Main.RecordMain;
import com.oolong.view.utils.JdbcCrud;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author oolong
 */
    public class Customers implements Initializable{



    @FXML
    private AnchorPane interface1;

    @FXML
    private Button cancel;


    @FXML
    private Button ensure;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableView tableview;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> category;


    @FXML
    private TableView tableview2;

    @FXML
    private TableColumn<?, ?> name1;
    @FXML
    private TableColumn<?, ?> price1;
    @FXML
    private TableColumn<?, ?> category1;

    @FXML
    private Label sumprice2;
    @FXML
    private TextField selectText;
    @FXML
    private Button ensure10;
    @FXML
    private Button record;

/**
 * @Description 客户点餐结束后，显示要付金额
 * @author oolong
 * @date  15:42
 * @since version-1.0
 * @return
 */

    @FXML
    void ensure(ActionEvent event) throws Exception {
        // 历史订餐记录
        String sql = "insert into record(account,price) values(?,?) ";
        JdbcCrud.update(sql,LogInReg.getAccount3(),TableServe.getPRICE());

        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) ensure.getScene().getWindow();
            //窗口隐藏
            primaryStage.hide();
                    });

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "请支付" + TableServe.getPRICE());
        alert.show();
    }

    /**
     * @Description 初始化表格数据
     * @author oolong
     * @date  15:42
     * @since version-1.0
     * @return
     */

    public void iniList(){

        // 获取数据库信息,添加表格
        List<Dish> dishes = DishServer.checkAll();
        // 包装类
        ObservableList<Dish> dishList = FXCollections.observableArrayList();
        // 添加数据
        dishList.addAll(dishes);
        // 绑定表格
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        // 显示数据到表格
        tableview.setItems(dishList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        iniList();
        // 提示语句
        selectText.setPromptText("查询条件");

        String[] strings = new String[]{"name","price","category"};


        TableServe.Monitor(tableview,tableview2,strings,sumprice2,name1,price1,category1);

        TableServe.tableViewMonitor(tableview2,sumprice2,strings,name1,price1,category1);

    }

    public void cancel(ActionEvent actionEvent) {
        EnterInto.enter(cancel,new LogMain());


    }


    public void pricesum(ActionEvent actionEvent) {
    }


    public void selectText(ActionEvent actionEvent) {
    }

    /**
     * @Description 查询语句，将匹配的数据显示在表格中
     * @author oolong
     * @date  15:40
     * @since version-1.0
     * @return
     */

    public void ensure10(ActionEvent actionEvent) {
        String text = selectText.getText();
        // 获取数据库信息,添加表格
        List<Dish> dishes =null;
        if(text.length()>0 && !("".equals(text))){

            String sql = "select id,price,name,category from dish where id = ? or name = ? or price = ? or category = ?";
            dishes = JdbcCrud.getForList(Dish.class, sql,text,text,text,text);
        }else{
            dishes = DishServer.checkAll();
        }
        // 包装类
        ObservableList<Dish> dishList = FXCollections.observableArrayList();
        // 添加数据
        dishList.addAll(dishes);
        // 绑定表格
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        // 显示数据到表格
        tableview.setItems(dishList);

        // 清空文本框
        selectText.clear();
    }


    /**
     * @Description 进入历史订餐记录
     * @author oolong
     * @date  15:41
     * @since version-1.0
     * @return
     */

    public void record(ActionEvent actionEvent) {
        // EnterInto.enter(record,new RecordMain());
// // 进入客户端界面
//         Platform.runLater(() -> {
//             //获取按钮所在的窗口
//             Stage primaryStage = (Stage) record.getScene().getWindow();
//             //窗口隐藏
//             primaryStage.hide();
//             //加载注册窗口
//             try {
//                 new RecordMain().start(primaryStage); // 将此行Reg()换为客户端的界面
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
        RecordMain recordMain = new RecordMain();
        Stage stage = new Stage();
        try {
            recordMain.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
