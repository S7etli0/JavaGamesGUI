package allgames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import lineGame.FourLineGame;
import matchGame.MatchGame;
import minesGame.MinesGame;
import seaGame.SeaChess;
import shipGame.BattleShipsGame;

public class GameFrame extends JFrame implements ActionListener{
	
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnReg;
	private JPanel logins;

	private JPanel regPan;
	private JPanel regPanOne;
	private JPanel regPanTwo;
	private JPanel regPanTre;
	private JPanel regPanBTN;
	
	private JPanel regPanTop;
	private JLabel lblsignPic;	
	
	private JLabel lblnewUser;
	private JTextField txtnewUser;
	private JLabel lblnewPass;
	private JTextField txtnewPass;
	private JButton btnset;
	
	BattleShipsGame shipGame;
	JButton battleShipBTN;

	MatchGame matchGame;
	JButton matchBTN;
	
	SeaChess seaChess;
	JButton seaBTN;
	
	MinesGame minesGame;
	JButton minesBTN;
	
	FourLineGame lineGame;
	JButton lineBTN;
	
	GameTable tables;
	JButton tabBTN;
	
	JLabel btnPic;
	JButton quitBTN;
	JButton backBTN;
	JButton backmenu;

	JLabel lblpic;
	ImageIcon loginPic;
	
	int choice;
	boolean change=false;
	
	public GameFrame () {
		
		int hor = 150;
		int ver = 50;
		int left = 95;
		int right = 280;
		
		setLayout(new FlowLayout());

		logins = new JPanel();
		logins.setLayout(new BorderLayout());
		JPanel panOne = new JPanel();
		JPanel panTwo = new JPanel();
		JPanel panTre = new JPanel();
		JPanel panBTN = new JPanel();
		panTre.setLayout(new BorderLayout());
		
		JPanel panTop = new JPanel();
		lblpic = new JLabel();	
		panTop.add(lblpic);	
		loginPic = new ImageIcon("pics/login.png");
		lblpic.setIcon(loginPic);		
		
		lblUserName = new JLabel("Input Username");
		txtUserName = new JTextField(8);
		lblPassword = new JLabel("Input Password") ;
		txtPassword = new JPasswordField(8);
		btnLogin = new JButton("Login");
		btnReg = new JButton("Sign in");

		panOne.add(lblUserName);
		panOne.add(txtUserName);
		panTwo.add(lblPassword);
		panTwo.add(txtPassword);
		panBTN.add(btnLogin);
		panBTN.add(btnReg);

		logins.add(panTop, BorderLayout.NORTH);
		logins.add(panOne, BorderLayout.CENTER);
		logins.add(panTre, BorderLayout.SOUTH);
		panTre.add(panTwo, BorderLayout.NORTH);
		panTre.add(panBTN, BorderLayout.CENTER);
		
		this.add(logins);	

		regPan = new JPanel();
		regPan.setLayout(new BorderLayout());
		regPanOne = new JPanel();
		regPanTwo = new JPanel();
		regPanTre = new JPanel();
		regPanBTN = new JPanel();
		regPanTre.setLayout(new BorderLayout());
		
		regPanTop = new JPanel();
		lblsignPic = new JLabel();	
		regPanTop.add(lblsignPic);	
		ImageIcon signPic = new ImageIcon("pics/register.png");
		lblsignPic.setIcon(signPic);		
		
		lblnewUser = new JLabel("New Username");
		txtnewUser = new JTextField(8);
		lblnewPass = new JLabel("New Password") ;
		txtnewPass = new JPasswordField(8);
		btnset = new JButton("register");
		JButton btnBack = new JButton("goback");

		regPanOne.add(lblnewUser);
		regPanOne.add(txtnewUser);
		regPanTwo.add(lblnewPass);
		regPanTwo.add(txtnewPass);
		regPanBTN.add(btnset);
		regPanBTN.add(btnBack);

		regPan.add(regPanTop, BorderLayout.NORTH);
		regPan.add(regPanOne, BorderLayout.CENTER);
		regPan.add(regPanTre, BorderLayout.SOUTH);
		regPanTre.add(regPanTwo, BorderLayout.NORTH);
		regPanTre.add(regPanBTN, BorderLayout.CENTER);						
		
		regPan.setVisible(false);
		this.add(regPan);
	
		btnset.addActionListener(ee-> {
			
			boolean playgame = ComboMethods.Message3(txtnewUser.getText(),txtnewPass.getText());
			
			if (playgame==true) {							

				boolean signed = ComboMethods.Signup(txtnewUser.getText(),Integer.parseInt(txtnewPass.getText()));			
				
				if (signed == false) {
					
					logins.setVisible(true);
					regPan.setVisible(false);
				}
			}
			
		});
		
		btnBack.addActionListener(ee-> {

			logins.setVisible(true);
			regPan.setVisible(false);
			txtnewUser.setText("");
			txtnewPass.setText("");
		});
		
		
		btnReg.addActionListener(e-> {
			
			logins.setVisible(false);
			regPan.setVisible(true);
			txtUserName.setText("");
			txtPassword.setText("");
			txtnewUser.setText("");
			txtnewPass.setText("");
		});
				
		
		btnLogin.addActionListener(e-> {
									
			boolean entergame = false;	
			boolean startgame = ComboMethods.Message3(txtUserName.getText(),txtPassword.getText());

			
			if (startgame==true) {
				
				entergame = ComboMethods.Login(txtUserName.getText(),Integer.parseInt(txtPassword.getText()));
			} 
			
			if (entergame==true) {				
	
				logins.setVisible(false);
				txtUserName.setText("");
				txtPassword.setText("");
				
				backmenu = new JButton("Back to Main Menu");
				backmenu.setSize(335,40);
				backmenu.setLocation(95,460);
				backmenu.addActionListener(this);
				
				
				this.setTitle("GameBoard");  
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setVisible(true);
				this.setSize(540,560);		
				
				minesBTN = new JButton("MinesGame");
				minesBTN.setSize(hor,ver);
				minesBTN.setLocation(left,230);
				minesBTN.addActionListener(this);
				
				seaBTN = new JButton("TicTacToe");
				seaBTN.setSize(hor,ver);
				seaBTN.setLocation(right,230);
				seaBTN.addActionListener(this);
				
				matchBTN = new JButton("MatchGame");
				matchBTN.setSize(hor,ver);
				matchBTN.setLocation(left,305);
				matchBTN.addActionListener(this);
				
				battleShipBTN = new JButton("BattleShips");
				battleShipBTN.setSize(hor,ver);
				battleShipBTN.setLocation(right,305);
				battleShipBTN.addActionListener(this);
				
				lineBTN = new JButton("4LineGame");
				lineBTN.setSize(hor,ver);
				lineBTN.setLocation(left,380);
				lineBTN.addActionListener(this);
				
				tabBTN = new JButton("GameTables");
				tabBTN.setSize(hor,ver);
				tabBTN.setLocation(right,380);
				tabBTN.addActionListener(this);			
				
				quitBTN = new JButton("Quit");
				quitBTN.setSize(hor,ver-10);
				quitBTN.setLocation(190,390);
				quitBTN.addActionListener(this);
				
				ImageIcon gamePic = new ImageIcon("pics/games-logo.png");
				lblpic.setIcon(gamePic);	
				btnPic = new JLabel(gamePic);	
				btnPic.setSize(385,150);
				btnPic.setLocation(70,50);
				this.add(btnPic);
				
				this.add(seaBTN);
				this.add(matchBTN);
				this.add(battleShipBTN);
				this.add(minesBTN);
				this.add(tabBTN);
				this.add(lineBTN);
				this.add(backmenu);
				this.setLayout(null);
			} 
			
		});
		
		this.setSize(280,260);
		this.setVisible(true);
		this.setResizable(true);
		this.setTitle("GameChoice");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
		
		if ((e.getSource()==battleShipBTN)||e.getSource()==matchBTN||e.getSource()==seaBTN||e.getSource()==minesBTN||e.getSource()==tabBTN||e.getSource()==lineBTN||e.getSource()==backmenu) {
			
			this.remove(btnPic);
			this.remove(battleShipBTN);
			this.remove(matchBTN);
			this.remove(seaBTN);
			this.remove(minesBTN);
			this.remove(tabBTN);
			this.remove(lineBTN);
			this.remove(backmenu);
			this.add(quitBTN);
		}		
		
		if(e.getSource()==backmenu) {
			
			setSize(280,260);
			lblpic.setIcon(loginPic);
			setTitle("GameChoice");
			logins.setVisible(true);
			this.remove(quitBTN);
			SwingUtilities.updateComponentTreeUI(this);
		}	
		
		
		if(e.getSource()==battleShipBTN) {
			
			choice = 1;
			setTitle("BattleShips");
			shipGame = new BattleShipsGame();
			this.add(shipGame);
			SwingUtilities.updateComponentTreeUI(this);
		}	
		
		if(e.getSource()==matchBTN) {

			choice = 2;
			setTitle("MatchGame");
			matchGame = new MatchGame();
			this.add(matchGame);
			SwingUtilities.updateComponentTreeUI(this);
		}	
		
		if(e.getSource()==seaBTN) {

			choice = 3;
			setTitle("TicTacToe");
			seaChess = new SeaChess();
			this.add(seaChess);
			SwingUtilities.updateComponentTreeUI(this);
		}	
		
		if(e.getSource()==minesBTN) {

			choice = 4;
			setTitle("MinesGame");
			minesGame = new MinesGame();
			this.add(minesGame);
			SwingUtilities.updateComponentTreeUI(this);
		}		
		
		if(e.getSource()==lineBTN) {
			
			choice = 5;
			setTitle("FourInLine");
			lineGame = new FourLineGame();
			this.add(lineGame);
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		if(e.getSource()==tabBTN) {
			
			choice = 6;
			setTitle("GameTables");
			tables = new GameTable();
			this.add(tables);
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		if(e.getSource()==quitBTN) {
						
			switch(choice) {
			
			case 1: if (shipGame.close()==true) {
				
						this.remove(shipGame);
						change=true;
				
					} break;
					
			case 2: if (matchGame.close()==true) {
				
						this.remove(matchGame);
						change=true;
		
					} break;		
			
			case 3: if (seaChess.close()==true) {
				
						this.remove(seaChess);
						change=true;
		
					} break;
			
			case 4: if (minesGame.close()==true) {
				
						this.remove(minesGame);
						change=true;

					} break;
			
			case 5: if (lineGame.close()==true) {
				
				this.remove(lineGame);
				change=true;

			} break;			
			
			case 6: this.remove(tables);
					change=true;
					break;							
			};				
		
				if (change==true) {
					
					change=false;
					setTitle("GameChoice");
					this.remove(quitBTN);
					SwingUtilities.updateComponentTreeUI(this);
					
					this.add(btnPic);
					this.add(battleShipBTN);
					this.add(matchBTN);
					this.add(seaBTN);
					this.add(minesBTN);
					this.add(lineBTN);
					this.add(tabBTN);
					this.add(backmenu);
				}		
				
		}			
	}	
}
