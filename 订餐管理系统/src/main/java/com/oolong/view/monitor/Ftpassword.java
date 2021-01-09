package com.oolong.view.monitor;

import com.oolong.controller.service.EnterInto;
import com.oolong.model.bean.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.oolong.view.Main.LogMain;
import com.oolong.view.utils.JdbcCrud;
import org.junit.Test;


public class Ftpassword {
    @FXML
    private TextField account3;
    @FXML
    private TextField encrypted3;
    @FXML
    private Button cancel;
    @FXML
    private Button ensure;

    /**
     * @Description 对输入账号和密保进行验证，正确显示账号密码，错误弹出警告
     * @author oolong
     * @date  15:44
     * @since version-1.0
     * @return
     */

    public void ensure(ActionEvent actionEvent) {
        String accountText = account3.getText();
        String encryptedText = encrypted3.getText();
        String select = "select accountNum,password, encrypted from account where accountNum = ?";

        User user = JdbcCrud.getInstance(User.class, select, accountText);

        if(user.getAccountNum() != null  ){
            if(encryptedText.equals(user.getEncrypted())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION , "密码是：" + user.getPassword());
                alert.show();

                EnterInto.enter(ensure,new LogMain());
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING , "密保错误!");
                alert.show();
                // 清空文本框中的数据
                encrypted3.setText("");
            }



        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING , "账户错误");
            alert.show();
            // 清空文本框中的数据
            encrypted3.setText("");
        }




    }
/**
 * @Description 回到登录界面
 * @author oolong
 * @date  15:44
 * @since version-1.0
 * @return
 */

    public void cancel(ActionEvent actionEvent) {

        EnterInto.enter(cancel,new LogMain());

    }

    public void account3(ActionEvent actionEvent) {
    }

    public void encrypted3(ActionEvent actionEvent) {
    }
}
