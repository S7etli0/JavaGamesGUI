package matchGame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MatchImpl {
	
	private static Database database;
	
	public MatchImpl() {	
		database = new Database(); 
	} 
	
	public static List<MatchBoard> getAllMatchBoard() {
		return database.getResults();
	}
	
	public interface AppService {
		//
			public List<MatchBoard> getAllMatchBoard();
		}
	
	// Database
	
	public class Database {

		private List<MatchBoard> results;
		
		public Database() {
			
			super();
			results = new ArrayList<>();
//			init();
		}

		public Database(List<MatchBoard> results) {
			
			super();
			this.results = results;
//			init();		
		}
		
		
		List<MatchBoard> getResults() {		
//			return results;
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();	
			tx.begin();

			TypedQuery<MatchBoard> query = em.createNamedQuery("matchScore.getAll", MatchBoard.class);								
			List<MatchBoard> stats = query.getResultList();
			
			for(MatchBoard theScore: stats) {
				System.out.println(theScore);
			}
			
			tx.commit();
			em.close();
			emf.close();

			return stats;
		}		
	}
}