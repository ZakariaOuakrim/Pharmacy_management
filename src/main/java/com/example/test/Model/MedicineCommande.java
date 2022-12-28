package com.example.test.Model;

public class MedicineCommande {

    private String MedecinID ;
    private String productName ;
    private  Double price ;
    private int qte ;

    public MedicineCommande(String medecinId, String productName, Double price, int qte) {

        this.MedecinID = medecinId;
        this.productName = productName;
        this.price = price;
        this.qte = qte;
    }

    public String getMedecinId() {
        return MedecinID;
    }

    public void setMedecinId(String medecinId) {
        this.MedecinID = medecinId;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "MedicineCommande{" +
                "MedecinID='" + MedecinID + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", qte=" + qte +
                '}';
    }
}
