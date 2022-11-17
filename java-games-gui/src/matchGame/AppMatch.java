package matchGame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class AppMatch extends JFrame{
	
	private MatchImpl appService;
	private MatchPanel matchPanel;
	
	public AppMatch()  {
		
		appService = new MatchImpl();

		matchPanel = new MatchPanel();	
		setLayout(new BorderLayout());
		add(matchPanel, BorderLayout.CENTER);
		refreshTable();
		
		pack();
		setVisible(true);
		setTitle("MatchBoard Demo");
	}
	
	public static void main (String[] args) {
		
		AppMatch app = new AppMatch();
	}	
	
	public void refreshTable() {
		
		List<MatchBoard>results = MatchImpl.getAllMatchBoard();
		matchPanel.setTableModel(results);
		matchPanel.updateTable();
	}	
}