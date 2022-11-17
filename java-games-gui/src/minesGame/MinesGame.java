package minesGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingUtilities;
import allgames.ComboMethods;
import allgames.GameFrame;


public class MinesGame extends JPanel {	
	
		// Game Timer
		static int limit = 20;
		static int sec = limit;
	
		JPanel panel;	
		static boolean stop = true;
		static int space = 4;
		static Random gen =  new Random();
		static JButton button [][] = new JButton [space][space];
		static String box [][] = new String [space][space];
		
		static int allmines = space+1;
		static int full = (int) (Math.pow(space,2) - allmines);
		static int check = 0;
		static int round = 1;
		static int turn = 1;
		static int boom = 0;

		JPanel panelFrame;
		JPanel startPanel;
		JPanel firstPanel;
		JLabel Play1;
		TextField namePlay1;
		JButton pressPlay;
		static int score = 0;
		JLabel nameTag;
		JPanel state; 
		static String match = "won"; 
		

		public MinesGame() {
			
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
			ImageIcon loginPic = new ImageIcon("pics/mines.jpg");
			lblpic.setIcon(loginPic);
			startPanel.add(pnlPic, BorderLayout.NORTH);
			
			startPanel.add(firstPanel, BorderLayout.CENTER);
			startPanel.add(pressPlay, BorderLayout.SOUTH);
			this.add(startPanel);									
			
			panelFrame = new JPanel();
			panelFrame.setLayout(new BorderLayout());
			
			JPanel topPnl = new JPanel(); 
			topPnl.setLayout(new FlowLayout());
			
			JLabel gameName = new JLabel("MineSweeper");
			JTextField gameNum = new JTextField(6);
			gameNum.setHorizontalAlignment(JTextField.CENTER); 
			gameNum.setText("Game "+round+"");
			gameNum.setEditable(false);
			gameNum.setBackground(Color.WHITE);
			
			JLabel time = new JLabel("time");
			JTextField txtTime = new JTextField(4);
			txtTime.setHorizontalAlignment(JTextField.CENTER); 

			txtTime.setEditable(false);
			txtTime.setBackground(Color.WHITE);
			
			topPnl.add(gameName);
			topPnl.add(gameNum);
			topPnl.add(time);
			topPnl.add(txtTime);
										
			
			panel = new JPanel();
			panel.setLayout(new GridLayout(space,space));
			button = ComboMethods.setBase(button, panel,"X",50);					
			
			nameTag = new JLabel();
			JTextField gameScore = new JTextField (3);
			gameScore.setHorizontalAlignment(JTextField.CENTER); 

			gameScore.setText(0+"");
			gameScore.setEditable(false);
			gameScore.setBackground(Color.WHITE);
			
			JLabel clear = new JLabel("clear");
			JTextField txtclear = new JTextField(3);
			txtclear.setHorizontalAlignment(JTextField.CENTER); 

			txtclear.setText(check+"");		
			txtclear.setEditable(false);
			txtclear.setBackground(Color.WHITE);
			
			JLabel mines = new JLabel("flags");
			JTextField txtMines = new JTextField(3);
			txtMines.setText(allmines+"");							
			txtMines.setEditable(false);
			txtMines.setBackground(Color.WHITE);
			txtMines.setHorizontalAlignment(JTextField.CENTER); 

			JPanel statPanel = new JPanel();
			statPanel.setLayout(new FlowLayout());		
			JButton reset = new JButton("RESET");
			
			statPanel.add(nameTag);
			statPanel.add(gameScore);
			statPanel.add(clear);
			statPanel.add(txtclear);
			statPanel.add(mines);
			statPanel.add(txtMines);
			
			state = new JPanel();
			state.setLayout(new BorderLayout());
			JTextField txtstate = new JTextField(3);
			txtstate.setEditable(false);
			txtstate.setBackground(Color.WHITE);
			txtstate.setHorizontalAlignment(JTextField.CENTER); 
			
			Font font = new Font("SansSerif", Font.BOLD, 14);
			txtstate.setHorizontalAlignment(JTextField.CENTER); 
			
			state.add(txtstate, BorderLayout.CENTER);
			state.add(statPanel, BorderLayout.NORTH);
			state.add(reset, BorderLayout.SOUTH);
			
			
			pressPlay.addActionListener(new ActionListener() {						
				public void actionPerformed(ActionEvent a) { 					
					
					String naming = namePlay1.getText();
					boolean playgame = ComboMethods.Message1(naming);
					
					if (playgame==true) {
						
						panelFrame.setVisible(true);
						startPanel.setVisible(false);
						nameTag.setText(naming);			
						
						GameReset();
						txtstate.setText("New Game : Click on a tile!");
						
						round=1;
						gameNum.setText("Game "+round+"");					
						txtclear.setText(check+"");

						
						button = ComboMethods.hideBase(button,"X"); 																		
						box = ComboMethods.hideBox(box,"X"); 
										
						
						Thread go = new Thread() {
							
							@Override 
							public void run() {
								
								while (stop==false) {												
									
									if (sec==0) {
										
										if (turn==0) {turn=1;}
										String drawTime = singleDraw();
										txtstate.setText(drawTime);
										WinnerData(nameTag.getText());
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
						
						txtMines.setText(allmines+""); 
						box = ComboMethods.SetField(box,gen,space);
						
						panelFrame.add(topPnl, BorderLayout.NORTH);
						panelFrame.add(panel, BorderLayout.CENTER);
						panelFrame.add(state, BorderLayout.SOUTH);													
					}		
				}
			});							

			
			for (int i = 0; i<button.length; i++) {
				
				for (int j = 0; j<button[i].length;j++) {	
		
						JButton btnZ = button[i][j];
				
						int A = i;
						int B = j;
						
						btnZ.addMouseListener(new MouseAdapter() {									

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
									if (SwingUtilities.isRightMouseButton(e)) {
										
										turn++;
										
										if (stop==false) {
																						
											if ((button[A][B].getText().equals("X"))) {

												button[A][B].setText("W");
												txtstate.setText("Round "+turn+" : Warning flag set!");	
												allmines--;
												txtMines.setText(allmines+"");										
											
											}	else {
												
												if ((button[A][B].getText().equals("W"))) {

													button[A][B].setText("X");	
													txtstate.setText("Round "+turn+" : Warning flag down!");
													allmines++;
													txtMines.setText(allmines+"");
												}											
											}
										}	
									} 					
								}
							});	
						
						btnZ.addActionListener (new ActionListener() {						
																					
							public void actionPerformed(ActionEvent a) {							
																																	
									String z = ComboMethods.setSign(box,A,B);								
									
									if (stop==false) {
										turn++;
																			
										if (z.equals("0")&&!button[A][B].getText().equals("W")) {										
																				
											boom++;
											if (boom<3) {
												
												button[A][B].setText("@");
												button[A][B].setForeground(Color.red);
												txtstate.setText("Round "+turn+" : Mine blown, Watch Out!!");
												allmines--;
												txtMines.setText(allmines+"");	
										
											} else {	
												
												allmines--;
												stop=true;
												btnZ.setForeground(Color.red);
												btnZ.setText("@");
												txtstate.setText("Round "+turn+" : Mission failed, Game Over!");	
												match = "lost";
												
												button = ComboMethods.Reveal(button,box);						
										}																													
			
									} else {
										
										if (button[A][B].getText().equals("W")) {
											
											txtstate.setText("Round "+turn+" : Unmark to reveal!");
											turn--;
											
										} else {
											
											if (!button[A][B].getText().equals(" ")) {
												
												btnZ.setText(" ");
												check++;
												txtclear.setText(check+"");
												txtstate.setText("Round "+turn+" : The tile is clear!");
											}											
										}																												
									}
									
									if (full==check) {
																			
										txtstate.setText("Round "+turn+" : Congratulations, You Won!");

										stop=true;
										score++;
										gameScore.setText(score+"");
			
									}	
									
									if (stop==true) {
										
										WinnerData(nameTag.getText());
									}																		
								}	
							}															
					});	
				}	
			}	
			
			reset.addActionListener (new ActionListener() {						
				
				public void actionPerformed(ActionEvent a) {
					
					if (stop==true) {
						
						GameReset();
						txtstate.setText("New Game : Click on a tile!");
						
						round++;
						gameNum.setText("Game "+round+"");
						txtclear.setText(check+"");


						button = ComboMethods.hideBase(button,"X"); 
						box = ComboMethods.hideBox(box,"X"); 
	
						
						Thread go = new Thread() {
							
							@Override 
							public void run() {
								
								while (stop==false) {												
									
									if (sec==0) {
										
										if (turn==0) {turn=1;}
										String drawTime = singleDraw();
										txtstate.setText(drawTime);
										WinnerData(nameTag.getText());
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
						
						txtMines.setText(allmines+""); 
						box = ComboMethods.SetField(box,gen,space);
					}
				}
			});				
			
			setSize(500,550);
			setLocation(15,30);
			setVisible(true);

			add(panelFrame);
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
			
			String tilecheck = check+" [] cleared";
			String result = "game " + match;
			
			String playing = "Game "+round;
			MinesBoard board = new MinesBoard(playing+": "+currentPlayer,turn,tilecheck,result,gameDate);
			
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
			button = ComboMethods.Reveal(button,box);
			
			return draw;
		}
		
		public static void GameReset() {
						
			sec = limit;
			check = 0;
			stop=false;	
			match = "won"; 
			boom = 0;
			turn=0;
			allmines = space+1;
		}	
		
		public static boolean close() {
			
			boolean ending = stop;
		
			return ending;
		}
}
