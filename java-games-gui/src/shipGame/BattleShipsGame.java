package shipGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import allgames.ComboMethods;
import allgames.GameFrame;

public class BattleShipsGame extends JPanel {
	
	// Game Timer
	static int limit = 30;
	static int sec = limit;
	
	JPanel leftPanel;
	JPanel rightPanel;
	JPanel panel1;
	JPanel panel2;
	
	static int space = 4;
	static Random ran = new Random();
	static int turn = 1;
	static int score1 = 0;
	static int score2 = 0;
	static int Play1win = 0;
	static int Play2win = 0;
	static boolean winner = true;
	static int round = 1;
	
	static JTextField scoreP1;
	static JTextField scoreP2;
	static JButton button1 [][] = new JButton [space][space];
	static JButton button2 [][] = new JButton [space][space];
	static String map1 [][] = new String [space][space];
	static String map2 [][] = new String [space][space];
	JButton resButton;
	
	JPanel startPanel;
	JLabel Play1;
	TextField namePlay1;
	JLabel Play2;
	TextField namePlay2;
	JButton pressPlay;
	JTextField infoField;
	static String next;
	static int playrole = 0;
	
	static int draw = 0;
	JLabel lblScoreDraw;	
	static JTextField scoreDraw;
	
	
	public BattleShipsGame () {
		
		startPanel = new JPanel ();
		startPanel.setLayout(new BorderLayout());
		
		JPanel firstPanel = new JPanel ();
		JPanel secPanel = new JPanel ();
		
		Play1 = new JLabel("Player1");
		namePlay1 = new TextField(10);
		Play2 = new JLabel("Player2");
		namePlay2 = new TextField(10);
		pressPlay = new JButton("Press to Play");									
				
		firstPanel.add(Play1);
		firstPanel.add(namePlay1);
		secPanel.add(Play2);
		secPanel.add(namePlay2);
		
		JPanel pnlPic = new JPanel();
		JLabel lblpic = new JLabel();	
		pnlPic.add(lblpic);	
		ImageIcon loginPic = new ImageIcon("pics/battleships.jpg");
		lblpic.setIcon(loginPic);
		
		JPanel players = new JPanel();
		players.setLayout(new BorderLayout());
		players.add(firstPanel, BorderLayout.NORTH);
		players.add(secPanel, BorderLayout.CENTER);	
		
		startPanel.add(pnlPic, BorderLayout.NORTH);
		startPanel.add(players, BorderLayout.CENTER);
		startPanel.add(pressPlay, BorderLayout.SOUTH);
		this.add(startPanel);
								
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(space,space));
		button1 = ComboMethods.setBase(button1, panel1,"X",50);		
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(space,space));
		button2 = ComboMethods.setBase(button2, panel2,"X",50);			
				
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());		
		
		JTextField infoGame = new JTextField(6);
		infoGame.setText("Game "+round+"");
		infoGame.setHorizontalAlignment(JTextField.CENTER); 
		infoGame.setEditable(false);
		infoGame.setBackground(Color.WHITE);
		
		JLabel infoRound = new JLabel("Stage");
		infoField = new JTextField(20);
		infoField.setHorizontalAlignment(JTextField.CENTER); 
		infoField.setEditable(false);
		infoField.setBackground(Color.WHITE);
		
		JLabel time = new JLabel("time");
		JTextField txtTime = new JTextField(3);
		txtTime.setHorizontalAlignment(JTextField.CENTER); 
		txtTime.setEditable(false);
		txtTime.setBackground(Color.WHITE);
		
		infoPanel.add(infoGame);
		infoPanel.add(infoRound);
		infoPanel.add(infoField);
		infoPanel.add(time);
		infoPanel.add(txtTime);	
		
		JPanel scorePanel1 = new JPanel();							
		scorePanel1.setLayout(new FlowLayout());
		
		JPanel scorePanel2 = new JPanel();							
		scorePanel2.setLayout(new FlowLayout());
		
		JLabel lblScoreP1 = new JLabel();
		JLabel lblScoreP2 = new JLabel();		
		
		scoreP1 = new JTextField(3);
		scoreP2 = new JTextField(3);
		scoreP1.setEditable(false);
		scoreP1.setBackground(Color.WHITE);
		scoreP1.setHorizontalAlignment(JTextField.CENTER); 

		scoreP2.setEditable(false);
		scoreP2.setBackground(Color.WHITE);
		scoreP2.setHorizontalAlignment(JTextField.CENTER); 

		JLabel shipsPlay1 = new JLabel("ships");
		JTextField shipsDownPlay1 = new JTextField(3);
		JLabel shipsPlay2 = new JLabel("ships");
		JTextField shipsDownPlay2 = new JTextField(3);
		
		shipsDownPlay1.setEditable(false);
		shipsDownPlay1.setBackground(Color.WHITE);
		shipsDownPlay1.setHorizontalAlignment(JTextField.CENTER); 

		shipsDownPlay2.setEditable(false);
		shipsDownPlay2.setBackground(Color.WHITE);
		shipsDownPlay2.setHorizontalAlignment(JTextField.CENTER); 

		scoreP1.setText("0");
		scoreP2.setText("0");
		
		scorePanel1.add(lblScoreP1);
		scorePanel1.add(scoreP1);
		scorePanel1.add(shipsPlay1);
		scorePanel1.add(shipsDownPlay1);
		
		scorePanel2.add(lblScoreP2);
		scorePanel2.add(scoreP2);
		scorePanel2.add(shipsPlay2);
		scorePanel2.add(shipsDownPlay2);
 
		
  			for (int i = 0; i<space; i++) {
				
				for (int j = 0; j<space; j++) {	
		
						JButton btn1 = button1[i][j];													
					
						int a1 = i;
						int b1 = j;
						
						btn1.addActionListener (new ActionListener() {						
																					
							public void actionPerformed(ActionEvent a) {
								
								
								if (winner==false) {
									
									if ((playrole%2 == 0)&&(btn1.getText().equals("X"))) {								
										
										String uno1 = ComboMethods.setSign(map1,a1,b1);			
										btn1.setText(uno1);
										
										String moveon = MovingOn(namePlay2.getText());
										infoField.setText(moveon);
										
										if (uno1.equals("Q ")) {										
											score1++;
											shipsDownPlay1.setText(score1+"");
											btn1.setForeground(Color.red);

										}									
									} 
									
									if (score1==5) {
										
										String winning = WinBattle(namePlay1.getText());
										Play1win++;
										scoreP1.setText(Play1win+"");
										infoField.setText(winning);										
									}									

									if (winner==true) {
										
										WinnerData(namePlay1.getText(),namePlay1.getText(),namePlay2.getText());
									}
								}															
							}						
					});							
						
						JButton btn2 = button2[i][j];													
												
						int a2 = i;
						int b2 = j;
						
						btn2.addActionListener (new ActionListener() {						
																					
							public void actionPerformed(ActionEvent a) {
											
								if (winner==false) {
									
									if ((playrole%2 != 0)&&(btn2.getText().equals("X"))) {								
										
										String uno2 = ComboMethods.setSign(map2,a2,b2);									
										btn2.setText(uno2);
										
										String moveon = MovingOn(namePlay1.getText());
										infoField.setText(moveon);
										
										if (uno2.equals("Q ")) {										
											score2++;
											shipsDownPlay2.setText(score2+"");
											btn2.setForeground(Color.red);
										}	
									} 
									
									if (score2==5) {
										
										String winning = WinBattle(namePlay2.getText());
										Play2win++;
										scoreP2.setText(Play2win+"");
										infoField.setText(winning);	
									}	
									
									if (winner==true) {
										
										WinnerData(namePlay2.getText(),namePlay1.getText(),namePlay2.getText());
									}							
								}	
							}						
					});	
				}
  			}
			
			resButton = new JButton("RESET");
			resButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub							
										
					
					if (winner!=false) {												
						
						GameReset();
						
						txtTime.setText(sec+"");
						
						Thread go = new Thread() {
							
							@Override 
							public void run() {
								
								while (winner==false) {												
									
									if (sec==0) {
										
										String input = TimeOut(namePlay1.getText(),namePlay2.getText());
										infoField.setText(input);																
									}
									
									try {
										Thread.sleep(1000);
										txtTime.setText(sec+"");
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
									}	
									
									sec--;
								} 
							}
						};
						go.start();	
						
						
						shipsDownPlay1.setText("0");
						shipsDownPlay2.setText("0");
						
						button1 = ComboMethods.hideBase(button1, "X");
						button2 = ComboMethods.hideBase(button2, "X");

						
						map1 = ComboMethods.setShips(map1,ran);
						map2 = ComboMethods.setShips(map2,ran);

						round++;				
						infoGame.setText("Game "+round+"");
						infoField.setText("Round "+(turn)+": Your turn "+next);
					}
				}		
			});

			
			JPanel middle = new JPanel();
			middle.setLayout(new BorderLayout());
			
			pressPlay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub							
					
					
					String naming1 = namePlay1.getText();
					String naming2 = namePlay2.getText();
					boolean playgame = ComboMethods.Message2(naming1,naming2);
					
					if (playgame==true) {					
						
						next=null;
						playrole = 0;
						Play1win=0;
						scoreP1.setText(Play1win+"");
						Play2win=0;
						scoreP2.setText(Play2win+"");
						
						draw = 0;
						GameReset();

						txtTime.setText(sec+"");
						
						Thread go = new Thread() {
							
							@Override 
							public void run() {
								
								while (winner==false) {												
									
									if (sec==0) {
																												
										String input = TimeOut(namePlay1.getText(),namePlay2.getText());
										infoField.setText(input);																		
									}
									
									try {
										Thread.sleep(1000);
										txtTime.setText(sec+"");
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
									}	
									
									sec--;
								} 
							}
						};
						go.start();	
						
						
						shipsDownPlay1.setText("0");
						shipsDownPlay2.setText("0");
						
						button1 = ComboMethods.hideBase(button1, "X");
						button2 = ComboMethods.hideBase(button2, "X");
						
						map1 = ComboMethods.setShips(map1,ran);
						map2 = ComboMethods.setShips(map2,ran);
				
						round=1;

						infoGame.setText("Game "+round+"");
						infoField.setText("Round "+(turn)+": Your turn "+naming1);
						
						lblScoreP1.setText(namePlay1.getText()+"");
						lblScoreP2.setText(namePlay2.getText()+"");

						startPanel.setVisible(false);	
						add(infoPanel, BorderLayout.NORTH);
						add(middle, BorderLayout.CENTER);			
						add(resButton, BorderLayout.SOUTH);
					}				
				}		
			});
						
			this.setSize(475,325);
			this.setLocation(25,50);
			this.setVisible(true);
	
			
		leftPanel.add(panel1, BorderLayout.CENTER);	
		rightPanel.add(panel2, BorderLayout.CENTER);
		
		JLabel spacebar = new JLabel("   ");
		middle.add(leftPanel, BorderLayout.WEST);
		middle.add(rightPanel, BorderLayout.EAST);
		middle.add(spacebar, BorderLayout.CENTER);
		
		JPanel allscores = new JPanel();
		allscores.setLayout(new FlowLayout());
		allscores.add(scorePanel1);
		allscores.add(scorePanel2);
		
		lblScoreDraw = new JLabel("Draw");	
		scoreDraw = new JTextField(3);
		scoreDraw.setText(draw+"");
		scoreDraw.setEditable(false);
		scoreDraw.setBackground(Color.WHITE);
		scoreDraw.setHorizontalAlignment(JTextField.CENTER); 
		allscores.add(lblScoreDraw);
		allscores.add(scoreDraw);
		middle.add(allscores, BorderLayout.SOUTH);
		
	}	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new GameFrame();
	}
		

	public static void WinnerData(String currentPlayer, String P1, String P2) {
		
		String gameDate = ComboMethods.DateAndTime(); 
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		String score = P1 +" - "+Play1win+" : "+Play2win+" - "+ P2;		
		String playing = "Game "+round;
		BattleShipsBoard board = new BattleShipsBoard(playing+": "+currentPlayer,(turn),score,draw,gameDate);
		
		tx.begin();

		em.persist(board);
		tx.commit();

		em.close();
		emf.close();		
	}
	
	public static boolean close() {
		
		boolean ending = winner;
	
		return ending;
	}
	
	public static String TimeOut(String P1,String P2) {
		
		if (turn!=1) {turn--;}
		winner=true;
		String text;
	
		if (score1>score2) {
			
			text = ("Round "+(turn)+": "+P1+" is the winner");
			Play1win++;
			scoreP1.setText(Play1win+"");
			WinnerData(P1,P1,P2);
			
		} else if (score1<score2) {
			
			text = ("Round "+(turn)+": "+P2+" is the winner");
			Play2win++;
			scoreP2.setText(Play2win+"");
			WinnerData(P2,P1,P2);
			
		} else {
			
			draw++;
			scoreDraw.setText(draw+"");
			text = ("Round "+(turn)+" : There is no winner!");
			WinnerData("draw",P1,P2);		
			if(next==null) {next = P1;}		
		}	
		
		return text;
	}

	public static String WinBattle(String P) {
	
		turn--;
		String anonce = "Round "+(turn)+": "+P+" is the winner";
		winner = true;
	
		return anonce;
	}
	
	public static String MovingOn(String player) {
				
		turn++;
		playrole++;
		String forward = "Round "+(turn)+": Your turn "+player;
		next = player;
		return forward;
	}
	
	public static void GameReset() {
			
		sec=limit;
		winner = false;
		score1 = 0;
		score2 = 0;	
		turn = 1;
	}
}
