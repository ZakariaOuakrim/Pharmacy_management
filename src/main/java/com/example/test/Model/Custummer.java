package com.example.test.Model;

import javafx.beans.property.SimpleStringProperty;

public class Custummer {
    public class info {
        public SimpleStringProperty id= new SimpleStringProperty();
        public SimpleStringProperty nom= new SimpleStringProperty();
        public SimpleStringProperty date= new SimpleStringProperty();
        public SimpleStringProperty adress= new SimpleStringProperty();


        public String getId() {
            return id.get();
        }

        public String getNom() {
            return nom.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getAdress() {
            return adress.get();
        }
    }
}
