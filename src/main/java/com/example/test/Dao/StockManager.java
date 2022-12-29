package com.example.test.Dao;

import com.example.test.Model.Stock;
import com.example.test.utiles.Dbutils;

import java.sql.*;

public class stockManager {
    private static Connection con ;
    public static boolean insert(Stock stock) throws SQLException {
        con = Dbutils.getConnection();
        String query = "INSERT INTO stock (medicine_id,quantity,Date) Values (?,?,?)";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,stock.getMedicine());
        stat.setString(2,String.valueOf(stock.getQuantity()));
        stat.setString(3,stock.getDate().toString());
        int test = stat.executeUpdate();
        con.close();
        if(test ==1){
            return true;
        }else {
            return false;
        }
    }

    public static boolean CheckIfMedicineExistsInStock(String medicine_id) throws SQLException {
        con = Dbutils.getConnection();
        String id;
        String query = "select medicine_id from stock";
        Statement stat = con.createStatement();
        ResultSet result = stat.executeQuery(query);
        while(result.next()){
            id = result.getString("medicine_id");
            if(id.equals(medicine_id)){
                con.close();
                return true;
            }
        }
        con.close();
        return false;
    }

    public static void Update(Stock stock) throws SQLException {
        con = Dbutils.getConnection();

        String query = "Update stock set Quantity = ? , Date = ? where medicine_id = ? ";
        PreparedStatement stat = con.prepareStatement(query);
        stat.setString(1,String.valueOf(stock.getQuantity()));
        stat.setString(2,stock.getDate().toString());
        stat.setString(3,stock.getMedicine());
        stat.executeUpdate();
        con.close();

    }
    public static Stock GetStock(String id) throws SQLException {
        con = Dbutils.getConnection();
        Stock stock ;
        String query = "select * from stock where medicine_id ='"+id+"'";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet result = ps.executeQuery();
        result.next();
        stock = new Stock(result.getString("medicine_id"),result.getInt("Quantity"), result.getDate("Date").toLocalDate());
        return stock;
    }


}
