package shipGame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

public class AppBattleships extends JFrame{
	
	private BattleshipsImpl appService;
	private BattleshipsPanel battleshipsPanel;
	
	public AppBattleships()  {
		
		appService = new BattleshipsImpl();

		battleshipsPanel = new BattleshipsPanel();	
		setLayout(new BorderLayout());
		add(battleshipsPanel, BorderLayout.CENTER);
		refreshTable();
		
		pack();
		setVisible(true);
		setTitle("BattleShipsBoard Demo");
	}
	
	public static void main (String[] args) {
		
		AppBattleships app = new AppBattleships();
	}	
	
	public void refreshTable() {
		
		List<BattleShipsBoard>results = BattleshipsImpl.getAllBattleShipsBoard();
		battleshipsPanel.setTableModel(results);
		battleshipsPanel.updateTable();
	}	
}