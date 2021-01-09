package com.oolong.controller.service;


import com.oolong.model.bean.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.List;


public class TableServe {

    static List<Dish> list = new ArrayList();
    static int PRICE = 0;

    public static int getPRICE() {
        return PRICE;
    }

    public static List<Dish> getList() {
        return list;
    }

    public static void Monitor(TableView tableview, TableView tableview2, String[] strings, Label sumPrice, TableColumn... args) {
        // 表格监听
        tableview.setRowFactory(new Callback<TableView<Dish>, TableRow<Dish>>() {

            @Override
            public TableRow<Dish> call(TableView<Dish> dishTableView) {
                TableRow<Dish> row = new TableRow<>();

                row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getClickCount() == 2 && !row.isEmpty()) {

                            // 包装类
                            ObservableList<Dish> dishList = FXCollections.observableArrayList();
                            // 添加数据

                            Dish item = row.getItem();
                            PRICE += item.getPrice();


                            sumPrice.setText(String.valueOf(PRICE));
                            dishList.clear();
                            list.add(item);
                            dishList.addAll(list);

                            for (int i = 0; i < args.length; i++) {
                                args[i].setCellValueFactory(new PropertyValueFactory(strings[i]));
                            }
                            // 显示数据到表格
                            tableview2.setItems(dishList);




                        }
                    }
                });

                return row;

            }
        });
    }

public static  void tableViewMonitor(TableView<Dish> tableView,Label sumPrice,String[] strings,TableColumn ...args){
    // 表格监听
    tableView.setRowFactory(new Callback<TableView<Dish>, TableRow<Dish>>() {

        @Override
        public TableRow<Dish> call(TableView<Dish> dishTableView) {
            TableRow<Dish> row = new TableRow<>();

            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mouseEvent.getClickCount() == 2 && !row.isEmpty()){
                        // 包装类
                        ObservableList<Dish> dishList = FXCollections.observableArrayList();
                        // 重新计算总价
                        PRICE -= row.getItem().getPrice();
                        // 重新刷新总价
                        sumPrice.setText(String.valueOf(PRICE));
                        // 将选中的行隐藏起来
                        row.setVisible(false);
                        // 移除集合中的要删除的数据
                        list.remove(row.getIndex());

                        // 清空数据
                        dishList.clear();
                        // 刷新表格中的数据
                        dishList.addAll(list);

                        for (int i = 0; i < args.length; i++) {
                            args[i].setCellValueFactory(new PropertyValueFactory(strings[i]));
                        }
                        // 显示数据到表格
                        tableView.setItems(dishList);



                    }
                }
            });

            return row;
        }
    });
}
}
