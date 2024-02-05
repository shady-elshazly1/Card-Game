package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class View extends JFrame {
	private JPanel CurrentHand ;
	private JPanel OppHand;
	private JPanel CurrField;
	private JPanel OppField;
	private JPanel CurrInfo;
	private JPanel OppInfo;
	private JPanel CurrPowerEnd;
	private JPanel OppPowerEnd;
	private JPanel CurrDeck;
	private JPanel OppDeck;
	private JPanel CurrHandNum;
	private JPanel OppHandNum;
	private JPanel WhosTurn;
	private JLabel chnT;
	private JLabel ohnT;
	private JTextArea text;
	private JPanel b;
	private JPanel a;
	private JPanel l;
	private JPanel e;
	private JPanel g;
	private JPanel i;
	private JPanel z;
	private JPanel o;
	private String T1;private String T2;private  String H1;private String H2;private String D1;
	private String D2;
	

	public View () {
		super();
		JButton test = new JButton("test");
		test.setVisible(true);
		test.setSize(100, 100);
		chnT= new JLabel();
		chnT.setVisible(true);
		ohnT= new JLabel();
		ohnT.setVisible(true);
	 CurrentHand= new JPanel();
	 OppHand= new JPanel();
	 WhosTurn= new JPanel();
	 CurrField= new JPanel();
	 OppField= new JPanel();
	 CurrInfo= new JPanel();
	 OppInfo= new JPanel();
	 CurrPowerEnd= new JPanel();
	 OppPowerEnd= new JPanel();
	 CurrDeck= new JPanel();
	 OppDeck= new JPanel();
	 b= new JPanel();
     a= new JPanel();
     l= new JPanel();
	 e= new JPanel();
	 g= new JPanel();
	 i= new JPanel();
	 z= new JPanel();
	 o= new JPanel();
	 

	
	setTitle("HearthStone");
	setSize(1500, 800);
	setVisible(true); 
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.revalidate();
	this.repaint();
	b.setSize(10,10);
	a.setSize(10,10);
	l.setSize(10,10);
	e.setSize(10,10);
	g.setSize(10,10);
	i.setSize(10,10);
	z.setSize(10,10);
	o.setSize(10,10);
	b.setVisible(true);
	a.setVisible(true);
	l.setVisible(true);
	e.setVisible(true);
	g.setVisible(true);
	i.setVisible(true);
	z.setVisible(true);
	o.setVisible(true);
	
	
	WhosTurn.setVisible(true);
	WhosTurn.setBackground(Color.GREEN);
	WhosTurn.setBounds(100, 340, 1100, 40);
	CurrHandNum= new JPanel();
	CurrHandNum.setVisible(true);
	CurrHandNum.setBackground(Color.cyan);
	CurrHandNum.setBounds(1210, 510, 120, 100);
	OppHandNum= new JPanel();
	OppHandNum.setVisible(true);
	OppHandNum.setBounds(1210, 107, 120, 100);
	OppHandNum.setBackground(Color.cyan);
	
	CurrInfo.setBounds(530,650 , 200, 70);
	CurrInfo.setVisible(true);
	CurrInfo.setBackground(Color.GRAY);
	OppInfo.setBounds(530,0,200 ,70);
	OppInfo.setVisible(true);
	OppInfo.setBackground(Color.cyan);
	CurrentHand.setBounds(100, 498, 1100, 150);
	CurrentHand.setVisible(true);
	CurrentHand.setBackground(Color.RED);
	OppHand.setBounds(100, 72, 1100, 150);
	OppHand.setVisible(true);
	OppHand.setBackground(Color.BLUE);
	CurrField.setBounds(100, 375, 1100, 120);
	CurrField.setVisible(true);
	CurrField.setBackground(Color.BLUE);
	OppField.setBounds(100, 225, 1100, 120);
	OppField.setVisible(true);
	OppField.setBackground(Color.RED);
	CurrPowerEnd.setBounds(0,642,200,100);
	CurrPowerEnd.setVisible(true);
	OppPowerEnd.setBounds(0,0,200,100);
	OppPowerEnd.setVisible(true);
	CurrDeck.setBounds(1220,620,135, 100);
	CurrDeck.setVisible(true);
	CurrDeck.setBackground(Color.green);
	OppDeck.setBounds(1220,0,135,100);
	OppDeck.setVisible(true);	
	OppDeck.setBackground(Color.GREEN);
	
	add(OppHand);
	add(CurrentHand);
	add(CurrHandNum);
	add(OppInfo);
	add(CurrInfo);
	add(OppHandNum);
	add(CurrField);
	add(OppField);
	add(CurrPowerEnd);
	add(OppPowerEnd);
	
	add(CurrDeck);
	add(OppDeck);
	add(WhosTurn);
	add(b);
	
	
	}	public JLabel getChnT() {
		return chnT;
	}
	public void setChnT(JLabel chnT) {
		this.chnT = chnT;
	}
	public JLabel getOhnT() {
		return ohnT;
	}
	public void setOhnT(JLabel ohnT) {
		this.ohnT = ohnT;
	}
	public JPanel getCurrHandNum() {
		return CurrHandNum;
	}
	public void setCurrHandNum(JPanel currHandNum) {
		CurrHandNum = currHandNum;
	}
	public JPanel getOppHandNum() {
		return OppHandNum;
	}
	public void setOppHandNum(JPanel oppHandNum) {
		OppHandNum = oppHandNum;
	}
	public JPanel getWhosTurn() {
		return WhosTurn;
	}
	public void setWhosTurn(JPanel whosTurn) {
		WhosTurn = whosTurn;
	}
	public JPanel getB() {
		return b;
	}

	public JPanel getA() {
		return a;
	}
	public JPanel getL() {
		return l;
	}	
	public JPanel getE() {
		return e;
	}
	public JPanel getG() {
		return g;
	}
	public JPanel getI() {
	return i;
	}
	public JPanel getZ() {
	return z;
	}
	public JPanel getO() {
		return o;
	}
	public JPanel getCurrentHand() {
		return CurrentHand;
	}
	public JPanel getOppHand() {
		return OppHand;
	}






	public JPanel getCurrField() {
		return CurrField;
	}
public JPanel getOppField() {
		return OppField;
	}
	public JPanel getCurrInfo() {
		return CurrInfo;
	}
	public JPanel getOppInfo() {
		return OppInfo;
	}
	public JPanel getCurrPowerEnd() {
		return CurrPowerEnd;
	}
	public JPanel getOppPowerEnd() {
		return OppPowerEnd;
	}
	public JPanel getCurrDeck() {
		return CurrDeck;
	}
	public JPanel getOppDeck() {
		return OppDeck;
	}
	public JTextArea getText() {
		return text;
	}
	public String getT1() {
		return T1;
	}
	public String getT2() {
		return T2;
	}
	public String getH1() {
		return H1;
	}
	public String getH2() {
		return H2;
	}
	public String getD1() {
		return D1;
	}
	public String getD2() {
		return D2;
	}

}
