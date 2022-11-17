package allgames;

import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComboMethods {

	
	// registration and login
	
	public static boolean Signup(String name, int pass) {
	
		boolean donot = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();				
		
		tx.begin();		

		GameUserBoard  repeat = em.find (GameUserBoard.class,name);
		
		if (repeat != null) {
			
			JOptionPane.showMessageDialog(null,"This username is already taken!","Warning", JOptionPane.INFORMATION_MESSAGE);
			donot = true;
		} 
		
		if (donot == false) {
		
			GameUserBoard  board = new GameUserBoard (name,pass);	
			em.persist(board);
			JOptionPane.showMessageDialog(null,"You have been successfully registered!","Good Job", JOptionPane.INFORMATION_MESSAGE);
		}
				
		tx.commit();

		em.close();
		emf.close();
		
		return donot;
	}
	
	public static boolean Login(String name, int pass) {
		
		
		boolean fact = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();				
		
		tx.begin();		

		GameUserBoard  board = em.find (GameUserBoard.class,name);		
		
		if (board==null) {
			
			JOptionPane.showMessageDialog(null,"Wrong username! Register if you haven't!","Warning", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		if ((board.getUser().equals(name))&&(board.getCode()==(pass))) {
			
			fact=true;
			JOptionPane.showMessageDialog(null,"Hello there, "+board.getUser()+"! Choose a game!","Welcome", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		if ((board.getUser().equals(name))&&(board.getCode()!=(pass))) {
			
			JOptionPane.showMessageDialog(null,"Wrong password "+board.getUser()+" ! Try again!","Warning", JOptionPane.INFORMATION_MESSAGE);
		} 

		tx.commit();
		
		em.close();
		emf.close();
	
		return fact;
	}
	
	
	// name validation	
	
	public static boolean Message1(String naming) {
		
		boolean get = true;
		
		if (naming.length()>9) {
						
			JOptionPane.showMessageDialog(null,"The username is too long!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		} else if (naming.equals("")) {
									
			JOptionPane.showMessageDialog(null,"You must input a username!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
		}
			return get;					
	}

	public static boolean Message2(String naming1, String naming2) {
		
		boolean get = true;
		
		if ((naming1.length()>9||naming2.length()>9)&&(naming1.equals("")||naming2.equals(""))) {												
			
			JOptionPane.showMessageDialog(null,"One username is empty, the other is too long!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		} else 	if (naming1.length()>9||naming2.length()>9) {												
			
			JOptionPane.showMessageDialog(null,"One or both usernames are too long!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		} else if (naming1.equals("")||naming2.equals("")) {
									
			JOptionPane.showMessageDialog(null,"You must input both usernames to play!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		}  else if (naming1.equals(naming2)) {
		
		JOptionPane.showMessageDialog(null,"The usernames must be different!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
		}
		
		return get;	
	}
	
	
	public static boolean Message3(String naming1, String naming2) {
		
		boolean get = true;
		
		if ((naming1.length()>9||naming2.length()>9)&&(naming1.equals("")||naming2.equals(""))) {												
			
			JOptionPane.showMessageDialog(null,"One field is empty, the other is too long!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		} else 	if (naming1.length()>9||naming2.length()>9) {												
			
			JOptionPane.showMessageDialog(null,"One or both input fields are too long!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		} else if (naming1.equals("")||naming2.equals("")) {
									
			JOptionPane.showMessageDialog(null,"Input both username & password to play!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
			
		}  else if (naming1.equals(naming2)) {
		
		JOptionPane.showMessageDialog(null,"Username & password must be different!","Warning", JOptionPane.INFORMATION_MESSAGE);
			get = false;
		}
		
		return get;	
	}
	
	
	// Making a ButtonField
	
	public static JButton[][] setBase(JButton button[][], JPanel panel, String hide, int tile) {
						
		for (int i = 0; i<button.length; i++) {
			
			for (int j = 0; j<button[i].length; j++) {			
				
				JButton btn = new JButton();
				button[i][j] = btn;
				button[i][j].setText(hide);
				btn.setPreferredSize(new Dimension(tile,tile));	
				panel.add(btn);					
			}								
		}		
		return button;
	}
	
	public static JButton[][] hideBase(JButton button[][], String hide) {
		
		for (int i = 0; i<button.length; i++) {						
			for (int j = 0; j<button[i].length; j++) {																
				button[i][j].setText(hide);
				button[i][j].setForeground(Color.black);
			}								
		}		
		return button;
	}
	
	public static String[][] hideBox(String box[][], String hide) {
		
		for (int i = 0; i<box.length; i++) {
			for (int j=0; j<box[i].length; j++) {
				box[i][j]=hide;	
			}
		}			
		return box;
	}
	
	
	// input a mark & get the time
	
	public static String setSign(String map[][], int i, int j) {
		
		String mark = map[i][j];		
		
		return mark;
	}
	
	public static String DateAndTime () {
		
	  	LocalDateTime myDate = LocalDateTime.now();  
	    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String gameDate = myDate.format(myFormat);  
		
	    return gameDate;
	}
	
	
	// Mines
	
	public static JButton [][] Reveal (JButton[][]button, String box[][]) {
		
		for (int i = 0; i<button.length; i++) {
			
			for (int j = 0; j<button[i].length;j++) {	
			
				
				if ((button[i][j].getText().equals("W"))&&(box[i][j].equals("X"))) {
					
					button[i][j].setText("#");
					button[i][j].setForeground(Color.red);
					
				} else {
					
					if ((!button[i][j].getText().equals("@"))&&(!button[i][j].getText().equals(" "))&&(!button[i][j].getText().equals("W"))) {
						
						button[i][j].setText(box[i][j]);
					
					}														
				}												
			}
		}
		return button;
	}	
	
	
	public static String[][] SetField(String box[][], Random gen, int space) {
		
		int num = 0; 
		while (num<space+1) {
	
		int row = gen.nextInt(space);
		int col = gen.nextInt(space);
		
		for (int i = 0; i<box.length; i++) {
			for (int j=0; j<box[i].length; j++) {
				
				if (box[i][j] != "0") { 
					if ((row==i)&&(col==j)) {
						box[i][j] = "0";	
						num++;
					}
				}	
			}
		}		
	} 
		
//		//Revealing the mines in the console 
//		for (int i = 0; i<box.length; i++) {
//			
//			for (int j = 0; j<box[i].length; j++) {			
//				
//				System.out.print(box[i][j]+" ");
//				
//			}	System.out.println();								
//		}
//		System.out.println();	
		
		return box;
	}	
	
	
	// Matches
	
	public static JButton[][] Revealing (JButton[][] button, String box[][]) {
		
		for (int i = 0; i<button.length; i++) {
			
			for (int j = 0; j<button[i].length;j++) {										
				
				if (!button[i][j].getText().equals(" ")) {
					
					String end = ComboMethods.setSign(box,i,j);
					button[i][j].setText(end);																					
				}											
			}
		}
		return button;
	}
	
	public static String[][] MakeBox (String box[][], Random gen, int space) {
		
		box = ComboMethods.hideBox(box, "0");
		
		int key = 0;
		int doubl = 0;
		String sign [] = {"@","#","$","Q","Z","M","U","A"};
		
		do  {
			do  {
				int row = gen.nextInt(space);	
				int col = gen.nextInt(space);	
				
				if ((box[row][col].equals("0"))) {
					box[row][col] = sign[key];
					key++;
				}
			} while (key<sign.length);
			
				key=0;
				doubl++;

		} while (doubl<2);
		
//		// Revealing the pairs in the console
//		for (int i = 0; i<box.length; i++) {
//			
//			for (int j = 0; j<box[i].length; j++) {			
//				
//				System.out.print(box[i][j]+" ");
//				
//			}	System.out.println();								
//		}
//		System.out.println();	
		
		return box;
	}
	
	
	// TicTacToe
	
	public static String changePlayer(String player) {
		
		if (player.equals("X")) {
			player = "Î";
		} else {
			player = "X";
		}

		return player;
	}
	
	public static boolean whoHasWon(JButton[][] button, String x) {
		
		String mark = x;
		boolean winner = false;
		
		for (int i=0; i<button.length; i++) {
			for (int j=0; j<button[i].length; j++) {
				
				if ((button[i][0].getText().equals(mark))&&(button[i][1].getText().equals(mark))&&(button[i][2].getText().equals(mark))) {
				winner = true;	break;	
				}	
				if ((button[0][j].getText().equals(mark))&&(button[1][j].getText().equals(mark))&&(button[2][j].getText().equals(mark))) {
				winner = true;	break;		
				}	
				if ((button[0][0].getText().equals(mark))&&(button[1][1].getText().equals(mark))&&(button[2][2].getText().equals(mark))) {
					winner = true;	break;			
					}
				if ((button[0][2].getText().equals(mark))&&(button[1][1].getText().equals(mark))&&(button[2][0].getText().equals(mark))) {
					winner = true;	break;		
					}
			}
			
		} return winner;
	}
	
	
	// FourinLine
	
	public static boolean whoHasaLine(JButton[][] button, String x, int space1, int space2) {
		
		String mark = x;
		boolean winner = false;
		
		int thre = space1 - 3;
		int four = space2 - 3; 
		
		for (int i=0; i<button.length; i++) {
			for (int j=0; j<button[i].length; j++) {
				
				if ((j<four)&&(button[i][j].getText().equals(mark))&&(button[i][j+1].getText().equals(mark))&&(button[i][j+2].getText().equals(mark))&&(button[i][j+3].getText().equals(mark))) {
					winner = true;	break;	
					}	
					else if ((i<thre)&&(button[i][j].getText().equals(mark))&&(button[i+1][j].getText().equals(mark))&&(button[i+2][j].getText().equals(mark))&&(button[i+3][j].getText().equals(mark))) {
					winner = true;	break;		
					}	
					else if ((i<thre)&&(j<four)&&(button[i][j].getText().equals(mark))&&(button[i+1][j+1].getText().equals(mark))&&(button[i+2][j+2].getText().equals(mark))&&(button[i+3][j+3].getText().equals(mark))) {
						winner = true;	break;			
						}
					else if ((i<thre)&&(j<four)&&((button[i][j+3].getText().equals(mark))&&button[i+1][j+2].getText().equals(mark))&&(button[i+2][j+1].getText().equals(mark))&&(button[i+3][j].getText().equals(mark))) {
						winner = true;	break;		
						}
			}
			
		} return winner;
	}	
	
	
	// Battleships
	
	public static String[][] setShips(String Base[][], Random gen) {
		
		int num = 0; 
		
		Base = ComboMethods.hideBox(Base, "# ");	
		
		while (num<5) {

			int x = gen.nextInt(5);
			int y = gen.nextInt(5);				
			
			for (int i = 0; i<=Base.length-1; i++) {
				for (int j=0; j<Base[i].length; j++) {
			
					if (Base[i][j] != "Q ") { 
						if ((x-1==i)&&(y-1==j)) {
							Base[i][j] = "Q ";	
							num++;
						}
					}	
				}
			}	
		}
		
		return Base;
	}
}
