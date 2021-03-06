package kb.pl.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import kb.pl.protocol.Message;
import kb.pl.protocol.MessageStorage;

public class User {
	private final String username;
	private final int userId;
	private final Timestamp userTimestamp;
	private final List<Message> messages = new ArrayList<>();
	
	public User (int userId, String username, Timestamp userTimestamp) {
		System.out.println("@@@ Server User constructor " + userId + " " + username);
		this.userId = userId;
		this.username = username;
		this.userTimestamp = userTimestamp;
	}
	
	public String getUsername () {
		return this.username;
	}
	
	public int getUserId () {
		return this.userId;
	}

	public Timestamp getUserTimestamp () {
		return this.userTimestamp;
	}
	
}
