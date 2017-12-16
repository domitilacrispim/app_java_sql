
package ririo;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Adm_supremo extends JFrame {
    private String user = "Adm";
    private String senha = "123456";
    public boolean Adm_supremo(String a, String b){
        if ( !a.equals(user) || !b.equals(senha) ) return false ;
    final ImageIcon imageIcon = new ImageIcon("/home/domitila/Imagens/rir.jpg");
      JOptionPane.showMessageDialog(null,"Logado com administrador. Instruções "
                                                   + ""
                                                   +":\n 1. Você pode apagar, inserir e consultar."
                                                   +"\n 2. Cuidado ao deletar! Se os campos ficarem em branco TODOS os dados serão excuídos."
                                                   + "\n 3. Caso você tente deletar informações atreladas a mais de uma tabela a exclusão será feita em cascata. "
                                                   +"\n 4. Na situção de um dado inserido ser inválido, você será avisado e a inserção não acontecerá."
                                                   + "\n5. Tenha um bom dia de trabalho! :)");
            
   JPanel painel_princ = new JPanel(new FlowLayout()){
        	Image image = imageIcon.getImage();
        	Image grayImage = GrayFilter.createDisabledImage(image);
        	{
            	setOpaque(false);
        	}
        	public void paint(Graphics g) {
           		g.drawImage(grayImage, 0, 0, this);
            	super.paint(g);
        	}
    	};// painel principal
     JButton inser = new JButton("Inserção"); // botao de insercao  
     JButton consult = new JButton("Consulta"); // botao de consulta
      JButton dele = new JButton("Deleção"); // botao de delecao
      JTextField tabela = new JTextField(20);
      painel_princ.add(consult);    
      return true;
    }
    
    
}
