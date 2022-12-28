package com.example.test.Controller;

import com.example.test.utiles.Dbutils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientController  implements Initializable {

    @FXML
    private TextField nom;
    @FXML private TextField id;
    @FXML private TextField adress;
    @FXML private DatePicker date;
    @FXML private Label confirmation;





    public Connection conn = Dbutils.getConnection();

    @FXML private void nvclient(ActionEvent event) throws IOException {
        try{
            ResultSet resultat = null;
            Statement instruction = conn.createStatement();
            String requet = "INSERT INTO custumer"
                    + " values("+id.getText()+",'"+nom.getText()+"','"+adress.getText()+"',to_date('"+date.getValue()+"','yyyy-mm-dd'))";
            resultat = instruction.executeQuery(requet);
            confirmation.setText("New Client Inserted");

        }
        catch(SQLException e){
            e.printStackTrace();
        }


    }

    @FXML private void btnsuppr(ActionEvent event) throws IOException {
        try{
            ResultSet resultat = null;
            Statement instruction = conn.createStatement();
            String requet = "delete from custumer where nom='"+nom.getText()+"'";
            resultat = instruction.executeQuery(requet);
            confirmation.setText("CLIENT Deleted");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
//    private void btnupdate(ActionEvent event) throws IOException {
//        try{
//            ResultSet resultat = null;
//            Statement instruction = conn.createStatement();
//            String requet = "delete from custumer where nom='"+nom.getText()+"'";
//            resultat = instruction.executeQuery(requet);
//            confirmation.setText("CLIENT SUPPERIMER");
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//
//    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
