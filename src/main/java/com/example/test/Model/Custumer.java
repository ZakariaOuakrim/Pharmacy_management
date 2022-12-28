package com.example.test.Model;

import java.util.Date;

public class Custumer {
private Integer custummer_id;
private Integer medecine_id;
private  Double quantite ;
private  double price;
private Date date;

    public Custumer(Integer custummer_id, Integer medecine_id, Double quantite, double price, Date date) {
        this.custummer_id = custummer_id;
        this.medecine_id = medecine_id;
        this.quantite = quantite;
        this.price = price;
        this.date = date;
    }

    public Integer getCustummer_id() {
        return custummer_id;
    }

    public void setCustummer_id(Integer custummer_id) {
        this.custummer_id = custummer_id;
    }

    public Integer getMedecine_id() {
        return medecine_id;
    }

    public void setMedecine_id(Integer medecine_id) {
        this.medecine_id = medecine_id;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
