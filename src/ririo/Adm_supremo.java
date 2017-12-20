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
        JTextField tabela = new JTextField(20);
        JLabel ajuda = new JLabel("Digite o nome da tabela que deseja editar:\n");
        //painel_princ.setLayout(new GridLayout(4,1));
        ajuda.setForeground(Color.white);

        painel_princ.add(ajuda);
        painel_princ.add(tabela);
        painel_princ.add(inser);
        painel_princ.add(dele);
        painel_princ.add(consult);
        JFrame campo = new JFrame("Janela do Adm.");
        campo.setSize(400, 300);
        campo.setDefaultCloseOperation(EXIT_ON_CLOSE);
        campo.getContentPane().add(painel_princ);
        inser.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
              insere(tabela.getText(),con );
                // JOptionPane.showMessageDialog(null, "mimim");
                
              

            }
        }
        );
        campo.setVisible(true);

        return true;
    }
    
    public void insere ( String a, Connection con){
                   try { // Cria uma sentenca para atualizar o banco de dado
                       sentenca = con.createStatement();
                       sentenca.executeUpdate("Set search_path to rockinrio");
                       ResultSet rs;
                       rs = sentenca.executeQuery("SELECT column_name FROM information_schema.columns WHERE table_name ='"+a +"'");
                       JPanel painel_princ = new JPanel(new FlowLayout());
                      JTextField [] campo = new JTextField[145];
                      int i=0;
                        while (rs.next())
                        {
                            
                            painel_princ.add(new JLabel(rs.getString(1)));
                            painel_princ.add(campo[i]); i++;
                        }
                        JButton botao = new JButton("Pronto!");
                        painel_princ.add(botao); 
                       
                        final int y = i;
                                            String tentativa_desesperada = reconhece(campo, i);
                        botao.addActionListener (
                                new ActionListener(){
                                    public void actionPerformed (ActionEvent e){
                                       
                                            
                                        try {
                                            
                                              sentenca.executeUpdate("insert into" + a + "values"+tentativa_desesperada  );
                                              JOptionPane.showMessageDialog(null,"UHUUUUU!");   
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Adm_supremo.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            );
                          JFrame janela = new JFrame("Janela da consulta.");
                     
                        janela.add(painel_princ);
                         janela.setSize(300,500);
                           janela.setResizable(false);
                               janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        janela.setVisible(true);
                       
                   } 
                  
catch (SQLException se) {
          JOptionPane.showMessageDialog(null, "Tabela invalida ou voce nao possui esse privilegio!");
                      
        System.out.println("Nao foi possivel executar.");
                           se.printStackTrace();
        System.exit(1);}
    }
    public String reconhece (JTextField [] array_botao, int i){
        String a=null; int p=0;
        
        for ( int o=0; o<i; i++){
            a=a+"'" + array_botao[o] + "'";
            if (0!=p){
                a=a+" ,";
            }
            p=1;
        }
        System.out.print(a);
        return a;
    }
}