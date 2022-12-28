package com.example.test.utiles;

import java.sql.*;

public class Dbutils {
    //	private static final String USERNAME = "root";
//	private static final String PASSWORD = "";
//	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/grh";
    private static Connection cnx=null;
    private static final String username="root";
    private static final String password="";
    private static final String CONN_STRING ="jdbc:mysql://localhost:3306/pharmacy";

    public static Connection getConnection(){
        try{
            cnx = DriverManager.getConnection(CONN_STRING,username,password);
        }catch (Exception e){
            System.out.println("Erreur "+e);
        }finally {
            return cnx;
        }
    }
    public static boolean authentification(String username,String password) throws SQLException{
        PreparedStatement stat=null;
        ResultSet res=null;
        Connection con=null;
        String sql = "SELECT * from users WHERE username= ? and password = ?";
        try {
            con = Dbutils.getConnection();
            stat = con.prepareStatement(sql);
            stat.setString(1, username);
            stat.setString(2, password);
            res = stat.executeQuery();
            if(res.next())
                return res.getString("username").equals(username) && res.getString("password").equals(password);
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally{
            res.close();
            stat.close();
            con.close();
        }
    }
    public static void chanePassword(String username,String password) throws SQLException{
        PreparedStatement stat=null;
        Connection con=null;
        String sql = "UPDATE login SET password=? WHERE username= ?";
        try {
            con = Dbutils.getConnection();
            stat = con.prepareStatement(sql);
            stat.setString(1, password);
            stat.setString(2, username);
            int affected = stat.executeUpdate();
            if(affected==1)
                System.out.println("password changed");
            else
                System.err.println("password changing failed");
        } catch (Exception e) {
            System.err.println("password changing failed");
        }
        finally{
            stat.close();
            con.close();
        }
    }}