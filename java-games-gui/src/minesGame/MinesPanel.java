package minesGame;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class MinesPanel extends JPanel{
	
	private MinesTable tableModel; 
	private JTable resultsTable;
	
	public MinesPanel() {
		
		tableModel = new MinesTable (); 
		resultsTable = new JTable (tableModel);
		
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10,30,10,30));
		add(new JScrollPane(resultsTable),BorderLayout.CENTER);				
	}

	public void setTableModel(List<MinesBoard>results) {
		
		tableModel.setResults(results);
	}
	
	public void updateTable() {
		
		tableModel.updateTable();		
	}
	
}
