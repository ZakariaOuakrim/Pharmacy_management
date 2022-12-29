package com.example.test.Dao;

import com.example.test.Model.Medicine;
import com.example.test.utiles.Dbutils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class medicineManager {
    private static Connection con ;

        public static boolean insert(Medicine medicine) throws SQLException {
            String query = "INSERT INTO medicine (medicine_id,company_name,medicine_name,type_product,price,Image,Description) Values (?,?,?,?,?,?,?)";
            con = Dbutils.getConnection();
            PreparedStatement stat = con.prepareStatement(query);

            stat.setString(1,String.valueOf(medicine.getMedicineId()));
            stat.setString(2,medicine.getCompany_name());
            stat.setString(3,medicine.getMedicine_Name());
            stat.setString(4,medicine.getType());
            stat.setString(5,String.valueOf(medicine.getPrice()));
            stat.setString(6,medicine.getImage());
            stat.setString(7,medicine.getDescription());

            int a = stat.executeUpdate();
            con.close();
            if(a ==1){
                return true;
            }else {
                return false;
            }
        }
        public static boolean CheckIfIdExist(String id_Searched_for) throws SQLException {
            String id;
            con = Dbutils.getConnection();
            String query = "select medicine_id from medicine";
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery(query);

            while(result.next()){
                id = result.getString("medicine_id");
                if(id.equals(id_Searched_for)){
                    con.close();
                    return true;
                }
            }
            con.close();
            return false;
        }

    public static void delete(String id) throws SQLException {
        con = Dbutils.getConnection();
        String query = "DELETE FROM MEDICINE WHERE medicine_id = ?";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,id);
        stat.executeUpdate();
        con.close();
    }
    public static void update(Medicine medicine) throws SQLException {
        con = Dbutils.getConnection();
        String query = "UPDATE medicine set company_name = ?,medicine_name=?,type_product=?,price=?,Description=? where medicine_id =? ";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,medicine.getCompany_name());
        stat.setString(2,medicine.getMedicine_Name());
        stat.setString(3,medicine.getType());
        stat.setString(4,String.valueOf(medicine.getPrice()));
        stat.setString(5,medicine.getDescription());
        stat.setString(6,medicine.getMedicineId());
        stat.executeUpdate();
        con.close();
    }


    public static ObservableList<Medicine> Search(String id) throws SQLException {
        con = Dbutils.getConnection();
        Medicine medicine;
        String query ="Select * from medicine where medicine_id = '"+id+"'";

        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        rs.next();
        ObservableList<Medicine> listMedicines = FXCollections.observableArrayList();
        medicine = new Medicine(id,rs.getString("company_name"),rs.getString("medicine_name"),
                rs.getString("type_product"),
                rs.getDouble("Price"), rs.getString("Image"),rs.getString("Description"));
        listMedicines.add(medicine);
        con.close();
        return listMedicines;
    }
    public static String getImage(String id) throws SQLException {
        con = Dbutils.getConnection();
        String query = "select Image from medicine where medicine_id='"+id+"'";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("Image");
        }
    public static ObservableList<Medicine> MedicineList() throws SQLException {
        Medicine medicine;
        String sql = "SELECT * FROM medicine";
        ObservableList<Medicine> listMedicines = FXCollections.observableArrayList();
        Connection con = Dbutils.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            medicine = new Medicine(rs.getString("medicine_id"),rs.getString("company_name")
                    ,rs.getString("medicine_name"),rs.getString("type_product"),
                     rs.getDouble("Price"), " ",rs.getString("Description"));
            listMedicines.add(medicine);
        }
        con.close();
        return listMedicines;
    }


}
