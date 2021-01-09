package com.oolong.model.bean;

/**
 * @author oolong
 */
public class RecordBean {
    private int id;
    private String account;
    private int price;

    public RecordBean() {
    }

    public RecordBean(int id, String account, int price) {
        this.id = id;
        this.account = account;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", price=" + price +
                '}';
    }
}
