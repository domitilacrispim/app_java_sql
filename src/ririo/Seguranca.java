/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ririo;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author domitila
 */
public class Seguranca {
    public static Connection seguranca(String user, String senha) throws ClassNotFoundException{
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/juihui", user, senha);
            return con;
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
            return null;
        }
    }
}
