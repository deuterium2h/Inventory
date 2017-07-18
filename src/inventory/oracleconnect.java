/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author AICS_TAYTAY
 */
import java.sql.*;

public class oracleconnect {
    public static void main(String[] args) {
        Connection conn = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@PC-41:1521:databank", "admin", "berto");
            System.out.println("Connected to Database!");
        }catch(ClassNotFoundException e){
            System.err.println("Could not found database Driver");
        }catch(SQLException e){
            System.err.println("Connection to database failed..");
        }
        
    }
}
