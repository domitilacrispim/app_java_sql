package ririo;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Adm_supremo extends JFrame {
    //  private String user = "Adm";
    //   private String senha = "123456";
public Statement sentenca;
    public boolean Adm_supremo(String a, String b) throws ClassNotFoundException {
        //    if ( !a.equals(user) || !b.equals(senha) ) return false ; // subistirui esse if com a conexao com o banco
        final Connection con =  Seguranca.seguranca(a, b);
        PreparedStatement pst = null;
        ResultSet rs = null;
       
        if (con == null) {
            return false;
        }
        final ImageIcon imageIcon = new ImageIcon("/home/domitila/Imagens/rir.jpg");
        JOptionPane.showMessageDialog(null, "Logado com administrador. Instruções "
                + ""
                + ":\n 1. Você pode apagar, inserir e consultar."
                + "\n 2. Cuidado ao deletar! Se os campos ficarem em branco TODOS os dados serão excuídos."
                + "\n 3. Caso você tente deletar informações atreladas a mais de uma tabela a exclusão será feita em cascata. "
                + "\n 4. Na situção de um dado inserido ser inválido, você será avisado e a inserção não acontecerá."
                + "\n5. Tenha um bom dia de trabalho! :)");

        JPanel painel_princ = new JPanel(new FlowLayout()) {
            Image image = imageIcon.getImage();
            Image grayImage = image;

            {
                setOpaque(false);
            }

            public void paint(Graphics g) {
                g.drawImage(grayImage, -350, -50, this);
                super.paint(g);
            }
        };// painel principal
        JButton inser = new JButton("Inserção"); // botao de insercao  
        JButton consult = new JButton("Consulta"); // botao de consulta
        JButton dele = new JButton("Deleção"); // botao de delecao
        JButton atualiza = new JButton("Atualização"); // botao de delecao
        JTextField tabela = new JTextField(20);
        JLabel ajuda = new JLabel("Digite o nome da tabela que deseja editar:\n");
        //painel_princ.setLayout(new GridLayout(4,1));
        ajuda.setForeground(Color.white);

        painel_princ.add(ajuda);
        painel_princ.add(tabela);
        painel_princ.add(inser);
        painel_princ.add(dele);
        painel_princ.add(consult);
        painel_princ.add(atualiza);
        JFrame campo = new JFrame("Janela do Adm.");
        campo.setSize(400, 300);
        campo.setDefaultCloseOperation(EXIT_ON_CLOSE);
        campo.getContentPane().add(painel_princ);
        inser.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 insere(tabela.getText(),con );
            }
        }
        );
        atualiza.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 updata(tabela.getText(),con );
            }
        }
        );
        dele.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 deleta(tabela.getText(),con );
            }
        }
        );
        campo.setVisible(true);

        return true;
    }
 
       public void updata (String a, Connection con){
                   try { // Cria uma sentenca para atualizar o banco de dado
                       sentenca = con.createStatement();
                       sentenca.executeUpdate("Set search_path to rockinrio");
                       ResultSet rs;
                       rs = sentenca.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name ='"+a +"'");
                      //rs = sentenca.executeQuery("\\d " +a );
                      String aux = "";
                      JOptionPane.showMessageDialog(null, "Digite a seguir os dados cada um de uma vez."
                              + "Primeiro digite a condicao para o campo e na janela seguinte  o valor de atualizacao."
                              + "Caso nao deseje alterar certo campo apenas deixe a caixa em branco.");
                       int i=0, i1=0;
                        String z=null, z1="(";
                            
                       while (rs.next())
                        {                      
                            z=JOptionPane.showInputDialog(rs.getString(1));
                            
                            if( z != null){
                                if(i>0) aux= aux+" AND ";
                                i++;
                                aux= aux+ rs.getString(1) + "='"+z+"'" ;
                            }
                            if(z1!=null){
                                if(i1>0) z1= z1+" , ";
                                    z1 = z1 +rs.getString(1)+ "='"+ JOptionPane.showInputDialog(rs.getString(1)) + "'";
                                i1++;
                            }
                        }
                        String mimi="";
                        if(i!=0){
                             mimi = " where "+z;
                        }
                        if(i1!=0){
                            mimi = " set "+z1+ mimi;
                        }
                        JOptionPane.showInputDialog("Update "+a+mimi);
                        sentenca.executeUpdate("Update "+a+mimi);
                        
                   } 
                  
                    catch (SQLException se) {
                              JOptionPane.showMessageDialog(null, "Tabela invalida ou voce nao possui esse privilegio!");

                            System.out.println("Nao foi possivel executar.");
                                               se.printStackTrace();
                            System.exit(1);}
  }
    public void deleta (String a, Connection con){
                   try { // Cria uma sentenca para atualizar o banco de dado
                       sentenca = con.createStatement();
                       sentenca.executeUpdate("Set search_path to rockinrio");
                       ResultSet rs;
                       rs = sentenca.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name ='"+a +"'");
                      //rs = sentenca.executeQuery("\\d " +a );
                      String aux = "";
                      JOptionPane.showMessageDialog(null, "Digite a seguir os dados cada um de uma vez. Campos que nao tem restricoes devem "
                              + "ser deixados em branco. ");
                       int i=0;
                        while (rs.next())
                        {                      
                            String z=null;
                            z=JOptionPane.showInputDialog(rs.getString(1));
                            if( z != null){
                                if(i>0) aux= aux+" AND ";
                                i++;
                                aux= aux+ rs.getString(1) + "='"+z+"'" ;
                            }
                        }
                        
                        sentenca.executeUpdate("Delete from " + a +" where "+ aux);
                        
                   } 
                  
                    catch (SQLException se) {
                              JOptionPane.showMessageDialog(null, "Tabela invalida ou voce nao possui esse privilegio!");

                            System.out.println("Nao foi possivel executar.");
                                               se.printStackTrace();
                            System.exit(1);}
  }
    
    public void insere ( String a, Connection con){
                   try { // Cria uma sentenca para atualizar o banco de dado
                       sentenca = con.createStatement();
                       sentenca.executeUpdate("Set search_path to rockinrio");
                       ResultSet rs;
                       rs = sentenca.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name ='"+a +"'");
                      //rs = sentenca.executeQuery("\\d " +a );
                      String aux = "( ";
                      JOptionPane.showMessageDialog(null, "Digite a seguir os dados cada um de uma vez. ");
                       int i=0;
                        while (rs.next())
                        {
                            if(i>0) aux= aux+" , ";
                            aux = aux + "'"+ JOptionPane.showInputDialog(rs.getString(1)) + "'";
                            i++;
                        }
                        aux = aux +")";
                        sentenca.executeUpdate("Insert into " + a +" values "+ aux);
                        
                   } 
                  
                    catch (SQLException se) {
                              JOptionPane.showMessageDialog(null, "Tabela invalida ou voce nao possui esse privilegio!");

                            System.out.println("Nao foi possivel executar.");
                                               se.printStackTrace();
                            System.exit(1);}
                        }
    
}