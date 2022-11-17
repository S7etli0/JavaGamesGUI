package seaGame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class AppSea extends JFrame{
	
	private SeaImpl appService;
	private SeaPanel seaPanel;
	
	public AppSea()  {
		
		appService = new SeaImpl();

		seaPanel = new SeaPanel();	
		setLayout(new BorderLayout());
		add(seaPanel, BorderLayout.CENTER);
		refreshTable();
		
		pack();
		setVisible(true);
		setTitle("SeaBoard Demo");
	}
	
	public static void main (String[] args) {
		
		AppSea app = new AppSea();
	}	
	
	public void refreshTable() {
		
		List<SeaBoard>results = SeaImpl.getAllSeaBoard();
		seaPanel.setTableModel(results);
		seaPanel.updateTable();
	}	
}