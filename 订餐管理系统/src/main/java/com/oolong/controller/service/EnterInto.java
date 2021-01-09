package com.oolong.controller.service;

import com.oolong.model.bean.Dish;
import com.oolong.view.Main.FtpasswordMain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author oolong
 */
public class EnterInto {
    public static void enter(Button button , Application app){
        // 进入客户端界面
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) button.getScene().getWindow();
            //窗口隐藏
            primaryStage.hide();
            //加载注册窗口
            try {
                app.start(primaryStage); // 将此行Reg()换为客户端的界面
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

//     public static void enterModify(Button button , Application app, Dish dish){
//         // 进入客户端界面
//         Platform.runLater(() -> {
//             //获取按钮所在的窗口
//             Stage primaryStage = (Stage) button.getScene().getWindow();
//             //窗口隐藏
//             primaryStage.hide();
//             //加载注册窗口
//             try {
//                 app.start(primaryStage); // 将此行Reg()换为客户端的界面
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
//     }
}
