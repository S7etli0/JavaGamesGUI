package seaGame;

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
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import allgames.ComboMethods;
	import allgames.GameFrame;
	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.EntityTransaction;
	import javax.persistence.Persistence;
	
	public class SeaChess extends JPanel {
			
			// Game Timer
			static int limit = 15;
			static int sec = limit;
		
			JPanel startPanel;
			static JButton button [][];
			JPanel panelBTM;
			JPanel panelGame;
			JTextField txtField;
			JButton resButton;
			JPanel panelFrame;
			static int turn = 1;
			String player = "X";
			static boolean win;
			static String currentPlayer;
			static int Play1win = 0;
			static int Play2win = 0;
			static int round = 1;
			static boolean stop = true;
			
			static int draw = 0;
			static int space = 3;

			
			public SeaChess() {	
				
				startPanel = new JPanel ();
				startPanel.setLayout(new BorderLayout());
				
				JPanel firstPanel = new JPanel ();
				JPanel secPanel = new JPanel ();
				
				JLabel Play1 = new JLabel("Player1 (X)");
				TextField namePlay1 = new TextField(10);
				JLabel Play2 = new JLabel("Player2 (O)");
				TextField namePlay2 = new TextField(10);
				JButton pressPlay = new JButton("Press to Play");									
				
				firstPanel.add(Play1);
				firstPanel.add(namePlay1);
				secPanel.add(Play2);
				secPanel.add(namePlay2);
				
				JPanel pnlPic = new JPanel();
				JLabel lblpic = new JLabel();	
				pnlPic.add(lblpic);	
				ImageIcon loginPic = new ImageIcon("pics/xoxoxo.png");
				lblpic.setIcon(loginPic);
				
				JPanel players = new JPanel();
				players.setLayout(new BorderLayout());
				players.add(firstPanel, BorderLayout.NORTH);
				players.add(secPanel, BorderLayout.CENTER);				
				
				startPanel.add(pnlPic, BorderLayout.NORTH);
				startPanel.add(players, BorderLayout.CENTER);
				startPanel.add(pressPlay, BorderLayout.SOUTH);
				this.add(startPanel);								
				
				JPanel infoPanel = new JPanel();
				infoPanel.setLayout(new FlowLayout());
				
				JLabel title = new JLabel("TicTacToe");
				JTextField infoField = new JTextField(5);
				infoField.setText("Game "+round+"");
				
				infoField.setHorizontalAlignment(JTextField.CENTER); 
				infoField.setEditable(false);
				infoField.setBackground(Color.WHITE);
				
				JLabel time = new JLabel("time");
				JTextField txtTime = new JTextField(4);
				txtTime.setHorizontalAlignment(JTextField.CENTER); 
				txtTime.setEditable(false);
				txtTime.setBackground(Color.WHITE);
				
				infoPanel.add(title);
				infoPanel.add(infoField);
				infoPanel.add(time);
				infoPanel.add(txtTime);					
				
				panelGame = new JPanel();
				panelGame.setLayout(new FlowLayout());				
				panelGame.setLayout(new GridLayout(space,space));
				button = new JButton [space][space];
				
				Random gen =  new Random();		
				String box [][] = new String [space][space];
				button = ComboMethods.setBase(button, panelGame," ",60);													
				txtField = new JTextField(10);
				
				txtField.setHorizontalAlignment(JTextField.CENTER); 
				txtField.setEditable(false);
				txtField.setBackground(Color.WHITE);
					
				JPanel scorePanel = new JPanel();							
				scorePanel.setLayout(new FlowLayout());

				JLabel lblScoreP1 = new JLabel();
				JLabel lblScoreP2 = new JLabel();		
				JLabel lblScoreDraw = new JLabel("Draw");	
				
				JTextField scoreP1 = new JTextField(3);
				scoreP1.setEditable(false);
				scoreP1.setBackground(Color.WHITE);
				scoreP1.setHorizontalAlignment(JTextField.CENTER); 

				JTextField scoreP2 = new JTextField(3);
				scoreP2.setEditable(false);
				scoreP2.setBackground(Color.WHITE);
				scoreP2.setHorizontalAlignment(JTextField.CENTER); 

				JTextField scoreDraw = new JTextField(3);
				scoreDraw.setEditable(false);
				scoreDraw.setBackground(Color.WHITE);
				scoreDraw.setHorizontalAlignment(JTextField.CENTER); 

				scoreP1.setText("0");
				scoreP2.setText("0");
				scoreDraw.setText("0");
				
				scorePanel.add(lblScoreP1);
				scorePanel.add(scoreP1);
				scorePanel.add(lblScoreP2);
				scorePanel.add(scoreP2);			
				scorePanel.add(lblScoreDraw);
				scorePanel.add(scoreDraw);
				
				pressPlay.addActionListener(new ActionListener() {						
					public void actionPerformed(ActionEvent a) { 					
						
						String naming1 = namePlay1.getText();
						String naming2 = namePlay2.getText();
						boolean playgame = ComboMethods.Message2(naming1,naming2);
						
						if (playgame==true) {
							
							panelFrame.setVisible(true);
							startPanel.setVisible(false);
							
							GameReset();
							
							draw = 0;
							scoreDraw.setText(draw+"");
							Play1win = 0;
							scoreP1.setText(Play1win+"");
							Play2win = 0;
							scoreP2.setText(Play2win+"");
							
							Thread go = new Thread() {
								
								@Override 
								public void run() {
									
									while (stop==false) {												
										
										if (sec==0) {
											
											String drawplay = TimeOut();
											txtField.setText(drawplay);
											scoreDraw.setText(draw+"");
											WinnerData(namePlay1.getText(),namePlay2.getText());
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

							button = ComboMethods.hideBase(button," "); 		
			
							round=1;
							infoField.setText("Game "+round+"");							
													
							String ScoreP1 = namePlay1.getText();
							String ScoreP2 = namePlay2.getText();
							lblScoreP1.setText(ScoreP1);
							lblScoreP2.setText(ScoreP2);
							
							txtField.setText("Round "+(turn)+": "+namePlay1.getText()+"'s turn to set an "+player);
							player = ComboMethods.changePlayer(player);
						}											
					}
				});
				
				
				for (int i = 0; i<button.length; i++) {
					
					for (int j = 0; j<button[i].length;j++) {
						JButton btn1 = button[i][j];
						
							btn1.addActionListener (new ActionListener() {						
								public void actionPerformed(ActionEvent a) {																	
									
									if (stop == false) {

										if (btn1.getText().equals(" ")) {
											turn++;
											currentPlayer = Name(player, namePlay1.getText(), namePlay2.getText());					
											
											txtField.setText("Round "+(turn)+": "+currentPlayer+"'s turn to set an "+player);
											player = ComboMethods.changePlayer(player);										
											
											btn1.setText(player);
																				
											win = ComboMethods.whoHasWon(button,player);
										}
										
										if ((win==true)) {
											
											turn--;
											currentPlayer = Name(player, namePlay1.getText(), namePlay2.getText());					
								
											txtField.setText("Round "+(turn)+": "+"The winner is "+currentPlayer);
											stop = true;
											
											if (currentPlayer.equals(namePlay1.getText())) {
												
												Play1win++;
												scoreP1.setText(Play1win+"");
												
											} else {
												
												Play2win++;
												scoreP2.setText(Play2win+"");
											}
												  																						
										} else {
										
											if (turn>9) {
												
												String drawplay = TimeOut();
												txtField.setText(drawplay);
												scoreDraw.setText(draw+"");
											}
										}	
										
										if (stop==true) {
											
											WinnerData(namePlay1.getText(),namePlay2.getText());
										}										
									}																										
								}		
							});	
						} 	
					} 

				panelBTM = new JPanel();
				panelBTM.setLayout(new BorderLayout());
				
				panelFrame = new JPanel();
				panelFrame.setLayout(new BorderLayout());
						
				resButton = new JButton("RESET");
				resButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
								
						if (stop==true) {
						
							GameReset();
							
							Thread go = new Thread() {
								
								@Override 
								public void run() {
									
									while (stop==false) {												
										
										if (sec==0) {
											
											String drawplay = TimeOut();
											txtField.setText(drawplay);
											scoreDraw.setText(draw+"");
											WinnerData(namePlay1.getText(),namePlay2.getText());
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

							
							button = ComboMethods.hideBase(button," "); 			
			
							currentPlayer = Name(player, namePlay1.getText(), namePlay2.getText());					
							round++;
							infoField.setText("Game "+round+"");
							
							txtField.setText("Round "+(turn)+": "+currentPlayer+"'s turn to set an "+player);
							player = ComboMethods.changePlayer(player);						
						}				
					}		
				});
				
				JPanel buttonArea = new JPanel();
				
				buttonArea.setLayout(new BorderLayout());
				buttonArea.add(resButton, BorderLayout.CENTER);
				
				panelFrame.add(infoPanel,BorderLayout.NORTH);
				panelFrame.add(panelGame, BorderLayout.CENTER);
				panelFrame.add(panelBTM, BorderLayout.SOUTH);
				
				this.setSize(530,460);
				this.setLocation(0,50);
				this.setVisible(true);
				
				panelBTM.add(buttonArea, BorderLayout.SOUTH);
				panelBTM.add(txtField, BorderLayout.NORTH);
				panelBTM.add(scorePanel, BorderLayout.CENTER);
				add(panelFrame);
				panelFrame.setVisible(false);
		}	
		
		public static void main(String[] args) {
			
			new GameFrame() ;			
		}
		
		public static void WinnerData(String P1, String P2) {		

			String gameDate = ComboMethods.DateAndTime(); 
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
						
			String score = P1+" - "+Play1win+" : "+Play2win+" - "+P2;
			String playing = "Game "+round;
			SeaBoard board = new SeaBoard(playing+": "+currentPlayer,(turn),score,draw,gameDate);
			
			tx.begin();

			em.persist(board);
			tx.commit();

			em.close();
			emf.close();
		}
		
		public static String Name(String player, String n1, String n2) {
			
			if (player.equals("X")) {
				
				currentPlayer = n1;
				
			} else {
				
				currentPlayer = n2;
			}

			return currentPlayer;
		}		
		
		public static String TimeOut() {
			
			if (turn!=1) {turn--;}
			stop = true;
			currentPlayer = "draw";	
			draw++;		
			String text = "Round "+(turn)+": "+"There is no winner";
			
			return text;
		}
		
		public static void GameReset() {
			
			sec = limit;
			stop=false;	
			turn=1;	
		}		
		
		public static boolean close() {
			
			boolean ending = stop;
		
			return ending;
		}
	}