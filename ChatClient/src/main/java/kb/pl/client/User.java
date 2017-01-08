package kb.pl.client;

import java.sql.Timestamp;

public class User {
	private int userId;
	private String username;
	private Timestamp timestamp;
	
	public User(int userId, String username, Timestamp timestamp) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.username = username;
		this.timestamp = timestamp;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

}
