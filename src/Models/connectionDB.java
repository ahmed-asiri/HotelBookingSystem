package Models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ia7ma
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
public class connectionDB {
    
    
    public Connection setConnection(){
        Connection conn = null;
        try  {
        String driverName = "oracle.jdbc.driver.OracleDriver";
        Class.forName(driverName);
        String serverName = "localhost";
        String serverPort = "1522";
        String sid = "AHMED";
        String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + "/" + sid;
        String username = "cpit251";
        String password = "cpit251";
        conn = DriverManager.getConnection(url, username, password);

        }catch (ClassNotFoundException e){
                System.out.println("could not found the db driver " + e.getMessage());
        }catch (SQLException e) {
                System.out.println("Could not connect to database " + e.getMessage());
            }
        return conn;
    }
    
    public ResultSet select(String sql){
            // get one row of the query or multiple rows, and return ResultSet.
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }catch(Exception e) {
            System.out.println(e );
        }
        return null;
        
    }
        
        
        
    public boolean insert(String sql) {
        // insert into the Database the tuple, and if it's exist return false.
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            boolean rs = st.execute(sql);
            if(rs){
                return true;
            }else{
                return false;
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void update(String sql) {
        // update existing tuple in the DB.
        try {
            Connection con = setConnection();
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        }catch(Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void delete(String sql){
        // deleting row from the database
        Connection con = setConnection();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(connectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
