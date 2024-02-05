package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;

import model.heroes.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import exceptions.InvalidTargetException;
public class ChooseHero extends JFrame implements ActionListener{
private JButton Mage,Hunter,Priest,Warlock,Paladin;
private JPanel b;
private JPanel a;
private Hero hero1;



	public ChooseHero() {
		super();
		 b= new JPanel();
	     a= new JPanel();
	     b.setSize(10,10);
			a.setSize(10,10);
			b.setVisible(true);
			a.setVisible(true);
		getContentPane(). setLayout(new GridLayout(1,5));
		setTitle("Choose Your Hero");
		setSize(1200, 700);
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.revalidate();
		this.repaint();
		ImageIcon warlock=new ImageIcon("warlock.jpg");
		ImageIcon mage=new ImageIcon("mage.jpg");
		ImageIcon hunter=new ImageIcon("hunter.jpg");
		ImageIcon priest=new ImageIcon("priest.jpg");
		ImageIcon paladin=new ImageIcon("paladin.jpg");
		 Mage = new JButton(mage);
		 Hunter = new JButton(hunter);
		 Priest= new JButton(priest);
		 Warlock= new JButton(warlock);
		 Paladin= new JButton(paladin);
		Mage.addActionListener(this);
		Hunter.addActionListener(this);
		Priest.addActionListener(this);
		Paladin.addActionListener(this);
		Warlock.addActionListener(this);
		Mage.setVisible(true);
		Hunter.setVisible(true);
		Priest.setVisible(true);
		Warlock.setVisible(true);
		Paladin.setVisible(true);
		Mage.setBounds(440, 100, 400, 50);
		Hunter.setBounds(440, 175, 400, 50);
		Priest.setBounds(440, 250, 400, 50);
		Warlock.setBounds(440, 325, 400, 50);
		Paladin.setBounds(440, 400, 400, 50);
		//add(a,BorderLayout.NORTH);
		add(Mage,new GridLayout(1,1));
		add(Hunter,new GridLayout(1,2));
		add(Priest,new GridLayout(1,3));
		add(Warlock,new GridLayout(1,4));
		add(Paladin,new GridLayout(1,5));
		//add(b,BorderLayout.SOUTH);
		Mage.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Mage.setBackground(Color.cyan);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Mage.setBackground(UIManager.getColor("control"));
		    }
		});
		Hunter.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Hunter.setBackground(Color.cyan);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Hunter.setBackground(UIManager.getColor("control"));
		    }
		});
		Warlock.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Warlock.setBackground(Color.cyan);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Warlock.setBackground(UIManager.getColor("control"));
		    }
		});
		Priest.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Priest.setBackground(Color.cyan);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Priest.setBackground(UIManager.getColor("control"));
		    }
		});
		Paladin.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	Paladin.setBackground(Color.cyan);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	Paladin.setBackground(UIManager.getColor("control"));
		    }
		});
		
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
						
			
			
			if(e.getSource()==Mage){
				Mage hero1 = null;
				try {
					hero1 = new Mage();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (hero1 != null){
					dispose();
					new ChooseSecondHero(hero1);
					}
			}
			if(e.getSource()==Hunter){
				Hunter hero1 = null;
				try {
					hero1 = new Hunter();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (hero1 != null){
					dispose();
					new ChooseSecondHero(hero1);
					}
			}
			if(e.getSource()==Warlock){
				Warlock hero1 = null;
				try {
					hero1 = new Warlock();
				} catch (IOException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (hero1 != null){
					dispose();
					new ChooseSecondHero(hero1);
					}
			}
			if(e.getSource()==Priest){
				Priest hero1 = null;
				try {
					hero1 = new Priest();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (hero1 != null){
					dispose();
					new ChooseSecondHero(hero1);
					}
			}
			else if(e.getSource()==Paladin){
				Paladin hero1 = null;
				try {
					hero1 = new Paladin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (hero1 != null){
					dispose();
					new ChooseSecondHero(hero1);
					}
			}
			
	
			
		
			
		}
		
		
		
		
		
		
		
		}


