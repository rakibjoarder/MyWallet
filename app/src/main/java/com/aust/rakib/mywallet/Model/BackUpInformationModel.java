package com.aust.rakib.mywallet.Model;

/**
 * Created by Personal on 7/5/2017.
 */

public class BackUpInformationModel {
    int id;
    String fromDate;
    String toDate;
    String key;
    int total_income;
    int total_expense;
    int total;

    public BackUpInformationModel(String fromDate, String toDate, String key, int total_income, int total_expense, int total) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.key = key;
        this.total_income = total_income;
        this.total_expense = total_expense;
        this.total = total;
    }

    public BackUpInformationModel(int id, String fromDate, String toDate, String key) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.key = key;
    }

    public BackUpInformationModel(String fromDate, String toDate, String key) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTotal_income() {
        return total_income;
    }

    public void setTotal_income(int total_income) {
        this.total_income = total_income;
    }

    public int getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(int total_expense) {
        this.total_expense = total_expense;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
