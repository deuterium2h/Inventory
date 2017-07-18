/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author PC-1
 */
import java.sql.*;
import javax.swing.*;

public class javaconnect {
    Connection conn = null;
    public static Connection ConnecrDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory", "root", "berto");
            System.out.println("Connected to Database!");
            return conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
