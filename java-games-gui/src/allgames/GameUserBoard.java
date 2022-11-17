package allgames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity

@Table (catalog="gaming_score", name = "allplayers")

public class GameUserBoard {

	@Id	
	@Column(name="name")	
	private String user;
	@Column(name="password")	
	private int code;
	
	
	public GameUserBoard (String user, int code) {

		super();
		this.user = user;
		this.code=code;
	}

	public GameUserBoard () {

		super();
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return "GameUserBoard [user=" + user + ", code=" + code + "]";
	}	
	
}
