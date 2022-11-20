package Svetoslav.Kochanov;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComboPokerHands extends JFrame{

	JLabel lblText1;
	JLabel lblText2;
	JLabel lblText3;
	JLabel lblText4;
	JLabel lblText5;
	JLabel lblpic;
	ImageIcon pokerPic;

	JComboBox cardSet1;
	JComboBox cardSet2;
	JComboBox cardSet3;
	JComboBox cardSet4;
	JComboBox cardSet5;
	
	JComboBox cardSuit1;
	JComboBox cardSuit2;
	JComboBox cardSuit3;
	JComboBox cardSuit4;
	JComboBox cardSuit5;

	JPanel bottom;
	JButton btn;
	JTextField txtHand;   		// What is the POKER combination 
	JPanel deck;
	
	public ComboPokerHands() {		
		
		setLayout(new FlowLayout());
		
		lblpic = new JLabel() ;
		pokerPic = new ImageIcon("poker.jpg") ;
		lblpic.setIcon(pokerPic);
		
		deck  = new JPanel();
		deck.setLayout(new GridLayout(5,3,5,5));

		lblText1  = new JLabel("1st Card");
		lblText2  = new JLabel("2nd Card");
		lblText3  = new JLabel("3rd Card");
		lblText4  = new JLabel("4th Card");
		lblText5  = new JLabel("5th Card");

		String ranks [] = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		
		cardSet1 = new JComboBox(ranks);
		cardSet2 = new JComboBox(ranks);
		cardSet3 = new JComboBox(ranks);
		cardSet4 = new JComboBox(ranks);
		cardSet5 = new JComboBox(ranks);
	
		bottom  = new JPanel();
		bottom.setLayout(new FlowLayout());

		txtHand = new JTextField(15);
		txtHand.setEditable(false);
		txtHand.setHorizontalAlignment(JTextField.CENTER);
		txtHand.setPreferredSize(new Dimension(270, 25));
		txtHand.setFont(new Font("Arial", Font.BOLD, 12));
		
		deck.setBackground(Color.WHITE);
		deck.setPreferredSize(new Dimension(300, 145));
		bottom.setBackground(Color.WHITE);
		bottom.setPreferredSize(new Dimension(300, 35));
		getContentPane().setBackground(Color.BLACK);

		String suite [] = {"diamonds", "clubs", "hearts","spades"};
		
		cardSuit1 = new JComboBox(suite);
		cardSuit2 = new JComboBox(suite);
		cardSuit3 = new JComboBox(suite);
		cardSuit4 = new JComboBox(suite);
		cardSuit5 = new JComboBox(suite);

		btn = new JButton("Check Your Hand");
		btn.addActionListener (e -> {
		
			String Card1 = cardSet1.getSelectedItem().toString();
			String Card2 = cardSet2.getSelectedItem().toString();
			String Card3 = cardSet3.getSelectedItem().toString();
			String Card4 = cardSet4.getSelectedItem().toString();
			String Card5 = cardSet5.getSelectedItem().toString();

			String Suit1 = cardSuit1.getSelectedItem().toString();
			String Suit2 = cardSuit2.getSelectedItem().toString();
			String Suit3 = cardSuit3.getSelectedItem().toString();
			String Suit4 = cardSuit4.getSelectedItem().toString();
			String Suit5 = cardSuit5.getSelectedItem().toString();
			
			String cards [] = {Card1,Card2,Card3,Card4,Card5};
			String suits [] = {Suit1,Suit2,Suit3,Suit4,Suit5};
			String hand = "";
									
//			Checking for false combinations		
			
			int M = ComboPokerMethods.FalseCombos(cards, suits);
	
			if (M>1) {
				hand ="made an Error";
			
			} else {

				int same = ComboPokerMethods.SameSuits(suits);
				int str = ComboPokerMethods.Straight(ranks, cards);	

				if (same==5) {
					
					int rc = ComboPokerMethods.RoyalFlush(cards);
				
					if ((rc==5)) {
						hand ="a Royal Flush";
					
					} else {
					
						if ((str==5)) {
							hand ="a Straight Flush";
						
						} else {
							hand ="a Flush";
						}	
					}
				} else {
			
					if ((str==5)){
						hand ="a Straight";
					
					} else {
						int max2 = 1;
						String sign = "";
						int max1 = ComboPokerMethods.MaxCounter(cards,sign);
						sign = ComboPokerMethods.RankCounter(cards);
						max2 = ComboPokerMethods.MaxCounter(cards,sign);			
					
							if (max2==2) {
								if ((max1==3)) {	
									hand = "a Full House";
								}	
								if ((max1==2)) {
									hand = "Two Pairs";
								}				
							} else {				
								if (max1>2) {
										
									if (max1==4) {
										hand = "Four of a Kind";
									} else {
										if (max1==3){
											hand ="Three of a Kind";
										}
									}				
								} else  {
									if (max1==2){
										hand ="One Pair";
									} else  {
										if (max1==1){
											hand ="a High Card";
										} else  {
											hand ="made an Error";						
										}
									}
								}
							}	
						}		
					}					
				}
				
			txtHand.setText(" You have "+hand);		
		
		});	
		
		setTitle("ComboPokerHands");
		setSize(330,390);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(lblpic);
		
		deck.add(lblText1);
		deck.add(cardSet1);
		deck.add(cardSuit1);

		deck.add(lblText2);
		deck.add(cardSet2);
		deck.add(cardSuit2);
		
		deck.add(lblText3);
		deck.add(cardSet3);
		deck.add(cardSuit3);
		
		deck.add(lblText4);
		deck.add(cardSet4);	
		deck.add(cardSuit4);

		deck.add(lblText5);
		deck.add(cardSet5);		
		deck.add(cardSuit5);

		add(deck);
		bottom.add(btn);
		bottom.add(txtHand);
		add(bottom);
		txtHand.setEnabled(true);
	}
	
	public static void main(String[] args) {

		ComboPokerHands hand = new ComboPokerHands();
	}
	
}
