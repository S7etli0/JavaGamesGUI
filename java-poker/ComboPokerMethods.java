package Svetoslav.Kochanov;

public class ComboPokerMethods {
	
/*
	private int FalseCombos;
	private int SameSuits;
	private int Straight;
	private int RoyalFlush;
	private String RankCounter;
	private int MaxCounter;
		
	public ComboPokerMethods() {
		
		FalseCombos = this.FalseCombos;
		SameSuits = this.SameSuits;
		Straight = this.Straight;
		RoyalFlush = this.RoyalFlush;
		RankCounter = this.RankCounter;
		MaxCounter = this.MaxCounter;
		
	}
*/	
	
	public static int FalseCombos(String[] cards, String[] suits) {
		
		int F = 0;			// counting the false cases
		int M = 1;			// maximum matches allowed 
		
		for(int i=0; i<cards.length; i++) {					
			F = 0;

			for(int j=0; j<cards.length; j++) {

				if ((cards[i].equals(cards[j])) && (suits[i].equals(suits[j]))) {
					F++;
				}	
				
				if (F>M) {
					M = F;
					break;
				}			
			}  
		} return M;					
	}
	
	public static int SameSuits(String suits[]) {
		
		int same=0;
		for(int i=0; i<suits.length; i++) {
			same=0;
			for(int j=0; j<suits.length; j++) {

				if (suits[i].equals(suits[j])) {
				same++;
				}
			}  
		} return same;		
	
	}
	
	public static int Straight(String ranks[], String cards[]) {	
		
		int str = 0;
		int x = 5;
		int first=0;						// the lowest number of the straight
		String copy [] = new String[x];  	// every possible combination for a straight

		while (first<ranks.length-4) {

			for (int i=first; i<x; i++) {						
		
					copy[i-first] = ranks [i];										
				}	
			str = 0;
			for (int k=0; k<cards.length; k++) {
				
				for (int j=0; j<copy.length; j++) {			

					if (cards[k].equals(copy[j])) {
						str++;
						copy[j]="0";
					}			
				}
			}			
			if (str==5) {
				break;		
			}	first++;
				x++;	
		}
			return str;
	}
	
	public static int RoyalFlush (String cards[]) {		
		
		int x=5;
		String royal [] = {"10", "J", "Q", "K", "A"};
		int rc = 0;
		for (int i=0; i<x; i++) {

			for (int j=0; j<x; j++) {

				if (cards[i].equals(royal[j])) {
					rc++;
					royal [j] = "0";
				}
			}
		} return rc;
	}
	
	public static String RankCounter (String cards[]) {		
		
		int count = 0;		// first count
		int max = 1;		// times the first rank repeats
		String rank="";	// the first repetitive rank
		int x = 5;
			
			for(int i=0; i<x; i++) {		
				count=0;
				for(int j=0; j<x; j++) {

					if (cards[i].equals(cards[j])) {
						count++;
					}
				} if (max<count) {
					rank=cards[i];
					max=count;
				}
			} return rank;
	}
	
	public static int MaxCounter (String cards[], String rank) {		
				
		int count = 0;		// second count
		int max = 1;		// times the second rank repeats
		int x = 5;
					
			for(int i=0; i<x; i++) {		
				count=0;
				for(int j=0; j<x; j++) {

					if (cards[i].equals(rank)!=true) {
					
						if (cards[i].equals(cards[j])) {
							count++;
						}
					}
				}
				if (max<count) {
					max=count;
				}
			}
		return max;
	}
	
}
