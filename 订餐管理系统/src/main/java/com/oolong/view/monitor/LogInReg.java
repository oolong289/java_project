package com.oolong.view.monitor;

import com.oolong.controller.service.EnterInto;
import com.oolong.model.bean.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.oolong.view.Main.AdminMain;
import com.oolong.view.Main.CustomersMain;
import com.oolong.view.Main.FtpasswordMain;
import com.oolong.view.Main.RegisterMain;
import com.oolong.view.utils.JdbcCrud;

import java.net.URL;
import java.util.ResourceBundle;


public class LogInReg implements Initializable {

    @FXML
    private Button Register1;

    @FXML
    private Button Login;

    @FXML
    private TextField account2;

    @FXML
    private PasswordField password2;
    @FXML
    private MenuItem close;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuItem about;


public static String account3 = null;

    public static String getAccount3() {
        return account3;
    }

    public void register(ActionEvent actionEvent) {
        EnterInto.enter(Register1,new RegisterMain());

    }

    public void login(ActionEvent actionEvent) {



        // 得到输入的账户和密码
        String accountText = account2.getText();
        String passwordText = password2.getText();

        account3 = accountText;

        if(!accountText.equals(null) && accountText.length()>0 && !passwordText.equals(null)){

            String select = "select accountNum , password , identity from account where accountNum = ?";

            // 查看数据库中有无相同的账户和密码
            User user = JdbcCrud.getInstance(User.class, select, accountText);

            // 判断数据库中账户是否为空，密码是否正确
            if((user.getAccountNum() != null) && (passwordText.equals(user.getPassword()))){
                // System.out.println("登录成功！");
                // 如果不为空，则对身份进行判断。进入不同的服务界面
                if("管理员".equals(user.getIdentity())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION , "登录成功");
                    alert.show();

                    EnterInto.enter(Login,new AdminMain());
                }else{

                    EnterInto.enter(Login,new CustomersMain());
                }
            }else{
                // 密码或账户输入错误，进行弹窗提示。
                Alert alert = new Alert(Alert.AlertType.WARNING , "密码或账户输入错误!");
                alert.show();
                // 清空文本框
                account2.setText("");
                password2.setText("");
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING , "账户或密码为空！");
            alert.show();
        }
        // }else{
        //     System.out.println("密码或账号为空!");
        //     // 密码或账号为空
    }

    public void account2(ActionEvent actionEvent) {

    }

    public void password2(ActionEvent actionEvent) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 提示
        account2.setPromptText("account");
        password2.setPromptText("password");
        // 客户测试
        password2.setText("7363");
        account2.setText("7363");

        // 管理员测试

        // account2.setText("119");
        // password2.setText("gakki");
    }


    public void ftpassword(ActionEvent actionEvent) {
        // 进入忘记密码
        EnterInto.enter(Login,new FtpasswordMain());
    }
}
