package com.oolong.view.monitor;


import com.oolong.model.bean.RecordBean;
import com.oolong.view.utils.JdbcCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author oolong
 */
public class Record implements Initializable {
    @FXML
    private TableView tableview123;
    @FXML
    private TableColumn<RecordBean, Integer> price;

    @FXML
    private TableColumn<RecordBean, String> account;

    @FXML
    private TableColumn<RecordBean, Integer> id;

    /**
     * @Description 将记录数据库中的数据输入到表格中进行查看
     * @author oolong
     * @date  15:46
     * @since version-1.0
     * @return
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "select * from record";
        List<RecordBean> forList = JdbcCrud.getForList(RecordBean.class, sql);
        ObservableList<RecordBean> recordObservableList = FXCollections.observableArrayList();
        // 添加数据
        recordObservableList.addAll(forList);
        // 绑定表格
        id.setCellValueFactory(new PropertyValueFactory("id"));
        account.setCellValueFactory(new PropertyValueFactory("account"));
        price.setCellValueFactory(new PropertyValueFactory("price"));

        // 显示数据到表格
        tableview123.setItems(recordObservableList);
    }
}
