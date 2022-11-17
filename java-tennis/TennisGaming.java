package testTennins;

	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.event.MouseMotionAdapter;
	import java.awt.event.MouseMotionListener;
	import java.util.Random;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

public class TennisGaming extends JFrame {
	
	private static int P1x=230, P1y = 55, P2x=230, P2y = 585, Bx=260, By = 320;
	private static int start, turn = 0, move, p1=0, p2=0;
	private static Random gen = new Random();
	private static boolean go = true;
	private static boolean game = true;

	public TennisGaming() {
		
//		JPanel canvas = new JPanel();
//		canvas.setLayout(new BorderLayout());	
		move = gen.nextInt(2);
		start = gen.nextInt(10);
		System.out.println(start);

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent event) {
				
				int keyCode = event.getExtendedKeyCode();
				
//				Paddle of P1
				if(keyCode==event.VK_W) {
					P1y-=10;
					repaint();				
				}	
								
				if(keyCode==event.VK_S) {
					
					if ((P1y+10)!=325) {
						P1y+=10;
						repaint();	
					}						
				}
				
				if(keyCode==event.VK_D) {
					P1x+=10;
					repaint();
				}
				
				if(keyCode==event.VK_A) {
					P1x-=10;
					repaint();
				}		
				
//				Paddle of P2				
				if(keyCode==event.VK_UP) {
					
					if (P2y!=325) {
						P2y-=10;
						repaint();	
					}										
				}	
								
				if(keyCode==event.VK_DOWN) {
					P2y+=10;
					repaint();	
				}
				
				if(keyCode==event.VK_RIGHT) {
					P2x+=10;
					repaint();
				}
				
				if(keyCode==event.VK_LEFT) {
					P2x-=10;
					repaint();
				}
				
//				Pause Game while the Ball is in the Middle				
				if(keyCode==event.VK_ENTER) {
				
					if (By==320) {
						go = false;
					}
				}
				
//				Restart the Game								
				if(keyCode==event.VK_SPACE) {

					go = true; 						
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub			
			}		
			
		});
		
		
//		this.add(canvas);
		this.setSize(530,705);
		this.setVisible(true);			

		this.setResizable(false);
		this.setTitle("Table Tennis");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		while (game==true) {	
//		Random direction for the ball

			if (start <=4) {
				turn = 2;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				
			} else {
				 turn = 1;
				 try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				 
				}	
			
			while ((move<2)&&(go==true))	{
				
//				the ball goes towards P1
				if (turn == 1) { 			
					for (int j =1; j>0; j++) {
//						the ball goes up			
						By-=5;
						if (move==0) {Bx-=1;} else {Bx+=1;}
						repaint();
						
//						the ball is on the Paddle of P1 along its length
						if ((By-10==P1y)&&(P1x-20<=Bx)&&(P1x+80>=Bx)) {
							turn = 2;	

//			 				the direction of the ball depends on which half of the Paddle it fell on		
							if ((P1x>=Bx-30)) {
								move=0;//Bx-=1; 
							} else {
								move=1;}//Bx+=1; 
							
							break;		
						
						}else {
							
//						the ball left the field on the side of Player1											
						if ((By<325)&&((Bx<10) || (Bx>490) || (By<15))) {
							move=2; p2+=10;
							break;}
									
						}
					
							if (go != false) {
								try {
									Thread.sleep(40);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}		
							}
						}
				}	
				
//				the ball goes towards P2			
				if (turn == 2) {
					for (int i =1; i>0; i++) {	
//						the ball goes down										
						By+=5;
						if (move==0) {Bx-=1;} else {Bx+=1;}									
						repaint();
						
//					the ball is on the Paddle of P2 along its length				
					if ((By+10==P2y)&&(P2x-20<=Bx)&&(P2x+80>=Bx)) {
						turn = 1;

// 						the direction of the ball depends on which half of the Paddle it fell on
						if ((P2x>=Bx-30)) {
							move=0;//Bx-=1; 
						} else {
							move=1;}//Bx+=1; 
						
						break;	
						
					}else {
						
//						the ball left the field on the side of Player2											
						if ((By>325)&&((Bx<10) || (Bx>490) || (By>630))) {
							move=2; p1+=10;
							break;}	
					}
						if (go != false) {
							try {
								Thread.sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			}Restart();		 	
		}				
	}
	
	public static void main(String[] args) {
		
		TennisGaming ten = new TennisGaming();
	}	
		
		
		public void paint (Graphics graphics) {
			
			Graphics2D g = (Graphics2D)graphics;
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
//			P1	
			g.setColor(Color.RED);
			g.fillRect(P1x,P1y,80,20);
//			P2			
			g.setColor(Color.RED);
			g.fillRect(P2x,P2y,80,20);						
//			middle		
			g.setColor(Color.WHITE);
			g.fillRect(40,325,450,10);
//			ball
			g.setColor(Color.YELLOW);
			g.fillOval(Bx, By, 20, 20);	
			
//			gamescore			
			g.setColor(Color.YELLOW);
			g.fillRect(0, 630, 530, 120);
			
			g.setColor(Color.RED);
			g.setFont(new Font("default", Font.BOLD, 14));
//			g.drawString("Player1: "+p1, 45, 650);
//			g.drawString("Player2: "+p2, 400, 650);
			g.drawString("Player1 "+p1+"-"+p2+" Player2", 195, 660);

		}		
	
	
		public static void Restart() {
			
				move = gen.nextInt(2);
				start = gen.nextInt(10);
				P1x=230; P1y = 55; P2x=230; P2y = 585; Bx=260; By = 320;
		}	
	}
