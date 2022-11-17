package matchGame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MatchTable extends AbstractTableModel{

	private final int COL = 6;
	private List <MatchBoard> results;
	private String[] columnNames = {"Game", "Player", "Round", "Result", "Status", "Time"};	
	
	
	public MatchTable () {
		
		results = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return COL;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return results.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		if (row<results.size()) {
			
			MatchBoard theScore = results.get(row);
			
			switch(col) {
			
			case 0 : return theScore.getGame(); 
			case 1 : return theScore.getGameWinner(); 
			case 2 : return theScore.getTheRound(); 
			case 3 : return theScore.getTheScore();
			case 4 : return theScore.getTheStat();
			case 5 : return theScore.getTheTime();
			default: return null;
			
			} 
		
		} else {
			return null;
			
		}		
	}

	@Override
	public String getColumnName(int column) {
		
		return columnNames[column];
//		return super.getColumnName(col);				
	}

	public void setResults(List<MatchBoard>results) {
		
		this.results = results;
	}
	
	public void updateTable () {
		
		fireTableDataChanged();
	}
	
}
