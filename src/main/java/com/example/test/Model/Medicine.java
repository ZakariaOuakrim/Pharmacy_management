package com.example.test.Model;

import java.sql.Date;

public class Medicine {
  private String medicineId ;
  private String company_name ;
  private String medicine_Name ;
  private String type_product;
  private  Double price ;
  private String Description;
  private String Image;

  public Medicine(String medecineId, String brand,  String medicine_Name,String type, Double price,String Image,String Description) {
    this.medicineId = medecineId;
    this.company_name = brand;
    this.medicine_Name = medicine_Name;
    this.type_product = type;
    this.price = price;
    this.Description=Description;
    this.Image = "";
  }


  public String getMedicineId() {
    return medicineId;
  }

  public void setMedicineId(String medecinId) {
    this.medicineId = medecinId;
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String brand) {
    this.company_name = brand;
  }

  public String getMedicine_Name() {
    return medicine_Name;
  }

  public void setMedicine_Name(String productName) {
    this.medicine_Name = productName;
  }

  public String getType() {
    return type_product;
  }

  public void setType(String type) {
    this.type_product = type;
  }

  public String getDescription() {return Description;}

  public void setDescription(String description) {Description = description;}

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImage() {
    return Image;
  }

  public void setImage(String image) {
    Image = image;
  }
}
