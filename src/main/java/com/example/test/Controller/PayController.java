package com.example.test.Controller;

import com.example.test.utiles.Dbutils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.test.Model.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PayController implements Initializable {
    @FXML
    private TableColumn<Custumer, Integer> price_table;

    @FXML
    private ComboBox<String> type_medcine;
//
    @FXML
    private ComboBox<?> id_medcine;

//    @FXML
//    private ComboBox<?> brand_medcine;
//
//    @FXML
//    private ComboBox<?> product_medcin;

    @FXML
    private Button button_add;

    @FXML
    private Label text_total;

   // @FXML
//    private TextField text_amount;

    @FXML
    private Button button_pay;

    @FXML
    private TableView<Custumer> table_id;

    @FXML
    private TableColumn<Custumer,Integer > id_medecine_table;


    @FXML
    private TableColumn<Medicine, String> product_name_table;


    @FXML
    private TableColumn<Custumer,Integer> qte_table;
    @FXML
    private Spinner<Integer> id_spinner;
    @FXML
    private TextField custummer_name_text;

    @FXML
    private TextField id_medecine_text;


    private  SpinnerValueFactory<Integer> spinnerValueFactory;
    private int qte ;
    private double totaleP ;
    private int custumer_id;

    /// add data to table







    public ObservableList<Custumer> getDataCustumer() throws SQLException {

        Custumer custumer;
        String sql = "select * from custumer where custumer_id= '"+custumer_id+"'";
        ObservableList<Custumer> listcustummer = FXCollections.observableArrayList();
        Connection con = Dbutils.getConnection();
        PreparedStatement stat = con.prepareStatement(sql);
        System.out.println(sql);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            custumer = new Custumer(res.getInt("custummer_id"), res.getInt("medecine_id"),
                    res.getDouble("qte") ,
                    res.getDouble("price"),
                    res.getDate("DATE_ACHAT"));
            listcustummer.add(custumer);
        }
        return  listcustummer;

    }
    public void pushCustummerid() throws SQLException {
        String sql ="select custumer_id from salles;";
        Connection con = Dbutils.getConnection();
        PreparedStatement stat = con.prepareStatement(sql);
        System.out.println(sql);
        ResultSet res = stat.executeQuery();
        while(res.next()){
           custumer_id = res.getInt("custummer_id");
        }
int clientid =0;
        String sql_cek ="select custumer_id from custummer;";
        PreparedStatement statement = con.prepareStatement(sql_cek);
//        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            clientid = resultSet.getInt("custumer_id");
        }


        if (custumer_id == 0 || custumer_id == clientid) custumer_id+=0;

    }


    public void  showValueSpinner(){
        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20,0);
        id_spinner.setValueFactory(spinnerValueFactory);

    }
    public  void pushQuantite(){
    qte = id_spinner.getValue();
    }

private  ObservableList<Custumer> listDataCustummer;
    public void showListDataTable() throws SQLException {
        listDataCustummer = getDataCustumer();
        id_medecine_table.setCellValueFactory(new PropertyValueFactory<>("medecine_id"));
//        id_brand_table.setCellValueFactory(new PropertyValueFactory<>("brand"));
        product_name_table.setCellValueFactory(new PropertyValueFactory<>("product_name"));
//        type_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        qte_table.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        price_table.setCellValueFactory(new PropertyValueFactory<>("price"));

        table_id.setItems(listDataCustummer);
    }

//    public  void  addItemtype() throws SQLException {
//
//        MedecineManager.typeMedecine(this.type_medcine);
//    }
    public  void addIdMedecin () throws SQLException {
        String sql = " Select * from medecine where type '"+ type_medcine.getSelectionModel().getSelectedItem()+"'";

        Connection con = Dbutils.getConnection();
        ObservableList listData = FXCollections.observableArrayList();
        PreparedStatement stat = con.prepareStatement(sql);
        System.out.println(sql);
        ResultSet res = stat.executeQuery();
        while(res.next()){
            listData.add(res.getString("MEDECINEID"));
        }
        id_medcine.setItems(listData);
        addbrandMedecine();
        addProductName();

    }
    public  void  addtypeMedecine() throws SQLException {
        String sql = " select * from medecine where type = 'avaible' ";
        PreparedStatement stat = null;
        ResultSet res = null;

        Connection con = Dbutils.getConnection();
        ObservableList listData = FXCollections.observableArrayList();
        stat = con.prepareStatement(sql);
        res = stat.executeQuery();
        System.out.println(sql);
        while(res.next()){
            listData.add(res.getString("type"));
            System.out.println(res.getString("type"));
        }

        type_medcine.setItems(listData);
//        addIdMedecin();

    }
    public  void  addbrandMedecine() throws SQLException {
        String sql = " select * from medecine where medecineid = '"+id_medcine.getSelectionModel().getSelectedItem();
        PreparedStatement stat = null;
        ResultSet res = null;

        Connection con = Dbutils.getConnection();
        ObservableList listData = FXCollections.observableArrayList();
        stat = con.prepareStatement(sql);
        res = stat.executeQuery();
        System.out.println(sql);
        while(res.next()){
            listData.add(res.getString("brand"));
            System.out.println(res.getString("type"));
        }

        type_medcine.setItems(listData);
//        addIdMedecin();

    } public  void  addProductName() throws SQLException {
        String sql = " select * from medecine where medecineid = '"+id_medcine.getSelectionModel().getSelectedItem();
        PreparedStatement stat = null;
        ResultSet res = null;

        Connection con = Dbutils.getConnection();
        ObservableList listData = FXCollections.observableArrayList();
        stat = con.prepareStatement(sql);
        res = stat.executeQuery();
        System.out.println(sql);
        while(res.next()){
            listData.add(res.getString("PRODUCTNAME"));
            System.out.println(res.getString("PRODUCTNAME"));
        }

        addIdMedecin();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showValueSpinner();

    }


}
