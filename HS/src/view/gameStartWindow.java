package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

//import org.junit.jupiter.params.converter.DefaultArgumentConverter;

import engine.GameListener;
public class gameStartWindow extends JFrame implements ActionListener{

	
	private JPanel Buttons;
	private JLabel HearthStone;
	private JPanel b;
	private JPanel a;
	private JButton StartGame;
	private JButton Exit;
	
	
	
	public gameStartWindow(){
		super();
		
		Dimension s= getToolkit().getScreenSize();
		
	     ImageIcon img=new ImageIcon("hearthstone.jpg");
	 setLayout(new FlowLayout());
	     JLabel background = new JLabel("",img,JLabel.CENTER);
	      background.setVisible(true);
		setTitle("HearthStone");
		
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.revalidate();
		this.repaint();
		JButton StartGame = new JButton("Start Game");
		background.setLayout(null);
		StartGame.setVisible(true);
		StartGame.setLayout(null);
		StartGame.setBounds((int)(0.4*s.getWidth()),(int) (0.85*s.getHeight()), 400, 50);
		
		 setDefaultLookAndFeelDecorated(true);
		StartGame.addActionListener(this);
StartGame.setLayout(new FlowLayout());
		 add(background);
		 background.add(StartGame);
		 this.pack();
		 this.revalidate();
			this.repaint();
		
		
		
		
		
		
			StartGame.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	StartGame.setBackground(Color.cyan);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	StartGame.setBackground(UIManager.getColor("control"));
			    }
			});	
		
		
		
		
		
		
		
		
	}
	
	
	public JPanel getButtons() {
		return Buttons;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b= (JButton) e.getSource();
		if(b.getText().equalsIgnoreCase("Start Game")){
			dispose(); 
	     	new ChooseHero();
			
		}
		else if(b.getText().equalsIgnoreCase("Exit"))
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

}
