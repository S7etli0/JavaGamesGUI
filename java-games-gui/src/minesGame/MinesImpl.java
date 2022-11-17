package minesGame;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MinesImpl {
	
	private static Database database;
	
	public MinesImpl() {	
		database = new Database(); 
	} 
	
	public static List<MinesBoard> getAllMinesBoard() {
		return database.getResults();
	}
	
	public interface AppService {
		
			public List<MinesBoard> getAllMinesBoard();
		}
	
	// Database	
	public class Database {

		private List<MinesBoard> results;
		
		public Database() {
			
			super();
			results = new ArrayList<>();
		}

		public Database(List<MinesBoard> results) {
			
			super();
			this.results = results;
		}
		
		
		List<MinesBoard> getResults() {		
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();	
			tx.begin();

			TypedQuery<MinesBoard> query = em.createNamedQuery("MinesScore.getAll", MinesBoard.class);						
			List<MinesBoard> stats = query.getResultList();
			
			for(MinesBoard theScore: stats) {
				System.out.println(theScore);
			}
			
			tx.commit();
			em.close();
			emf.close();

			return stats;
		}		
	}
}