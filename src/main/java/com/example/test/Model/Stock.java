package com.example.test.Model;

import java.sql.Date;
import java.time.LocalDate;

public class Stock {
    private String medicine;
    private int quantity;
    private LocalDate date;

    public Stock(String medicine, int quantity, LocalDate date) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
