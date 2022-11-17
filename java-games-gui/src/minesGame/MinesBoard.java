package minesGame;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

	@Entity
	
	@Table (catalog="gaming_score", name = "minesweep")
	@NamedQueries({
		@NamedQuery(name="MinesScore.getAll", query="SELECT p FROM MinesBoard p"),
	})


	public class MinesBoard {
		
		@Id	
		@GeneratedValue	
		@Column(name="game")	
		private int Game;
		
		@Column(name="player")
		private String gameWinner;
		
		@Column(name="round")
		private int theRound;
		
		@Column(name="tiles")
		private String theScore;
		
		@Column(name="status")
		private String theStat;

		@Column(name="date_time")
		private String theTime;

		
		public MinesBoard() {
			
		}
		
		public MinesBoard(String gameWinner, int theRound, String theScore, String theStat, String theTime) { //, int theSequence) {
			super();
			this.gameWinner = gameWinner;
			this.theRound = theRound;
			this.theStat = theStat;
			this.theScore = theScore;
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

		public String getTheStat() {
			return theStat;
		}

		public void setTheStat(String theStat) {
			this.theStat = theStat;
		}
		
		public String getTheTime() {
			return theTime;
		}

		public void setTheTime(String theTime) {
			this.theTime = theTime;
		}
				
	}