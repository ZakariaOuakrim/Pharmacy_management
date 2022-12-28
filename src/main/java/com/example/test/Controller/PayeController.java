package com.example.test.Controller;


import com.example.test.Model.MedicineCommande;
import com.example.test.utiles.Dbutils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class PayeController  implements Initializable {

    @FXML
    private Button button_add;

    @FXML
    private Label text_total;

    @FXML
    private Button button_pay;

    @FXML
    private Spinner<Integer> id_spinner;

    @FXML
    private TextField custummer_name_text;

    @FXML
    private TextField id_medecine_text;

    @FXML
    private TableView<MedicineCommande> table_id;

    @FXML
    private TableColumn<MedicineCommande, String> id_medecine_table;

    @FXML
    private TableColumn<MedicineCommande, String> product_name_table;

    @FXML
    private TableColumn<MedicineCommande, Integer> qte_table;

    @FXML
    private TableColumn<MedicineCommande, Double> price_table;
    private SpinnerValueFactory spinnerValueFactory;


// initialisation de spinner

    public void  showValueSpinner(){
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20,0);
        id_spinner.setValueFactory(spinnerValueFactory);

    }


    // getter for quantiter
    private int qte;
    public  void pushQuantite(){
        qte = id_spinner.getValue();
    }

    public Connection conn = Dbutils.getConnection();



    // get medecie data ( use in the table )
    public ObservableList<MedicineCommande> getMedecineData() throws SQLException {
        ObservableList<MedicineCommande> medecinelist = FXCollections.observableArrayList();
        ResultSet resultat = null;
        Statement instruction = conn.createStatement();
        String requet = "Select * from medecine ,ItemCommandes where medecine.MEDECINEID like ItemCommandes.IDMEDECINE";
        resultat = instruction.executeQuery(requet);
        MedicineCommande medicineCommande;
        while (resultat.next()){
            medicineCommande = new MedicineCommande(resultat.getString("MEDECINEID")
                    ,resultat.getString("PRODUCTNAME"), resultat.getDouble("PRICE"),
                    resultat.getInt("QUANTITEDEMANDER"));
            medecinelist.add(medicineCommande);
        }
    return medecinelist;
    }


// function  show data in table
    public void ShowDataTable() throws SQLException {
        ObservableList<MedicineCommande> listDataCommandeMedecine = getMedecineData();
        id_medecine_table.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMedecinId()));
        product_name_table.setCellValueFactory(new PropertyValueFactory<MedicineCommande,String>("productName"));
        qte_table.setCellValueFactory(new PropertyValueFactory<MedicineCommande,Integer>("qte"));
        price_table.setCellValueFactory(new PropertyValueFactory<MedicineCommande,Double>("price"));
        table_id.setItems(listDataCommandeMedecine);
    }


    // fuction  to manipulate the labeel price
    public Double showDataLabelPriceTotale() throws SQLException {
        ResultSet resultat = null;
        double totale_price =0.0;
        Statement instruction = conn.createStatement();
        String requet = "Select sum(medecine.price*itemcommandes.quantitedemander) as total from medecine ,ItemCommandes where medecine.MEDECINEID like ItemCommandes.IDMEDECINE";
        resultat = instruction.executeQuery(requet);
        while (resultat.next()){
          totale_price =resultat.getDouble("TOTAL");
        }
      return totale_price ;
    }

    // initialisation  de prix
    public void paymentPrice() throws SQLException {
        Double tDouble = 0.0;
        tDouble =  showDataLabelPriceTotale();
        text_total.setText(tDouble.toString()+" MAD");
    }

     // function payment button (facture payment)
    public  void  paymentFinal() throws SQLException {
        Double tDouble = showDataLabelPriceTotale();
        if (custummer_name_text.getText() ==""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("problem");
            alert.setHeaderText(" Trasaction not complet , you should ad name or add medecine ");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you suer to finish the transaction ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("succes Dialog");
                alert1.setHeaderText(" Trasaction with succes ");
                alert1.showAndWait();

                Statement instruction = conn.createStatement();
                String requet = "INSERT INTO salles(custumer,price,DATE_PAYER) "
                        + " values('"+custummer_name_text.getText()+"','"+tDouble+
                        "',to_date('"+java.time.LocalDate.now() +"','yyyy-mm-dd'))";
                ResultSet resultat = instruction.executeQuery(requet);
                String sqldelete = "delete from itemcommandes";
                ResultSet resultSet =instruction.executeQuery(sqldelete);
                ShowDataTable();
                resetallcolums();
                paymentPrice();
            }


        }
    }



    // reset colum after add ; delete ; update date in table
    public void  resetcolums() throws SQLException {
        showValueSpinner();
        paymentPrice();
        id_medecine_text.setText("");
    }

// reset all of colums after pay or cancel action
    public void resetallcolums() throws SQLException {
        resetcolums();
        custummer_name_text.setText("");
    }


    // make table interactive with the mouse ( get date from table )
    public  void handlingMouse(){
        MedicineCommande medicineCommande = table_id.getSelectionModel().getSelectedItem();
        id_medecine_text.setText(""+medicineCommande.getMedecinId());
    }

    // function pour ajouter un nouveaux medecine au table
    @FXML
    private void nouveauxCommandeMedecine(ActionEvent event) throws IOException,SQLException {

            int qtestoks =10;
            Statement instruction = conn.createStatement();
            if(qte > qtestoks){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Quantite Information");
                alert.setHeaderText(null);
                alert.setContentText("The Quantite in the stoks is "+qtestoks);
                alert.showAndWait();
            }else if (qte == 0 || id_medecine_text.getText()==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The quantity and id medecine are required  is required  !");
                alert.showAndWait();
            } else {
                String sqlrequest = "Select * from medicine where medicine_id = '"+id_medecine_text.getText()+"'";
                ResultSet res = instruction.executeQuery(sqlrequest);
                if (res.next()){
                    String requet = "INSERT INTO ItemCommandes values( '"+id_medecine_text.getText()+"','"+qte+"')";
                    ResultSet resultat = instruction.executeQuery(requet);
                    ShowDataTable();
                    showDataLabelPriceTotale();
                    resetcolums();
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("The id medecine must be in the database !");
                    alert.showAndWait();
                }

            }
        }



    public void updateCommandeMedecine(ActionEvent actionEvent) throws SQLException {
        int qtestoks =10;
        Statement instruction = conn.createStatement();
        if(qte > qtestoks){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quantite Information");
            alert.setHeaderText(null);
            alert.setContentText("The Quantite in the stoks is "+qtestoks);
            alert.showAndWait();
        }else if (qte == 0 || id_medecine_text.getText()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("The quantity and id medecine are required  is required  !");
            alert.showAndWait();
        } else {
            String sqlrequest = "Select * from medecine where MEDECINEID = '"+id_medecine_text.getText()+"'";
            ResultSet res = instruction.executeQuery(sqlrequest);
            if (res.next()){
                String requet = "update ItemCommandes set quantitedemander =  '"+qte+"' where IDMEDECINE = '"+id_medecine_text.getText()+"'";
                System.out.println(requet);
                ResultSet resultat = instruction.executeQuery(requet);
                ShowDataTable();
                showDataLabelPriceTotale();
                resetcolums();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The id medecine must be in the database !");
                alert.showAndWait();
            }
        }
    }

    public void deleteCommandeMedecine(ActionEvent actionEvent) throws SQLException {
        int qtestoks =10;
        Statement instruction = conn.createStatement();
        if(qte > qtestoks){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Quantite Information");
            alert.setHeaderText(null);
            alert.setContentText("The Quantite in the stoks is "+qtestoks);
            alert.showAndWait();
        }else if (qte == 0 || id_medecine_text.getText()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("The quantity and id medecine are required  is required  !");
            alert.showAndWait();
        } else {
                String requet = "delete from  ItemCommandes  where IDMEDECINE = '"+id_medecine_text.getText()+"'";
                ResultSet resultat = instruction.executeQuery(requet);
                ShowDataTable();
                showDataLabelPriceTotale();
                resetcolums();
        }
    }

    public void canceltransaction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you suer to Delete  ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Statement instruction = conn.createStatement();
            String sqldelete = "delete from itemcommandes";
            ResultSet resultSet =instruction.executeQuery(sqldelete);
            ShowDataTable();
            resetallcolums();
            paymentPrice();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showValueSpinner();
        text_total.setText("0 MAD");
        try {
            ShowDataTable();
            paymentPrice();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
