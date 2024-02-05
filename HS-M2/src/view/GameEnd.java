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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class GameEnd extends JFrame implements ActionListener{
private JPanel k;
private JLabel won;
private JButton startnewgame;
private JButton Exit;
public Hero herowon;
public GameEnd(Hero herowon){
	super();
	setSize(1200, 800);
	this.setVisible(true);
	this.herowon=herowon;
	JLabel won = new JLabel();
	won.setText(herowon.getName() +" Won the Game");
	won.setBounds(550,100,800,350);
	won.setVisible(true);
	JButton startnewgame = new JButton(" Start New Game");
	startnewgame.setBounds(400,100,800,350);
	startnewgame.addActionListener(this);
	JButton Exit = new JButton("Exit");
	Exit.setBounds(300,100,800,350);
	Exit.addActionListener(this);
	JPanel k = new JPanel();
	startnewgame.setVisible(true);
	Exit.setVisible(true);
	k.setVisible(true);
	k.setSize(1200,800);
	startnewgame.setBounds(550,400,600, 200);
	Exit.setBounds(550, 800, 600, 200);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.revalidate();
	this.repaint();
	k.add(won,BorderLayout.NORTH);
	k.add(startnewgame,BorderLayout.CENTER);
	k.add(Exit,BorderLayout.SOUTH);
add(k);
}

	public JPanel getK() {
	return k;
}

public void setK(JPanel k) {
	this.k = k;
}

public JLabel getWon() {
	return won;
}

public void setWon(JLabel won) {
	this.won = won;
}

public JButton getStartnewgame() {
	return startnewgame;
}

public void setStartnewgame(JButton startnewgame) {
	this.startnewgame = startnewgame;
}

public JButton getExit() {
	return Exit;
}

public void setExit(JButton exit) {
	Exit = exit;
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b= (JButton) e.getSource();
		if(b.getText().equalsIgnoreCase(" Start New Game")){
			dispose();
			new gameStartWindow();
		}
		else if(b.getText().equalsIgnoreCase("Exit")){
			//setDefaultCloseOperation(EXIT_ON_CLOSE);
			dispose();
		}
	}
	

}