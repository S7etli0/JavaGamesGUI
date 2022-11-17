package shipGame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BattleshipsImpl {
	
	private static Database database;
	
	public BattleshipsImpl() {	
		database = new Database(); 
	} 
	
	public static List<BattleShipsBoard> getAllBattleShipsBoard() {
		return database.getResults();
	}
	
	public interface AppService {
		//
			public List<BattleShipsBoard> getAllBattleShipsBoard();
		}
	
	// Database
	public class Database {

		private List<BattleShipsBoard> results;
		
		public Database() {
			
			super();
			results = new ArrayList<>();
		}

		public Database(List<BattleShipsBoard> results) {
			
			super();
			this.results = results;
		}
		
		
		List<BattleShipsBoard> getResults() {		
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();	
			tx.begin();

			TypedQuery<BattleShipsBoard> query = em.createNamedQuery("shipScore.getAll", BattleShipsBoard.class);			
			List<BattleShipsBoard> stats = query.getResultList();
			
			for(BattleShipsBoard theScore: stats) {
				System.out.println(theScore);
			}
			
			tx.commit();
			em.close();
			emf.close();

			return stats;
		}		
	}
}