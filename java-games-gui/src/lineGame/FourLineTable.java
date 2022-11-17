package lineGame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FourLineTable extends AbstractTableModel{

	private final int COL = 6;
	private List <FourLineBoard> results;
	private String[] columnNames = {"Game", "Winner", "Round", "Score", "Draw", "Time"};	
	
	
	public FourLineTable () {
		
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
			
			FourLineBoard theScore = results.get(row);
			
			switch(col) {
			
				case 0 : return theScore.getGame(); 
				case 1 : return theScore.getGameWinner(); 
				case 2 : return theScore.getTheRound(); 
				case 3 : return theScore.getTheScore(); 
				case 4 : return theScore.getTheTie(); 
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
	}

	public void setResults(List<FourLineBoard>results) {
		
		this.results = results;
	}
	
	public void updateTable () {
		
		fireTableDataChanged();
	}
	
}
