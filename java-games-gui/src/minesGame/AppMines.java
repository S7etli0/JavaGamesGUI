package minesGame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class AppMines extends JFrame{
	
	private MinesImpl appService;
	private MinesPanel minesPanel;
	
	public AppMines()  {
		
		appService = new MinesImpl();

		minesPanel = new MinesPanel();	
		setLayout(new BorderLayout());
		add(minesPanel, BorderLayout.CENTER);
		refreshTable();
		
		pack();
		setVisible(true);
		setTitle("MinesBoard Demo");
	}
	
	public static void main (String[] args) {
		
		AppMines app = new AppMines();
	}	
	
	public void refreshTable() {
		
		List<MinesBoard>results = MinesImpl.getAllMinesBoard();
		minesPanel.setTableModel(results);
		minesPanel.updateTable();
	}	
}