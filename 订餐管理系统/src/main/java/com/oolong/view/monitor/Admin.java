package com.oolong.view.monitor;


import com.oolong.controller.service.EnterInto;
import com.oolong.controller.service.DishServer;
import com.oolong.model.bean.Dish;
import com.oolong.view.Main.LogMain;
import com.oolong.view.utils.JdbcCrud;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author oolong
 */
public class Admin implements Initializable{


    @FXML
    private AnchorPane interface1;

    @FXML
    private Button cancel;


    @FXML
    private Button ensure;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TableColumn<Dish, Integer> price;

    @FXML
    private TableColumn<Dish, String>name;

    @FXML
    private TableView tableview;

    @FXML
    private TableColumn<Dish, Integer> id;

    @FXML
    private TableColumn<Dish, String> category;
    @FXML
    private TextField selectText;
    @FXML
    private Button ensure10;
    @FXML
    private TextField selectId;
    @FXML
    private TextField selectName;
    @FXML
    private TextField selectPrice;
    @FXML
    private TextField selectCategory;

    private static List<Dish> dishes = null;
    @FXML
    void ensure(ActionEvent event) {
        // 历史订餐记录
        Iterator<Dish> iterator = dishes.iterator();
        while (iterator.hasNext()){
            Dish next = iterator.next();
            String sql = "update dish set price = ? , name = ? , category = ? where id = ?";
            JdbcCrud.update(sql,next.getPrice(),next.getName(),next.getCategory(),next.getId());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "修改成功");
        alert.show();

    }

    // public static List<Dish> getDishes() {
    //     return dishes;
    // }
    //
    // public static void setDishes(List<Dish> dishes) {
    //     Admin.dishes = dishes;
    // }

    /**
     * @Description 获取数据库信息，填充进表格
     * @author oolong
     * @date  15:37
     * @since version-1.0
     * @return
     */


    public void iniList(){

        // 获取数据库信息,添加表格
         dishes = DishServer.checkAll();
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

    // private static Dish dish;
    //
    // public static Dish getDish() {
    //     return dish;
    // }
    //
    // public void setDish(Dish dish) {
    //     Admin.dish = dish;
    // }

    /**
     * @Description 在表格上进行编辑，表格数据进行同步更新
     * @author oolong
     * @date  15:37
     * @since version-1.0
     * @return
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 提示
        selectText.setPromptText("查询条件");
        selectId.setPromptText("id");
        selectName.setPromptText("name");
        selectPrice.setPromptText("price");
        selectCategory.setPromptText("category");

        iniList();


        // 表格允许编辑
        tableview.setEditable(true);

        // 设置可编辑列的相关属性
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        category.setCellFactory(TextFieldTableCell.forTableColumn());

        name.setOnEditCommit((TableColumn.CellEditEvent<Dish,String> tc) ->{
            // 同步更新
            tc.getTableView().getItems().get(
                    tc.getTablePosition().getRow()
            ).setName(tc.getNewValue());

        });

        price.setOnEditCommit((TableColumn.CellEditEvent<Dish,Integer> tc) ->{
            // 同步更新
            tc.getTableView().getItems().get(
                    tc.getTablePosition().getRow()
            ).setPrice(tc.getNewValue());

        });
        category.setOnEditCommit((TableColumn.CellEditEvent<Dish,String> tc) ->{
            // 同步更新
            tc.getTableView().getItems().get(
                    tc.getTablePosition().getRow()
            ).setCategory(tc.getNewValue());

        });

        // 设置多选
        tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }

    /**
     * @Description 回到登录界面
     * @author oolong
     * @date  15:38
     * @since version-1.0
     * @return
     */

    public void cancel(ActionEvent actionEvent) {
        EnterInto.enter(cancel,new LogMain());


    }


    public void pricesum(ActionEvent actionEvent) {
    }


    public void selectText(ActionEvent actionEvent) {
    }


    /**
     * @Description 将表格上修改的数据传到数据库中进行实时更新
     * @author oolong
     * @date  15:39
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

        // 输入完清空文本框数据
        selectText.clear();
    }

    /**
     * @Description 添加数据进入数据库
     * @author oolong
     * @date  16:16
     * @since version-1.0
     * @return
     */

    public void add(ActionEvent actionEvent) {

        // Integer.parseInt()
        String nameText = selectName.getText();
        int price = Integer.parseInt(selectPrice.getText());
        // String price = selectPrice.getText();
        String categoryText = selectCategory.getText();

        String sql = "INSERT INTO dish(price,NAME,category) VALUES(?,?,?)";
        JdbcCrud.update(sql,price,nameText,categoryText);

        // 输入完清空文本框数据
        selectName.clear();
        selectPrice.clear();
        selectCategory.clear();


        iniList();
    }

    /**
     * @Description 删除指定id数据
     * @author oolong
     * @date  16:15
     * @since version-1.0
     * @return
     */

    public void delete1(ActionEvent actionEvent) {
        int id = Integer.parseInt(selectId.getText());

        String sql = "delete from dish where id = ?";
        JdbcCrud.update(sql,id);
        iniList();

        // 输入完清空文本框数据
        selectId.clear();
    }

    public void selectId(ActionEvent actionEvent) {
    }
    public void selectName(ActionEvent actionEvent) {
    }
    public void selectPrice(ActionEvent actionEvent) {
    }
    public void selectCategory(ActionEvent actionEvent) {
    }
}
