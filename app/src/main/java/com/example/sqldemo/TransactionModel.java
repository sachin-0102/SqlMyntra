package com.example.sqldemo;

import androidx.annotation.NonNull;

public class TransactionModel {
    private String itemName;
    private int id;
    private int amount;
    private String date;

    public TransactionModel(String itemName, int id, int amount , String date) {
        this.itemName = itemName;
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public TransactionModel() {
    }

    @NonNull
    @Override
    public String toString() {
        return "TransactionModel{"+
                "id=" + id+
                ", name='"+itemName+'\''+
                ", amount="+amount+
                ", date="+date+"}";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
