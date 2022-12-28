package com.example.test.Controller;

import com.example.test.Dao.medicineManager;
import com.example.test.Model.Medicine;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @FXML private FlowPane Flow_Pane;

    public void generate() throws SQLException {
        ObservableList<Medicine> list = medicineManager.MedicineList();
        ImageView imageView ;
        Image image=null;
        for(int i =0;i<list.size();i++){
            imageView  = new ImageView();
            File file = new File(medicineManager.getImage(list.get(i).getMedicineId()));
            image = new Image(file.toURI().toString(),101,128,false,true);
            imageView.setImage(image);
            Flow_Pane.getChildren().add(imageView);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}