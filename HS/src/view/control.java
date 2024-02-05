package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.print.attribute.standard.Fidelity;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import view.control;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class control extends JFrame implements ActionListener,GameListener {
private View view;
private Game game;
private GameListener listener;
private ArrayList<JButton> hand1= new ArrayList<JButton>();
private ArrayList<JButton> hand2= new ArrayList<JButton>();
private ArrayList<JButton> field1= new ArrayList<JButton>();
private ArrayList<JButton> field2= new ArrayList<JButton>();
private ArrayList<JButton> herobuttons1= new ArrayList<JButton>();
private ArrayList<JButton> herobuttons2= new ArrayList<JButton>();
private ArrayList<JButton> attacker1= new ArrayList<JButton>();
private ArrayList<JButton> attacker2= new ArrayList<JButton>();
private ArrayList<JButton> target1= new ArrayList<JButton>();
private ArrayList<JButton> target2= new ArrayList<JButton>();
private ArrayList<JButton> spells1= new ArrayList<JButton>();
private ArrayList<JButton> spells2= new ArrayList<JButton>();
private ArrayList<JButton> usespells1= new ArrayList<JButton>();
private ArrayList<JButton> usespells2= new ArrayList<JButton>();
private ArrayList<JButton> heropowertarget= new ArrayList<JButton>();
private JLabel deck1;
private JLabel deck2;
private JButton name1;
private JButton name2;
private JLabel totalmana2;
private JLabel totalmana1;
private JButton draw1;
private JButton draw2;
private JLabel health1;
private JLabel health2;
private Hero hero1;
private Hero hero2;
private JButton turn;
public control (Hero hero1,Hero hero2)  {
	
	view = new View();
	
	try {
		game = new Game(hero1, hero2);
	} catch (FullHandException | CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e.getMessage());
		e.printStackTrace();
	}
	for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
		JButton b= new JButton();
		if(game.getCurrentHero().getHand().get(i)instanceof Minion)
			  b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+" Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()+" Ds:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isDivine())+" Sleep:"+((Minion)(game.getCurrentHero().getHand().get(i))).isSleeping())+" Taunt:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isTaunt())+" Charge:"+((!((Minion) (game.getCurrentHero().getHand().get(i))).isSleeping())));
		else {
			 b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+"Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()));
		}
		this.hero1=game.getCurrentHero();
		this.hero2=game.getOpponent();
		b.setSize(300,300);
		b.addActionListener(this);
		b.setVisible(true);
		hand1.add(b);
		if(game.getCurrentHero().getHand().get(i) instanceof Spell)
			spells1.add(b);
		view.getCurrentHand().add(b);
		view.add(view.getCurrentHand());
		
	}
	for (int i = 0; i < game.getOpponent().getHand().size(); i++) {
		JButton b = new JButton("??");
		b.addActionListener(this);
		b.setVisible(true);
		b.setSize(300,300);
		hand2.add(b);
		if(game.getOpponent().getHand().get(i) instanceof Spell)
			spells2.add(b);
		view.getOppHand().add(b);
		view.add(view.getOppHand());
		
	}
	
	 name1 = new JButton(game.getCurrentHero().getName());
	 name1.addActionListener(this);
	name1.setSize(100, 100);
	herobuttons1.add(name1);
	name1.addActionListener(this);
	name1.setForeground(Color.CYAN);
	view.getCurrInfo().add(name1);
	
	
	name2 = new JButton(game.getOpponent().getName());
	name2.addActionListener(this );
	name2.addActionListener(this);
	herobuttons2.add(name2);
	name2.setSize(100, 100);
	name2.setForeground(Color.CYAN);
	view.getOppInfo().add(name2);
	
	
	 health1 = new JLabel("HP"+game.getCurrentHero().getCurrentHP()+ " / 30");
	health1.setSize( 100, 100);
	health1.setForeground(Color.RED);
	view.getCurrInfo().add(health1);
	
	
	 health2 = new JLabel("HP"+game.getOpponent().getCurrentHP() +" / 30");
	health2.setSize( 100, 100);
	health2.setForeground(Color.RED);
	view.getOppInfo().add(health2);
	
	

    totalmana1 = new JLabel( "ManaCrystals"+game.getCurrentHero().getCurrentManaCrystals() +"/ 10");
	totalmana1.setSize( 100, 100);
	totalmana1.setForeground(Color.BLUE);
view.getCurrInfo().add(totalmana1);
	
	
	
	
	
	
	
	 totalmana2 = new JLabel("ManaCrystals"+game.getOpponent().getCurrentManaCrystals() +"/ 10");
	totalmana1.setSize( 100, 100);
	totalmana2.setForeground(Color.BLUE);
	view.getOppInfo().add(totalmana2);
	
	
	JButton heropower1= new JButton("Power");
	herobuttons1.add(heropower1);
	heropower1.setSize(90,90);
	heropower1.setForeground(Color.ORANGE);
	heropower1.addActionListener(this);
	JButton attack1 = new JButton("attack");
	herobuttons1.add(attack1);
	attack1.addActionListener(this);
	JButton attack2 = new JButton("attack");
	herobuttons2.add(attack2);
	attack2.addActionListener(this);
	JButton castSpell1 = new JButton("use spell");
	herobuttons1.add(castSpell1);
	castSpell1.addActionListener(this);
	JButton castSpell2 = new JButton("use spell");
	herobuttons2.add(castSpell2);
	castSpell2.addActionListener(this);
	
	JButton heropower2= new JButton("Power");
	herobuttons2.add(heropower2);
	heropower2.setSize(90,90);
	heropower2.setForeground(Color.ORANGE);
	heropower2.addActionListener(this);
	JButton endturn1= new JButton("End Turn");
	herobuttons1.add(endturn1);
	endturn1.setSize(90,90);
	endturn1.addActionListener(this);
	JButton endturn2= new JButton("End Turn");
	herobuttons2.add(endturn2);
	endturn2.setSize(90,90);
	endturn2.addActionListener(this);
	deck1= new JLabel("Cards in deck"+game.getCurrentHero().getDeck().size() + "/ 20");
	deck1.setSize(20, 20);
	deck1.setForeground(Color.black);
	
	deck2= new JLabel("Cards in deck"+game.getOpponent().getDeck().size() + "/ 20");
	deck2.setSize(20, 20);
	deck2.setForeground(Color.black);
	
	 draw1= new JButton("Draw");
	herobuttons1.add(draw1);
	draw1.setSize(90, 90);
	draw1.addActionListener(this);
	 draw2= new JButton("Draw");
	herobuttons2.add(draw2);
	draw2.setSize(90, 90);
	draw2.addActionListener(this);
	view.getContentPane().setBackground(Color.GRAY);
	
	
	
	
	
	
	 turn= new JButton();
	turn.setVisible(true);
	turn.setText(game.getCurrentHero().getName()+"'s Turn ");
	view.getWhosTurn().add(turn);
	
	
	view.getChnT().setText(hero1.getHand().size()+" Cards in Hand");
	view.getOhnT().setText(hero2.getHand().size()+" Cards in Hand");
	view.getCurrHandNum().add(view.getChnT());
	view.getOppHandNum().add(view.getOhnT());
	
	
	view.getCurrPowerEnd().add(heropower1);
	view.getCurrPowerEnd().add(endturn1);
	
	view.getCurrPowerEnd().add(attack1);
	view.getCurrPowerEnd().add(castSpell1);
	
	view.getOppPowerEnd().add(attack2);
	view.getOppPowerEnd().add(castSpell2);
	
	view.getOppPowerEnd().add(heropower2);
	view.getOppPowerEnd().add(endturn2);
	
	
	
	
	
	
	view.getCurrDeck().add(deck1);
	
	
	
	
	
	
	view.getOppDeck().add(deck2);

	
	
	
		
	
	
	
	
	
	
	
	
	
	
	view.add(view.getOppPowerEnd());
	view.add(view.getOppInfo());
	view.add(view.getOppDeck());
	view.add(view.getB());
	view.add(view.getOppHand());
	view.add(view.getA());
	view.add(view.getL());
	view.add(view.getOppField());
	view.add(view.getE());
	view.add(view.getG());
	view.add(view.getCurrField());
	view.add(view.getI());
	view.add(view.getZ());
    view.add(view.getCurrentHand());
	view.add(view.getO());
	view.add(view.getCurrPowerEnd());
	view.add(view.getCurrInfo());
	view.add((view.getCurrDeck()));
	view.add((view.getB()));

}



private  void updateStringsH1curr() {
	health1.setText("HP"+game.getCurrentHero().getCurrentHP()+ " / 30");
	deck1.setText("Cards in deck"+game.getCurrentHero().getDeck().size() + "/ 20");
	 totalmana1.setText( "ManaCrystals"+game.getCurrentHero().getCurrentManaCrystals() +"/ 10");
	 view.getChnT().setText(hero1.getHand().size()+" Cards in Hand");
		
}
private  void updateStringsH1opp() {
	health1.setText("HP"+game.getOpponent().getCurrentHP()+ " / 30");
	deck1.setText("Cards in deck"+game.getOpponent().getDeck().size() + "/ 20");
	 totalmana1.setText( "ManaCrystals"+game.getOpponent().getCurrentManaCrystals() +"/ 10");
	 view.getChnT().setText(hero1.getHand().size()+" Cards in Hand");
		
}
private void updateStringsH2curr() {
	health2.setText("HP"+game.getCurrentHero().getCurrentHP()+ " / 30");
	deck2.setText("Cards in deck"+game.getCurrentHero().getDeck().size() + "/ 20");
	 totalmana2.setText( "ManaCrystals"+game.getCurrentHero().getCurrentManaCrystals() +"/ 10");
	 view.getOhnT().setText(hero2.getHand().size()+" Cards in Hand");
}
private void updateStringsH2opp() {
	health2.setText("HP"+game.getOpponent().getCurrentHP()+ " / 30");
	deck2.setText("Cards in deck"+game.getOpponent().getDeck().size() + "/ 20");
	 totalmana2.setText( "ManaCrystals"+game.getOpponent().getCurrentManaCrystals() +"/ 10");
	 view.getOhnT().setText(hero2.getHand().size()+" Cards in Hand");
}
public void updatefield1curr(){
	int size = field1.size();
	for (int i = 0; i <size; i++) {
		view.getCurrField().remove(field1.get(0));
		field1.remove(0);
	
	}
	for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
		JButton B= new JButton();
		B.addActionListener(this);
		field1.add(B);
		B.setText(game.getCurrentHero().getField().get(i).getName()+"MC:"+game.getCurrentHero().getField().get(i).getManaCost()+" HP:"+game.getCurrentHero().getField().get(i).getCurrentHP()+game.getCurrentHero().getField().get(i).getRarity()+" DS:"+game.getCurrentHero().getField().get(i).isDivine()+ " Sleep:"+game.getCurrentHero().getField().get(i).isSleeping()+" Taunt:"+game.getCurrentHero().getField().get(i).isTaunt()+" chraged:"+!(game.getCurrentHero().getField().get(i).isSleeping()));
		
		view.getCurrField().add(B);
	
	}
	
	
}
public void updatefield2curr(){
	int size = field2.size();
	for (int i = 0; i <size; i++) {
	view.getOppField().remove(field2.get(0));
		field2.remove(0);	
	}
	for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
		JButton B= new JButton();
		B.addActionListener(this);
		field2.add(B);
		B.setText(game.getCurrentHero().getField().get(i).getName()+"MC:"+game.getCurrentHero().getField().get(i).getManaCost()+" HP:"+game.getCurrentHero().getField().get(i).getCurrentHP()+game.getCurrentHero().getField().get(i).getRarity()+" DS:"+game.getCurrentHero().getField().get(i).isDivine()+ " Sleep:"+game.getCurrentHero().getField().get(i).isSleeping()+" Taunt:"+game.getCurrentHero().getField().get(i).isTaunt()+" chraged:"+!(game.getCurrentHero().getField().get(i).isSleeping()));
		
		
		view.getOppField().add(B);
	
	}
	
}
public void updatefield1opp(){
	int size = field1.size();
	for (int i = 0; i <size; i++) {
		view.getCurrField().remove(field1.get(0));
	field1.remove(0);	
	}
	for (int i = 0; i < game.getOpponent().getField().size(); i++) {
		JButton B= new JButton();
		field1.add(B);
		B.addActionListener(this);
		B.setText(game.getOpponent().getField().get(i).getName()+"MC:"+game.getOpponent().getField().get(i).getManaCost()+" HP:"+game.getOpponent().getField().get(i).getCurrentHP()+game.getOpponent().getField().get(i).getRarity()+" DS:"+game.getOpponent().getField().get(i).isDivine()+ " Sleep:"+game.getOpponent().getField().get(i).isSleeping()+" Taunt:"+game.getOpponent().getField().get(i).isTaunt()+" charge:"+!(game.getOpponent().getField().get(i).isSleeping()));
		
		
		view.getCurrField().add(B);
	
	}
	
}
public void updatefield2opp(){
	int size = field2.size();
	for (int i = 0; i <size; i++) {
		view.getOppField().remove(field2.get(0));
	field2.remove(0);	
	}
	for (int i = 0; i < game.getOpponent().getField().size(); i++) {
		JButton B= new JButton();
		field2.add(B);
		B.addActionListener(this);
		B.setText(game.getOpponent().getField().get(i).getName()+"MC:"+game.getOpponent().getField().get(i).getManaCost()+" HP:"+game.getOpponent().getField().get(i).getCurrentHP()+game.getOpponent().getField().get(i).getRarity()+" DS:"+game.getOpponent().getField().get(i).isDivine()+ " Sleep:"+game.getOpponent().getField().get(i).isSleeping()+" Taunt:"+game.getOpponent().getField().get(i).isTaunt()+" charge:"+!(game.getOpponent().getField().get(i).isSleeping()));
		
		
		view.getOppField().add(B);
	}
	
}

public void updatehero2() {
	int size=hand2.size();
	for (int i = 0; i <size ; i++) {
		view.getOppHand().remove(0);
		 this.hand2.remove(0);
		
	}
	for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
		JButton b= new JButton();
		if(game.getCurrentHero().getHand().get(i)instanceof Minion)
			  b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+" Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()+" Ds:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isDivine())+" Sleep:"+((Minion)(game.getCurrentHero().getHand().get(i))).isSleeping())+" Taunt:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isTaunt())+" Charge:"+((!((Minion) (game.getCurrentHero().getHand().get(i))).isSleeping())));
		else {
			 b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+"Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()));
		}
		b.addActionListener(this);
			hand2.add(b);
			 if(game.getCurrentHero().getHand().get(i) instanceof Spell) 
					spells2.add(b);
		    view.getOppHand().add(b);}
		updateStringsH2curr();
		
	h1opphand();

}
public void updatehero11() {
	int size=hand1.size();
	for (int i = 0; i < size; i++) {
		view.getCurrentHand().remove(0);
		 this.  hand1.remove(0);		
	}
	for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
		JButton b= new JButton();
		if(game.getCurrentHero().getHand().get(i)instanceof Minion)
			  b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+" Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()+" Ds:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isDivine())+" Sleep:"+((Minion)(game.getCurrentHero().getHand().get(i))).isSleeping())+" Taunt:"+(((Minion)(game.getCurrentHero().getHand().get(i))).isTaunt())+" Charge:"+((!((Minion) (game.getCurrentHero().getHand().get(i))).isSleeping())));
		else {
			 b.setText((game.getCurrentHero().getHand().get(i).getName()+" MC:"+game.getCurrentHero().getHand().get(i).getManaCost()+"Rarity:"+game.getCurrentHero().getHand().get(i).getRarity()));
		} b.addActionListener(this); 
		    	this.hand1.add(b);
                   if(game.getCurrentHero().getHand().get(i) instanceof Spell)
				     	spells1.add(b);
			 
		    view.getCurrentHand().add(b);}
		
	updateStringsH1curr();
		h2opphand();
	

}
public void h1opphand() {
	
		int size=hand1.size();
		for (int i = 0; i < size; i++) {
			view.getCurrentHand().remove(0);
			 this.  hand1.remove(0);		
		}
		for (int i = 0; i < game.getOpponent().getHand().size(); i++) {
				JButton b= new JButton("??");
				 b.addActionListener(this); 
			    	this.hand1.add(b);
	                 
				 
			    view.getCurrentHand().add(b);}
			
		
			
		

	}
public void h2opphand() {
		
		int size=hand2.size();
		for (int i = 0; i <size ; i++) {
			view.getOppHand().remove(0);
			 this.hand2.remove(0);
			
		}
		for (int i = 0; i < game.getOpponent().getHand().size(); i++) {
				JButton b= new JButton("??");
				 b.addActionListener(this);
				this. hand2.add(b);
				
				
			    view.getOppHand().add(b);}
			
		
	}



public void hero1endturn(ActionEvent e) {
	JButton b= (JButton) e.getSource();
	Card c=null;
	if(!(hero1.getDeck().isEmpty())) {
     c = hero1.getDeck().get(0);
	}
		try {
			hero1.endTurn();
		} catch (FullHandException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			if(hero1.getDeck().isEmpty())
				JOptionPane.showMessageDialog(null, e1.getMessage());
			else
			JOptionPane.showMessageDialog(null, e1.getMessage()+"  "+"the burned card is:"+c.getName()+" Manacost: "+c.getManaCost()+" "+" Rarity:"+c.getRarity());
			
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	view.getWhosTurn().setBackground(Color.MAGENTA);
	turn.setText(game.getCurrentHero().getName()+"'s Turn ");
	updatehero2();
	updateStringsH2curr();
	updateStringsH1opp();
	updatefield2curr();
	draw2.addActionListener(this);
	while(!(attacker1.isEmpty())) {
		attacker1.remove(0);
	}
	while(!(attacker2.isEmpty())) {
		attacker2.remove(0);
	}
	while(!(usespells1.isEmpty())) {
		usespells1.remove(0);
	}
	while(!(usespells2.isEmpty())) {
		usespells2.remove(0);
	}
	while(!(target1.isEmpty())) {
		target1.remove(0);
	}
	while(!(target2.isEmpty())) {
	target2.remove(0);
	}
	while(!(heropowertarget.isEmpty())) {
		heropowertarget.remove(0);
		}
}

public void hero2endturn(ActionEvent e) {
	JButton b= (JButton) e.getSource();
	Card c=null;
	if(!(hero2.getDeck().isEmpty())){
	 c = hero2.getDeck().get(0);}
		try {
			hero2.endTurn();
		} catch (FullHandException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			if(hero2.getDeck().isEmpty())
				JOptionPane.showMessageDialog(null, e1.getMessage());
			else
			JOptionPane.showMessageDialog(null, e1.getMessage()+" "+"the burned card is:"+c.getName()+" Manacost: "+c.getManaCost()+" "+" Rarity:"+c.getRarity());
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		view.getWhosTurn().setBackground(Color.green);
	turn.setText(game.getCurrentHero().getName()+"'s Turn ");
	updatehero11();
	updatefield1curr();
	updateStringsH2opp();
	updateStringsH1curr();
	draw1.addActionListener(this);
	while(!(attacker1.isEmpty())) {
		attacker1.remove(0);
	}
	while(!(attacker2.isEmpty())) {
		attacker2.remove(0);
	}
	while(!(usespells1.isEmpty())) {
		usespells1.remove(0);
	}
	while(!(usespells2.isEmpty())) {
		usespells2.remove(0);
	}
	while(!(target1.isEmpty())) {
		target1.remove(0);
	}
	while(!(target2.isEmpty())) {
	target2.remove(0);
	}
	while(!(heropowertarget.isEmpty())) {
		heropowertarget.remove(0);
		}
	
}
public void hero1attack() {
	if (!(attacker1.isEmpty())&&!(target2.isEmpty()))
	{
		if(!(target2.get(0).getText().equalsIgnoreCase(hero2.getName())))
		{	
		try {
			hero1.attackWithMinion(hero1.getField().get(field1.indexOf(attacker1.get(0))), hero2.getField().get(field2.indexOf(target2.get(0))));
		updatefield1curr();
		updatefield2opp();
		updateStringsH1curr();
		updateStringsH2opp();
		while(!(attacker1.isEmpty())) {
			attacker1.remove(0);
		}
		while(!(attacker2.isEmpty())) {
			attacker2.remove(0);
		}
		while(!(usespells1.isEmpty())) {
			usespells1.remove(0);
		}
		while(!(usespells2.isEmpty())) {
			usespells2.remove(0);
		}
		while(!(target1.isEmpty())) {
			target1.remove(0);
		}
		while(!(target2.isEmpty())) {
		target2.remove(0);
		}
		
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
				| NotSummonedException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
		else {
			try {
				hero1.attackWithMinion(hero1.getField().get(field1.indexOf(attacker1.get(0))), hero2);
				updatefield1curr();
				updatefield2opp();
				updateStringsH1curr();
				updateStringsH2opp();
				while(!(attacker1.isEmpty())) {
					attacker1.remove(0);
				}
				while(!(attacker2.isEmpty())) {
					attacker2.remove(0);
				}
				while(!(usespells1.isEmpty())) {
					usespells1.remove(0);
				}
				while(!(usespells2.isEmpty())) {
					usespells2.remove(0);
				}
				while(!(target1.isEmpty())) {
					target1.remove(0);
				}
				while(!(target2.isEmpty())) {
				target2.remove(0);
				}
			} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
					| InvalidTargetException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
	
	}
	else {
		JOptionPane.showMessageDialog(null, "choose attacker and target");
	}
	
}
public void hero2attack() {
	if (!(attacker2.isEmpty())&&!(target1.isEmpty()))
	{
		if(!(target1.get(0).getText().equalsIgnoreCase(hero1.getName())))
		{	
		try {
			hero2.attackWithMinion(hero2.getField().get(field2.indexOf(attacker2.get(0))), hero1.getField().get(field1.indexOf(target1.get(0))));
		updatefield1opp();
		updatefield2curr();
		updateStringsH1opp();
		updateStringsH2curr();
		while(!(attacker1.isEmpty())) {
			attacker1.remove(0);
		}
		while(!(attacker2.isEmpty())) {
			attacker2.remove(0);
		}
		while(!(usespells1.isEmpty())) {
			usespells1.remove(0);
		}
		while(!(usespells2.isEmpty())) {
			usespells2.remove(0);
		}
		while(!(target1.isEmpty())) {
			target1.remove(0);
		}
		while(!(target2.isEmpty())) {
		target2.remove(0);
		}
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
				| NotSummonedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		else {
			try {
				hero2.attackWithMinion(hero2.getField().get(field2.indexOf(attacker2.get(0))), hero1);
				updatefield1opp();
				updatefield2curr();
				updateStringsH1opp();
				updateStringsH2curr();
				while(!(attacker1.isEmpty())) {
					attacker1.remove(0);
				}
				while(!(attacker2.isEmpty())) {
					attacker2.remove(0);
				}
				while(!(usespells1.isEmpty())) {
					usespells1.remove(0);
				}
				while(!(usespells2.isEmpty())) {
					usespells2.remove(0);
				}
				while(!(target1.isEmpty())) {
					target1.remove(0);
				}
				while(!(target2.isEmpty())) {
				target2.remove(0);
				}
			} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
					| InvalidTargetException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	else {
		JOptionPane.showMessageDialog(null, "choose attacker and target ");
	}
	
	
}
public void hero1power() {
	if (hero1 instanceof Mage ) {
		if(!(heropowertarget.isEmpty())) {
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())||heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName()))
			{
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName())) {
				try {
				((Mage) hero1).useHeroPower(hero2);
				updatefield1curr();
				updatefield2opp();
				updatehero11();
				updateStringsH1curr();
				updateStringsH2opp();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}}
			else {
				try {
					((Mage) hero1).useHeroPower(hero1);
					updatefield1curr();
					updatefield2opp();
					updatehero11();
					updateStringsH1curr();
					updateStringsH2opp();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
				
			}
			}
			else {
			if(field2.contains(heropowertarget.get(0))) {	
			try {
				((Mage) hero1).useHeroPower(hero2.getField().get(field2.indexOf(heropowertarget.get(0))));
				updatefield1curr();
				updatefield2opp();
				updatehero11();
				updateStringsH1curr();
				updateStringsH2opp();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}}
			else {
				try {
					((Mage) hero1).useHeroPower(hero1.getField().get(field1.indexOf(heropowertarget.get(0))));
					updatefield1curr();
					updatefield2opp();
					updatehero11();
					updateStringsH1curr();
					updateStringsH2opp();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
			}
		
	}}
	else {
		JOptionPane.showMessageDialog(null, "choose your target first");
	}}
	else if (hero1 instanceof Hunter) {
		try {
			((Hunter)hero1).useHeroPower();
			updatefield1curr();
			updatefield2opp();
			updatehero11();
			updateStringsH1curr();
			updateStringsH2opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
else if (hero1 instanceof Paladin) {
	try {
		((Paladin)hero1).useHeroPower();
		updatefield1curr();
		updatefield2opp();
		updatehero11();
		updateStringsH1curr();
		updateStringsH2opp();
	} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
			| FullFieldException | CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
		
	}
else if (hero1 instanceof Priest ) {
	if(!(heropowertarget.isEmpty())) {
		if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())||heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName())) 
		{
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName())) {
		try {
			((Priest) hero1).useHeroPower(hero2);
			updatefield1curr();
			updatefield2opp();
			updatehero11();
			updateStringsH1curr();
			updateStringsH2opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		}
			else {
				try {
					((Priest) hero1).useHeroPower(hero1);
					updatefield1curr();
					updatefield2opp();
					updatehero11();
					updateStringsH1curr();
					updateStringsH2opp();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		
		
		}
		
	else {
		if(field2.contains(heropowertarget.get(0))) {
		try {
			((Priest) hero1).useHeroPower(hero2.getField().get(field2.indexOf(heropowertarget.get(0))));
			updatefield1curr();
			updatefield2opp();
			updatehero11();
			updateStringsH1curr();
			updateStringsH2opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	
	}
		else {
			try {
				((Priest) hero1).useHeroPower(hero1.getField().get(field1.indexOf(heropowertarget.get(0))));
				updatefield1curr();
				updatefield2opp();
				updatehero11();
				updateStringsH1curr();
				updateStringsH2opp();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	
	}	
}else {
	JOptionPane.showMessageDialog(null, "choose your target first");
	
}
	}
else if (hero1 instanceof Warlock ) {
	try {
		((Warlock)hero1).useHeroPower();
		updatefield1curr();
		updatefield2opp();
		updatehero11();
		updateStringsH1curr();
		updateStringsH2opp();
	} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
			| FullFieldException | CloneNotSupportedException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
		
	
}

public void hero2power() {
	if (hero2 instanceof Mage ) {
		
		if(!(heropowertarget.isEmpty())) {
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())||heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName()))
			{
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())) {
			try {
				((Mage) hero2).useHeroPower(hero1);
				updatefield2curr();
				updatefield1opp();
				updateStringsH2curr();
				updateStringsH1opp();
				updatehero2();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}}
			else {
				try {
					((Mage) hero2).useHeroPower(hero2);
					updatefield2curr();
					updatefield1opp();
					updateStringsH2curr();
					updateStringsH1opp();
					updatehero2();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
			}
}
		else {
			if(field1.contains(heropowertarget.get(0))) {
			try {
				((Mage) hero2).useHeroPower(hero1.getField().get(field1.indexOf(heropowertarget.get(0))));
				updatefield2curr();
				updatefield1opp();
				updateStringsH2curr();
				updatehero2();
				updateStringsH1opp();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
	}
			
			else {
				try {
					((Mage) hero2).useHeroPower(hero2.getField().get(field2.indexOf(heropowertarget.get(0))));
					updatefield2curr();
					updatefield1opp();
					updateStringsH2curr();
					updatehero2();
					updateStringsH1opp();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		}
			
	}
	else {
		JOptionPane.showMessageDialog(null, "choose your target first");
	}
	
	
	
	}
	else if (hero2 instanceof Hunter) {
		try {
			((Hunter)hero2).useHeroPower();
			updatefield2curr();
			updatefield1opp();
			updateStringsH2curr();
			updatehero2();
			updateStringsH1opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
else if (hero2 instanceof Paladin) {
	try {
		((Paladin)hero2).useHeroPower();
		updatefield2curr();
		updatefield1opp();
		updateStringsH2curr();
		updatehero2();
		updateStringsH1opp();
	} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
			| FullFieldException | CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
		
	}
else if (hero2 instanceof Priest ) {
	if(!(heropowertarget.isEmpty())) {
		if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())||heropowertarget.get(0).getText().equalsIgnoreCase(hero2.getName())) 
		{
			if(heropowertarget.get(0).getText().equalsIgnoreCase(hero1.getName())) {
		try {
			((Priest) hero2).useHeroPower(hero1);
			updatefield2curr();
			updatefield1opp();
			updatehero2();
			updateStringsH2curr();
			updateStringsH1opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}}
			else{
				try {
					((Priest) hero2).useHeroPower(hero2);
					updatefield2curr();
					updatefield1opp();
					updatehero2();
					updateStringsH2curr();
					updateStringsH1opp();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			}
	else {
		if(field1.contains(heropowertarget.get(0))) {
		try {
			((Priest) hero2).useHeroPower(hero1.getField().get(field1.indexOf(heropowertarget.get(0))));
			updatefield2curr();
			updatefield1opp();
			updatehero2();
			updateStringsH2curr();
			updateStringsH1opp();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}}
		else {

			try {
				((Priest) hero2).useHeroPower(hero2.getField().get(field2.indexOf(heropowertarget.get(0))));
				updatefield2curr();
				updatefield1opp();
				updatehero2();
				updateStringsH2curr();
				updateStringsH1opp();
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		
		}
		
		
		
		
		
	}
	}
	else {
		JOptionPane.showMessageDialog(null, "choose your target first");
	}
}
else if (hero2 instanceof Warlock ) {
	try {
		((Warlock)hero2).useHeroPower();
		updatefield2curr();
		updatefield1opp();
		updatehero2();
		updateStringsH2curr();
		updateStringsH1opp();
	} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
			| FullFieldException | CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	
}
		
	
}
public void hero1castspell() {
	if(!(usespells1.isEmpty())) {
		if(hero1.getHand().get(hand1.indexOf(usespells1.get(0))) instanceof AOESpell  ) {
		try {
			hero1.castSpell((AOESpell)(hero1.getHand().get(hand1.indexOf(usespells1.get(0)))), hero2.getField());
		} catch (NotYourTurnException | NotEnoughManaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
			
			updatefield1curr();
			updatefield2opp();
			updateStringsH1curr();
			updateStringsH2opp();
			updatehero11();
		}
		else	if(hero1.getHand().get(hand1.indexOf(usespells1.get(0))) instanceof FieldSpell ) {
			
			try {
				hero1.castSpell((FieldSpell) hero1.getHand().get(hand1.indexOf(usespells1.get(0))));
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			updatefield1curr();
			updatefield2opp();
			updateStringsH1curr();
			updateStringsH2opp();
			updatehero11();
		}
		else if(hero1.getHand().get(hand1.indexOf(usespells1.get(0))) instanceof  HeroTargetSpell) {
			
			try {
				hero1.castSpell((HeroTargetSpell)(hero1.getHand().get(hand1.indexOf(usespells1.get(0)))), hero2);
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			updatefield1curr();
			updatefield2opp();
			updateStringsH1curr();
			updateStringsH2opp();
			updatehero11();
		}
		else if(hero1.getHand().get(hand1.indexOf(usespells1.get(0))) instanceof  LeechingSpell) {
		if(!(target2.isEmpty())) {	
			try {
				hero1.castSpell((LeechingSpell)(hero1.getHand().get(hand1.indexOf(usespells1.get(0)))), hero2.getField().get(field2.indexOf(target2.get(0))));
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			updatefield1curr();
			updatefield2opp();
			updateStringsH1curr();
			updateStringsH2opp();
			updatehero11();}
		else {
			JOptionPane.showMessageDialog(null, "choose target first");
		}
		}
		else	if(hero1.getHand().get(hand1.indexOf(usespells1.get(0))) instanceof  MinionTargetSpell) {
			if(!(target2.isEmpty())) {
				try {
					hero1.castSpell((MinionTargetSpell) hero1.getHand().get(hand1.indexOf(usespells1.get(0))), hero2.getField().get(field2.indexOf(target2.get(0))));
				} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			
			updatefield1curr();
			updatefield2opp();
			updateStringsH1curr();
			updateStringsH2opp();
			updatehero11();
		}}
		else {
			JOptionPane.showMessageDialog(null, "choose target first");
			
		}
		
		
		
	}
	else {
		JOptionPane.showMessageDialog(null, "choose spell first");
	}
	
}
public void hero2castspell() {
	if(!(usespells2.isEmpty())) {
		
		if(hero2.getHand().get(hand2.indexOf(usespells2.get(0))) instanceof AOESpell ) {
			try {
				hero2.castSpell((AOESpell)(hero2.getHand().get(hand2.indexOf(usespells2.get(0)))), hero1.getField());
			
				updatefield2curr();
				updatefield1opp();
				updatehero2();
				updateStringsH1opp();
				updateStringsH2curr();
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
		
		else if(hero2.getHand().get(hand2.indexOf(usespells2.get(0))) instanceof FieldSpell ) {
			try {
				hero2.castSpell((FieldSpell) hero2.getHand().get(hand2.indexOf(usespells2.get(0))));
			
				updatefield2curr();
				updatefield1opp();
				updatehero2();
				updateStringsH1opp();
				updateStringsH2curr();
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		
		else	if(hero2.getHand().get(hand2.indexOf(usespells2.get(0))) instanceof HeroTargetSpell ) {
			try {
				hero2.castSpell((HeroTargetSpell)(hero2.getHand().get(hand2.indexOf(usespells2.get(0)))), hero1);
		
				updatefield2curr();
				updatefield1opp();
				updatehero2();
				updateStringsH1opp();
				updateStringsH2curr();
			} catch (NotYourTurnException | NotEnoughManaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

		
		else	if(hero2.getHand().get(hand2.indexOf(usespells2.get(0))) instanceof LeechingSpell ) {
			if(!(target1.isEmpty())) {
				try {
					hero2.castSpell((LeechingSpell)(hero2.getHand().get(hand2.indexOf(usespells2.get(0)))), hero1.getField().get(field1.indexOf(target1.get(0))));
				
					updatefield2curr();
					updatefield1opp();
					updatehero2();
					updateStringsH1opp();
					updateStringsH2curr();
				} catch (NotYourTurnException | NotEnoughManaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				}
			else {
				JOptionPane.showMessageDialog(null, "choose target first");
			}
			
		}

		
		else	if(hero2.getHand().get(hand2.indexOf(usespells2.get(0))) instanceof MinionTargetSpell ) {
			
			if(!(target1.isEmpty())) {
				try {
					hero2.castSpell((MinionTargetSpell)(hero2.getHand().get(hand2.indexOf(usespells2.get(0)))), hero1.getField().get(field1.indexOf(target1.get(0))));
				updatefield2curr();
				updatefield1opp();
				updatehero2();
				updateStringsH1opp();
				updateStringsH2curr();
				
				} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "choose target first");
			}
		}
		
		
		
		
		
		
	}
	else {
		JOptionPane.showMessageDialog(null, "choose spell first");
	}
	
	
}



public void onGameOver() {
	if(game.getCurrentHero().getCurrentHP()<=0&&!(game.getOpponent().getCurrentHP()<=0)) {
		view.repaint();
		view.revalidate();
		view.dispose();
		new GameEnd (game.getOpponent());
		
	}
	else if(game.getOpponent().getCurrentHP()<=0&&!(game.getCurrentHero().getCurrentHP()<=0)) {
		view.repaint();
		view.revalidate();
		view.dispose();
		new GameEnd (game.getCurrentHero());
		
	}
	else if(game.getCurrentHero().getCurrentHP()<=0&&game.getOpponent().getCurrentHP()<=0) {
		view.repaint();
		view.revalidate();
		view.dispose();
		new DrawGame();
	}
	
}

public void actionPerformed(ActionEvent e) {
	JButton b= (JButton) e.getSource();
	

	if(b.getText().equalsIgnoreCase("end turn")&&herobuttons1.contains(b)&&hero1==game.getCurrentHero()) {
		
		hero1endturn(e);
		
	}
if(b.getText().equalsIgnoreCase("end turn")&&herobuttons2.contains(b)&&hero2==game.getCurrentHero()) {
		
		hero2endturn(e);   
		
	}

if(hand1.contains(b)&&hero1==game.getCurrentHero()) {
	if(!(spells1.contains(b))) {
	try {
		hero1.playMinion((Minion) hero1.getHand().get(hand1.indexOf(b)));
		updatefield1curr();
		updateStringsH1curr();
		updatehero11();
	} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e1.getMessage());
		e1.printStackTrace();
	}
	}
	else {
		if(usespells1.isEmpty())
			usespells1.add(b);
			else {
				usespells1.remove(0);
				usespells1.add(b);
			}
	}
	
}
if(hand2.contains(b)&&hero2==game.getCurrentHero()) {
	if(!(spells2.contains(b))) {
	try {
		hero2.playMinion((Minion) hero2.getHand().get(hand2.indexOf(b)));
		updatefield2curr();
		updateStringsH2curr();
		updatehero2();
	} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e1.getMessage());
		e1.printStackTrace();
	}}
	else {
		if(usespells2.isEmpty())
		usespells2.add(b);
		else {
			usespells2.remove(0);
			usespells2.add(b);
		}
	}
	
}

if(field1.contains(b)&&hero1==game.getCurrentHero()) {
	if(attacker1.isEmpty())
		attacker1.add(b);
	else {
		attacker1.remove(0);
		attacker1.add(b);
	}
}
if(field2.contains(b)&&hero2==game.getCurrentHero()) {
	if(attacker2.isEmpty())
		attacker2.add(b);
	else {
		attacker2.remove(0);
		attacker2.add(b);
	}
}
if(field1.contains(b)&&!(hero1==game.getCurrentHero())) {
	if(target1.isEmpty())
		target1.add(b);
	else {
		target1.remove(0);
		target1.add(b);
	}
}

if(field2.contains(b)&&!(hero2==game.getCurrentHero())) {
	if(target2.isEmpty()) {
		target2.add(b);
	
	}else {
		target2.remove(0);
		target2.add(b);
	}
}
if(herobuttons1.contains(b)&&b.getText().equalsIgnoreCase("attack")&&hero1==game.getCurrentHero()) {
	hero1attack();
	onGameOver();
}
if(herobuttons2.contains(b)&&b.getText().equalsIgnoreCase("attack")&&hero2==game.getCurrentHero()) {
	hero2attack();
	onGameOver();
}

if(herobuttons1.contains(b)&&b.getText().equalsIgnoreCase("power")&&hero1==game.getCurrentHero()) {
	hero1power();
	onGameOver();
}
if(herobuttons2.contains(b)&&b.getText().equalsIgnoreCase("power")&&hero2==game.getCurrentHero()) {
	hero2power();
	onGameOver();
}
if(herobuttons1.contains(b)&&b.getText().equalsIgnoreCase("use spell")&&hero1==game.getCurrentHero()) {
	hero1castspell();
	onGameOver();
}
if(herobuttons2.contains(b)&&b.getText().equalsIgnoreCase("use spell")&&hero2==game.getCurrentHero()) {
	hero2castspell();
	onGameOver();
}
if(b.getText().equalsIgnoreCase(hero1.getName())&&!(hero1==game.getCurrentHero())) {
	if(target1.isEmpty())
		target1.add(b);
	else {
		target1.remove(0);
		target1.add(b);
	}
}
if(b.getText().equalsIgnoreCase(hero2.getName())&&!(hero2==game.getCurrentHero())) {
	if(target2.isEmpty())
		target2.add(b);
	else {
		target2.remove(0);
		target2.add(b);
	}}
	if(game.getCurrentHero() instanceof Priest || game.getCurrentHero() instanceof Mage ) {
		if(b.getText().equalsIgnoreCase(hero2.getName()) || b.getText().equalsIgnoreCase(hero1.getName()) || field2.contains(b) ||field1.contains(b)  ) {
			if(heropowertarget.isEmpty()) {
				heropowertarget.add(b);				
			}
			else {
				heropowertarget.remove(0);
				heropowertarget.add(b);
			}
		}
		
	}

onGameOver();
repaint();
revalidate();
view.repaint();
view.revalidate();

}
	
public static  void main (String[]args) throws Exception {

	new gameStartWindow();
	
}
}
