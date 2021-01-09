package com.oolong.model.bean;

/**
 * @author oolong
 */
public class User {
    private String accountNum;
    private String password;
    private String identity;
    private String encrypted;

    public User() {
    }

    public User(String accountNum, String password, String identity,String encrypted) {
        this.accountNum = accountNum;
        this.password = password;
        this.identity = identity;
        this.encrypted = encrypted;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountNum=" + accountNum +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
