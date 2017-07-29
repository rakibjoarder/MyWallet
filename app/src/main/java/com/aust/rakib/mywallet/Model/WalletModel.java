package com.aust.rakib.mywallet.Model;

/**
 * Created by Personal on 6/18/2017.
 */

public class WalletModel {
    private int id;
    private int image;
    private String date;
    private int amount;
    private String description;
    private int status;

    public WalletModel(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public WalletModel(int id, int image, String date, int amount, String description) {
        this.id = id;
        this.image = image;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public WalletModel(int image, String date, int amount, String description) {
        this.image = image;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public WalletModel(int id, int amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int getImage() {
        return image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setImage(int image) {
        this.image = image;
    }
}