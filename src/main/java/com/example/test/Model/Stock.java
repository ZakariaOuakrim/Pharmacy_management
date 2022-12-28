package com.example.test.Model;

import java.sql.Date;

public class Stock {
    private Medicine medicine;
    private int quantity;
    private Date date;

    public Stock(Medicine medicine, int quantity, Date date) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.date = date;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
