package lineGame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class AppFourLine extends JFrame{
	
	private FourLineImpl appService;
	private FourLinePanel fourLinePanel;
	
	public AppFourLine()  {
		
		appService = new FourLineImpl();

		fourLinePanel = new FourLinePanel();	
		setLayout(new BorderLayout());
		add(fourLinePanel, BorderLayout.CENTER);
		refreshTable();
		
		pack();
		setVisible(true);
		setTitle("FourLineBoard Demo");
	}
	
	public static void main (String[] args) {
		
		AppFourLine app = new AppFourLine();
	}	
	
	public void refreshTable() {
		
		List<FourLineBoard>results = FourLineImpl.getAllFourLineBoard();
		fourLinePanel.setTableModel(results);
		fourLinePanel.updateTable();
	}	
}