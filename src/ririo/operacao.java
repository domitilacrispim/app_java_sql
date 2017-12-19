/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ririo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author domitila
 */
public class operacao {
    public String Minguado (String tabela,Connection con) throws ClassNotFoundException{
        try {
             String consulta = "Select * from " + tabela;
             PreparedStatement estado;
             estado = con.prepareStatement(consulta);
             ResultSet rs =   estado.executeQuery(consulta);
            String result = new String();
            while (rs.next()){
                System.out.print("Column 1 returned ");
               System.out.println(rs.getString(1));
            }
           return result;
        }
        catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
            return null;
        }
    }
}


  

