package matchGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import allgames.ComboMethods;
import allgames.GameFrame;
import allgames.GameTable;


public class MatchGame extends JPanel{
	
		// Game Timer
		static int limit = 25;
		static int sec = limit;
	
		static JButton btn2;
		static JButton btn1;
		static int count = 0;
		JPanel panel;
		static String x = null;
		static String y = null;		
		static int A=0, B=0, C=0, D=0;

		static Random gen =  new Random();
		static int space = 4;
		static JButton button [][] = new JButton [space][space];
		static String box [][] = new String [space][space];
		static boolean stop = true;
		static int pair = 8;
		static int round = 1;
		static int turn = 1;
		static int fails = 0;
		static int score = 0;
		
		JPanel startPanel;
		JPanel firstPanel;
		JLabel Play1;
		TextField namePlay1;
		JButton pressPlay;
		JPanel state; 
		static String match = "won"; 	
		
		
		public MatchGame() {
			
			startPanel = new JPanel ();
			startPanel.setLayout(new BorderLayout());
			
			JPanel firstPanel = new JPanel ();
			
			Play1 = new JLabel("Player1");
			namePlay1 = new TextField(10);

			pressPlay = new JButton("Press to Play");									
					
			firstPanel.add(Play1);
			firstPanel.add(namePlay1);

			JPanel pnlPic = new JPanel();
			JLabel lblpic = new JLabel();	
			pnlPic.add(lblpic);	
			ImageIcon loginPic = new ImageIcon("pics/2by2.jpg");
			lblpic.setIcon(loginPic);
			startPanel.add(pnlPic, BorderLayout.NORTH);

			
			startPanel.add(firstPanel, BorderLayout.CENTER);
			startPanel.add(pressPlay, BorderLayout.SOUTH);
			this.add(startPanel);			
			
			JPanel topPnl = new JPanel(); 
			topPnl.setLayout(new FlowLayout());
			
			JLabel gameName = new JLabel("MatchGame");
			JTextField gameNum = new JTextField(5);

			gameNum.setEditable(false);
			gameNum.setBackground(Color.WHITE);
			gameNum.setHorizontalAlignment(JTextField.CENTER); 
			
			JLabel time = new JLabel("time");
			JTextField txtTime = new JTextField(3);
			txtTime.setEditable(false);
			txtTime.setBackground(Color.WHITE);
			txtTime.setHorizontalAlignment(JTextField.CENTER); 

			topPnl.add(gameName);
			topPnl.add(gameNum);
			topPnl.add(time);
			topPnl.add(txtTime);		
			
			JPanel panelFrame = new JPanel();
			panelFrame.setLayout(new BorderLayout());
			
			panel = new JPanel();
			panel.setLayout(new GridLayout(4,4));
	
			button = ComboMethods.setBase(button, panel,"X",50);			
			
			JPanel timePanel = new JPanel();
			timePanel.setLayout(new FlowLayout());
						
			JLabel matches = new JLabel("pairs");
			JTextField txtMatch = new JTextField(3);
			txtMatch.setEditable(false);
			txtMatch.setBackground(Color.WHITE);
			txtMatch.setHorizontalAlignment(JTextField.CENTER); 

			JLabel nameTag = new JLabel("Richard");
			JTextField scoreTag = new JTextField(3);
			scoreTag.setText("0");
			scoreTag.setEditable(false);
			scoreTag.setBackground(Color.WHITE);
			scoreTag.setHorizontalAlignment(JTextField.CENTER); 

			JLabel errorTag = new JLabel("Fails");
			JTextField errors = new JTextField(3);
			errors.setText("0");
			errors.setEditable(false);
			errors.setBackground(Color.WHITE);
			errors.setHorizontalAlignment(JTextField.CENTER); 

			JPanel scoring = new JPanel();
			scoring.setLayout(new BorderLayout());
			JTextField info = new JTextField(4);
			info.setHorizontalAlignment(JTextField.CENTER);
			info.setEditable(false);
			info.setBackground(Color.WHITE);
			info.setText("Round "+turn+" : Checking for a match!");

			
			pressPlay.addActionListener(new ActionListener() {						
				public void actionPerformed(ActionEvent a) { 					
					
					String naming = namePlay1.getText();
					boolean playgame = ComboMethods.Message1(naming);
					
					if (playgame==true) {					
						
						panelFrame.setVisible(true);
						startPanel.setVisible(false);
						
						nameTag.setText(naming);								
						gameNum.setText("Game "+round+"");						
						GameReset();						

						score = 0;
						scoreTag.setText(score+"");

						info.setText("New Game : Check for a match!");
						errors.setText("0");
						
						round=1;
						gameNum.setText("Game "+round+"");	
						button = ComboMethods.hideBase(button, "X");
						
															
					Thread go = new Thread() {
						
						@Override 
						public void run() {
							
							while (stop==false) {												
								
								if (sec==0) {
									
									String drawTime = singleDraw();
									info.setText(drawTime);
									
									WinnerData(nameTag.getText());									
									button = ComboMethods.Revealing(button, box);									
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
						
						box = ComboMethods.MakeBox(box, gen, space);	
						
						txtMatch.setText(pair+"");
						
						
						panelFrame.add(topPnl, BorderLayout.NORTH);
						panelFrame.add(panel, BorderLayout.CENTER);
						panelFrame.add(scoring, BorderLayout.SOUTH);	
												
					}		
				}
			});	
			
			timePanel.add(nameTag);
			timePanel.add(scoreTag);
			timePanel.add(errorTag);
			timePanel.add(errors);		
			timePanel.add(matches);
			timePanel.add(txtMatch);
			txtMatch.setText(pair+"");						

			
			for (int i = 0; i<button.length; i++) {
				
				for (int j = 0; j<button[i].length;j++) {	
		
						JButton btnZ = button[i][j];
											
						int E1 = i;
						int E2 = j;
						
						btnZ.addActionListener (new ActionListener() {						
																					
							public void actionPerformed(ActionEvent a) {
								
								if (stop==false) {
									
									info.setText("Round "+turn+" : Checking for a match!");

									if (count%2 == 0) {								
										
										if((count>1)&&(x.equals(y)==false)) {

											if (btn1.getText().equals(" ")==false) {
												btn1.setText("X");
											}
											if (btn2.getText().equals(" ")==false) {
												btn2.setText("X");
											}											
										}
										
										A = E1;
										B = E2;								
										x = ComboMethods.setSign(box,A,B);
										
										if (btnZ.getText().equals("X")) {
											btnZ.setText(x);
										}									

										count++;
										btn1 = btnZ;
										
									} else {
										
										C = E1;
										D = E2;
										y = ComboMethods.setSign(box,C,D);
										
										if (btnZ.getText().equals("X")) {
										
											btnZ.setText(y);
										}
										
										count++;
										btn2 = btnZ;

										if(x.equals(y)&&((A!=C)||(B!=D))&&((!btn1.getText().equals(" "))||(!btn2.getText().equals(" ")))) {
											btn1.setText(" ");
											btn2.setText(" ");
											pair--;
											info.setText("Round "+turn+" : You have a Match!");
											txtMatch.setText(pair+"");
											
											if (pair==0) {

												stop=true;
												
												info.setText("Round "+turn+" : Congratulations, You Won!");
												turn--;
												score++;
												scoreTag.setText(score+"");
												txtTime.setText(sec+"");
											}
										}
										
										else if((A==C)&&(B==D)&&(!btn1.getText().equals(" ")||!btn2.getText().equals(" "))) {
											btn1.setText("X");
											btn2.setText("X");

											info.setText("Round "+turn+" : This is the same tile!");
											fails++;
											errors.setText(fails+"");
										}
										
										else if((btn1.getText().equals(" ")&&btn2.getText().equals(" "))) {

											info.setText("Round "+turn+" : This space was cleared!");
											turn--;
										}
										
										else if((count>1)&&(x.equals(y)==false)) {
											
											fails++;
											errors.setText(fails+"");
											info.setText("Round "+turn+" : No match found!");									
										}
											turn++;	
									}	
									
									if (stop==true) {
																				
										WinnerData(nameTag.getText());
									}					
									
								}
							}		
					});											
				}	
			}					
	
			JButton resetGame = new JButton("RESET"); 
						
			resetGame.addActionListener (new ActionListener() {						
								
				
				public void actionPerformed(ActionEvent a) {
			
					if (stop==true) {
						
						GameReset();
						info.setText("New Game : Check for a match!");
						errors.setText("0");
						
						round++;
						gameNum.setText("Game "+round+"");
						button = ComboMethods.hideBase(button, "X");
						
															
					Thread go = new Thread() {
						
						@Override 
						public void run() {
							
							while (stop==false) {												
								
								if (sec==0) {
									
									String drawTime = singleDraw();
									info.setText(drawTime);
									
									WinnerData(nameTag.getText());
									button = ComboMethods.Revealing(button, box);
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
						
						box = ComboMethods.MakeBox(box, gen, space);	

						txtMatch.setText(pair+"");
						
					}
				}				
			});

			
			scoring.add(timePanel, BorderLayout.NORTH);
			scoring.add(info, BorderLayout.CENTER);
			scoring.add(resetGame, BorderLayout.SOUTH);			

			
			this.setSize(400,400);
			this.setLocation(65,30);
			this.setVisible(true);

			
			this.add(panelFrame);
			panelFrame.setVisible(false);
	}	
	
	public static void main(String[] args) {
		
		new GameFrame() ;	
		
	}
	
	

public static void WinnerData(String currentPlayer) {
		

	    String gameDate = ComboMethods.DateAndTime();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		String tiles = pair+" pairs left";
		String result = "game " + match;

		String playing = "Game "+round;
		MatchBoard board = new MatchBoard(playing+": "+currentPlayer,turn,tiles,result,gameDate);
		
		tx.begin();

		em.persist(board);
		tx.commit();

		em.close();
		emf.close();	
	}	

	public static String singleDraw() {
	
		stop=true;
		match = "lost";		
		String draw = ("Round "+turn+" : Time's up, Game Over!");		
		
		return draw;
	}

	public static void GameReset() {
		
		sec = limit;
		pair=8;
		stop=false;	
		match = "won"; 
		fails = 0;
		turn=1;	
	}	
	
	public static boolean close() {
	
		boolean ending = stop;
	
		return ending;
	}
}
