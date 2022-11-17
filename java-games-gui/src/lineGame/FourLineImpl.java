package lineGame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FourLineImpl {
	
	private static Database database;
	
	public FourLineImpl() {	
		database = new Database(); 
	} 
	
	public static List<FourLineBoard> getAllFourLineBoard() {
		return database.getResults();
	}
	
	public interface AppService {
		//
			public List<FourLineBoard> getAllFourLineBoard();
		}
	
	// Database	
	public class Database {

		private List<FourLineBoard> results;
		
		public Database() {
			
			super();
			results = new ArrayList<>();
		}

		public Database(List<FourLineBoard> results) {
			
			super();
			this.results = results;
		}
		
		
		List<FourLineBoard> getResults() {		
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();	
			tx.begin();

			TypedQuery<FourLineBoard> query = em.createNamedQuery("fourLineScore.getAll", FourLineBoard.class);									
			List<FourLineBoard> stats = query.getResultList();
			
			for(FourLineBoard theScore: stats) {
				System.out.println(theScore);
			}
			
			tx.commit();
			em.close();
			emf.close();

			return stats;
		}		
	}
}
