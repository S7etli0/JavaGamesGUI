package seaGame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SeaImpl {
	
	private static Database database;
	
	public SeaImpl() {	
		database = new Database(); 
	} 
	
	public static List<SeaBoard> getAllSeaBoard() {
		return database.getResults();
	}
	
	public interface AppService {
		//
			public List<SeaBoard> getAllSeaBoard();
		}
	
	// Database
	public class Database {

		private List<SeaBoard> results;
		
		public Database() {
			
			super();
			results = new ArrayList<>();
		}

		public Database(List<SeaBoard> results) {
			
			super();
			this.results = results;
		}
		
		
		List<SeaBoard> getResults() {		
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();	
			tx.begin();

			TypedQuery<SeaBoard> query = em.createNamedQuery("seaScore.getAll", SeaBoard.class);									
			List<SeaBoard> stats = query.getResultList();
			
			for(SeaBoard theScore: stats) {
				System.out.println(theScore);
			}
			
			tx.commit();
			em.close();
			emf.close();

			return stats;
		}		
	}
}