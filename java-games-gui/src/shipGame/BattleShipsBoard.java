package shipGame;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

	@Entity
	
	@Table (catalog="gaming_score", name = "battleships")
	@NamedQueries({
		@NamedQuery(name="shipScore.getAll", query="SELECT p FROM BattleShipsBoard p"),
	})
	
	
	public class BattleShipsBoard {
		
		@Id	
		@GeneratedValue	
		@Column(name="game")	
		private int Game;
		
		@Column(name="winner")
		private String gameWinner;
		
		@Column(name="round")
		private int theRound;
		
		@Column(name="score")
		private String theScore;

		@Column(name="draw")
		private int theTie;
		
		@Column(name="date_time")
		private String theTime;


		public BattleShipsBoard() {
			
		}
		
		public BattleShipsBoard(String gameWinner, int theRound, String theScore, int theTie, String theTime) { //, int theSequence) {
			super();
			this.gameWinner = gameWinner;
			this.theRound = theRound;
			this.theScore = theScore;
			this.theTie = theTie;
			this.theTime = theTime;
		}

		public int getGame() {
			return Game;
		}

		public void setGame(int game) {
			Game = game;
		}

		public String getGameWinner() {
			return gameWinner;
		}

		public void setGameWinner(String gameWinner) {
			this.gameWinner = gameWinner;
		}

		public int getTheRound() {
			return theRound;
		}

		public void setTheRound(int theRound) {
			this.theRound = theRound;
		}

		public String getTheScore() {
			return theScore;
		}

		public void setTheScore(String theScore) {
			this.theScore = theScore;
		}

		public int getTheTie() {
			return theTie;
		}

		public void setTheTie(int theTie) {
			this.theTie = theTie;
		}

		public String getTheTime() {
			return theTime;
		}

		public void setTheTime(String theTime) {
			this.theTime = theTime;
		}
		
	}
