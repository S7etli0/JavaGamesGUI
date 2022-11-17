package allgames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lineGame.AppFourLine;
import matchGame.AppMatch;
import minesGame.AppMines;
import seaGame.AppSea;
import shipGame.AppBattleships;

public class GameTable extends JPanel implements ActionListener{

	AppSea sea;
	AppMines mines;
	AppMatch match;
	AppBattleships ships;
	AppFourLine line;

	JButton minesTAB;
	JButton seaTAB;
	JButton matchTAB;
	JButton lineTAB;
	JButton shipsTAB;
	
	public GameTable () {
			
		int hor = 150;
		int ver = 40;
		int loc = 190;
		
		this.setLayout(null);
		
		minesTAB = new JButton("MinesTable");
		minesTAB.setSize(hor,ver);
		minesTAB.setLocation(loc,40);
		minesTAB.addActionListener(this);
		
		seaTAB = new JButton("TicTacTable");
		seaTAB.setSize(hor,ver);
		seaTAB.setLocation(loc,110);
		seaTAB.addActionListener(this);
		
		matchTAB = new JButton("MatchTable");
		matchTAB.setSize(hor,ver);
		matchTAB.setLocation(loc,180);
		matchTAB.addActionListener(this);		
		
		lineTAB = new JButton("4LineTable");
		lineTAB.setSize(hor,ver);
		lineTAB.setLocation(loc,250);
		lineTAB.addActionListener(this);
		
		shipsTAB = new JButton("ShipsTable");
		shipsTAB.setSize(hor,ver);
		shipsTAB.setLocation(loc,320);
		shipsTAB.addActionListener(this);
		
		this.add(shipsTAB);
		this.add(matchTAB);
		this.add(seaTAB);
		this.add(lineTAB);
		this.add(minesTAB);


		this.setVisible(true);
		this.setSize(550,475);	

	}
	
	public static void main (String[] args) {
		
		new GameFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==seaTAB) {

			sea = new AppSea();
		}	
		
		if(e.getSource()==minesTAB) {

			mines = new AppMines();
		}	
		
		if(e.getSource()==matchTAB) {

			match = new AppMatch();
		}	
		
		if(e.getSource()==lineTAB) {

			line = new AppFourLine();
		}
		
		if(e.getSource()==shipsTAB) {

			ships = new AppBattleships();
		}			
	}	
}
