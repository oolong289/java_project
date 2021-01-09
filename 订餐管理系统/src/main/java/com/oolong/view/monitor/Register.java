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

public class Register {
    @FXML
    private TextField account;
    @FXML
    private TextField password;
    @FXML
    private Button cancel;
    @FXML
    private TextField encrypted;
    @FXML
    private Button ensure;
    @FXML
    private TextField password20;
    public void password(ActionEvent actionEvent) {

    }

    public void account(ActionEvent actionEvent) {
    }

    /**
     * @Description 在数据库中进行查找账号，如果不存在，则将输入的账号密码密保添加进数据库，如果存在，则弹窗进行提示
     * @author oolong
     * @date  15:47
     * @since version-1.0
     * @return
     */

    public void ensure(ActionEvent actionEvent) {
        String accountText = account.getText();
        String passwordText = password.getText();
        String encryptedText = encrypted.getText();
        String ensurePassword = password20.getText();
        String select = "select accountNum from account where accountNum = ?";
        String update = "insert into account(accountNum,password,encrypted) values(?,?,?)";

        User user = JdbcCrud.getInstance(User.class, select, accountText);
        if((accountText.length()>0)){
            if(user.getAccountNum() != null && (accountText.length()>0)){
                Alert alert = new Alert(Alert.AlertType.WARNING , "此账户已经存在，请重新输入！");
                alert.show();
                // 清空文本框中的数据
                account.setText("");
                password.setText("");
                encrypted.setText("");
            }else if (! passwordText.equals(ensurePassword)){
                Alert alert = new Alert(Alert.AlertType.WARNING , "两次密码输入不一致！");
                alert.show();
            }else{
                // 将注册的账号添加到数据库中
                JdbcCrud.update(update, accountText, passwordText,encryptedText);

                EnterInto.enter(ensure,new LogMain());
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING , "输入账号有误，请重新输入！");
            alert.show();
        }

    }

    /**
     * @Description 返回登录界面
     * @author oolong
     * @date  15:53
     * @since version-1.0
     * @return
     */

    public void cancel(ActionEvent actionEvent) {


        EnterInto.enter(cancel,new LogMain());
    }

    public void encrypted(ActionEvent actionEvent) {

    }

    public void password20(ActionEvent actionEvent) {
    }
}
