package com.example.graphic;

import android.widget.TextView;

public class Order {

    private String productName;
    private int amount;
    private String text;
    private String clientName;
    private String clientPhone;

    public Order(String productName, int amount, String text, String clientName, String clientPhone) {
        this.productName = productName;
        this.amount = amount;
        this.text = text;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
    }
    public Order(){

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
}
