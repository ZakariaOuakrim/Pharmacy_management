package com.example.test.Controller;

import com.example.test.Dao.medicineManager;
import com.example.test.Model.Medicine;
import com.example.test.Model.MedicineCommande;
import com.example.test.utiles.Dbutils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.security.cert.Extension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyPermission;
import java.util.ResourceBundle;

public class MedicineControler  implements Initializable {
    @FXML private TextField medicine_id;
    @FXML
    private TextField company_name;
    @FXML private TextField medicine_name;
    @FXML private ChoiceBox<String> medicine_type;
    @FXML private TextField price;
    @FXML private TextArea desciption_txt;
    @FXML private TextField searchtxt;
    @FXML private AnchorPane main_pane;

    @FXML private TableView<Medicine> id_table_medicine;
    @FXML private TableColumn<Medicine,String> table_medicine_id;
    @FXML private TableColumn<Medicine,String> table_medicine_type;
    @FXML private TableColumn<Medicine,String> table_medicine_company;
    @FXML private TableColumn<Medicine,String> table_medicine_descr;
    @FXML private TableColumn<Medicine, Double> table_medicine_price;
    @FXML private TableColumn<Medicine,String> table_medicine_name;
    @FXML private ImageView medicineImage;

    private final String[] typesOfMedicines = {"Liquid","Tablet","Capsules","Drops","Inhalers","Injections"};
    private String image_path ;

    public void addbtn() throws SQLException {
        Medicine medicine;
        if ( !medicine_id.getText().trim().equals("") || !company_name.getText().trim().equals("") || !medicine_name.getText().trim().equals("")
        || !price.getText().trim().equals("")) {
            if(!medicineManager.CheckIfIdExist(medicine_id.getText())){
                medicine = new Medicine(medicine_id.getText(),company_name.getText(),
                        medicine_name.getText(),medicine_type.getValue(),Double.valueOf(price.getText()),"image_path",desciption_txt.getText());
                medicine.setImage(image_path);
                Boolean test = medicineManager.insert(medicine);
                if(test){
                    System.out.println("inserted");
                }
                showList();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An error has occurred");
                alert.setContentText("This medicine Id already exist in the system");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid inputs");
            alert.setContentText("Please fill the inputs");
            alert.showAndWait();
        }

    }


    public void clearbtn(){
        medicine_id.setText("");
        company_name.setText("");
        medicine_type.getSelectionModel().clearSelection();
        price.setText("");
        desciption_txt.setText("");
        medicine_name.setText("");
    }

    public void importbtn(){
        System.out.println("import btn clicked");
        FileChooser open = new FileChooser();
        open.setTitle("Import Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File","jpg","*png"));
        File file = open.showOpenDialog(main_pane.getScene().getWindow());
        Image image = null;
        if(file !=null){
             image = new Image(file.toURI().toString(),101,128,false,true);
             medicineImage.setImage(image);
            image_path = file.getAbsolutePath();
        }
    }
    public void deletebtn() throws SQLException {
        String id_remove = medicine_id.getText();
        if(!id_remove.trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Sure you wan't to remove the medicine with the id "+id_remove+" ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.YES){
                medicineManager.delete(id_remove);
                clearbtn();
                showList();
            }
        }
    }

    public void updatebtn() throws SQLException {
        Medicine medicine;
        String id_update = medicine_id.getText();
        if(!id_update.trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Sureyou wan't to remove the medicine with the id "+id_update+" ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.YES){
                medicine = new Medicine(id_update,company_name.getText(),medicine_name.getText(),medicine_type.getValue(),Double.parseDouble(price.getText()),"",desciption_txt.getText());
                medicineManager.update( medicine);
                clearbtn();
                showList();
            }

        }

    }

    public void searchbtn() throws SQLException {
        String id = searchtxt.getText();
        if(!id.trim().equals("")){
            if(medicineManager.CheckIfIdExist(id)){
                ObservableList<Medicine>list=medicineManager.Search(id);
                id_table_medicine.setItems(list);
            }
        }else{
            showList();
        }

    }

    public void showList() throws SQLException {
        ObservableList<Medicine>list=medicineManager.MedicineList();
        table_medicine_id.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicineId"));//propertyvaluefactory takes from the class Medicine
        table_medicine_name.setCellValueFactory(new PropertyValueFactory<>("medicine_Name"));
        table_medicine_type.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getType()));
        table_medicine_company.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        table_medicine_descr.setCellValueFactory(new PropertyValueFactory<>("Description"));
        table_medicine_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        id_table_medicine.setItems(list);
    }
    public void MouseClikingTable() throws SQLException {
        Medicine medicine = id_table_medicine.getSelectionModel().getSelectedItem();
        medicine_id.setText(medicine.getMedicineId());
        company_name.setText(medicine.getCompany_name());
        medicine_name.setText(medicine.getMedicine_Name());
        medicine_type.setValue(medicine.getType());
        desciption_txt.setText(medicine.getDescription());
        price.setText(String.valueOf(medicine.getPrice()));
        String image_path=medicineManager.getImage(medicine_id.getText());
        if(image_path!=null){
            File file =  new File(image_path);
            Image image = new Image(file.toURI().toString(),101,128,false,true);
            medicineImage.setImage(image);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showList();
            medicine_type.getItems().addAll(typesOfMedicines);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
