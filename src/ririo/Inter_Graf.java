package ririo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Inter_Graf extends JFrame {
    public Inter_Graf(){
    	super("Login");
        Adm_supremo teste_s = new Adm_supremo();
    	Container container = getContentPane();
    	container.setLayout(new BorderLayout());
        JLabel regras1 = new JLabel("Bem vindo! Logue no menu acima para obter dados sobre");
        regras1.setForeground(Color.white);
        regras1.setFont(new Font("Dialog", Font.PLAIN, 30));
        JLabel regras2 = new JLabel("a infraestrutura do Rock in rio!");
        regras2.setForeground(Color.white);
        regras2.setFont(new Font("Dialog", Font.PLAIN, 30));
        JLabel regras3 = new JLabel("Aqui você pode consultar, inserir e excluir dados de forma fácil. ");
        regras3.setForeground(Color.white);
        regras3.setFont(new Font("Dialog", Font.PLAIN, 30));
    	//aqui vc deve adicionar a imagem que deseja mostrar no JPanel
        
    	final ImageIcon imageIcon = new ImageIcon("/home/domitila/Imagens/rir.jpg");
    	JPanel painel = new JPanel(new FlowLayout()){
        	Image image = imageIcon.getImage();
        	Image grayImage = GrayFilter.createDisabledImage(image);
        	{
            	setOpaque(false);
        	}
        	public void paint(Graphics g) {
           		g.drawImage(grayImage, 0, 0, this);
            	super.paint(g);
        	}
    	};
        JPanel painelcima = new JPanel(new FlowLayout()){
        	Image image = imageIcon.getImage();
        	Image grayImage = GrayFilter.createDisabledImage(image);
        	{
            	setOpaque(false);
        	}
        	public void paint(Graphics g) {
           		g.drawImage(grayImage, 0, 0, this);
            	super.paint(g);
        	}
    	};
        JTextField usuario = new JTextField(18);
        JPasswordField senha = new JPasswordField(18);
        JButton logar = new JButton("Logar");
    	painelcima.add(new JLabel("Login:"));
    	painelcima.add(usuario);
    	painelcima.add(new JLabel("Senha:"));
    	painelcima.add(senha);
    	painelcima.add(logar);
        painel.add(regras1);
        painel.add(regras2);
 	painel.add(regras3);
 		container.add(painel, BorderLayout.CENTER); 
 		container.add(painelcima, BorderLayout.NORTH);
    	setSize(1000,800);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
           logar.addActionListener (
                new ActionListener(){
                    public void actionPerformed (ActionEvent e){
                       if(teste_s.Adm_supremo(usuario.getText(),senha.getText())){
                           new Adm_supremo().setVisible(true);
                       }
                       
                    }
                }
            );
            
        
    }
    
}