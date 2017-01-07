package kb.pl.server;

import java.util.ArrayList;
import java.util.List;

import kb.pl.protocol.Message;
import kb.pl.protocol.MessageStorage;

public class User {
	private final String username;
	private final List<Message> messages = new ArrayList<>();
	
	public User (String username) {
		System.out.println("@@@ Server User constructor " + username);
		this.username = username;
	}
	
	public String getUsername () {
		return this.username;
	}

	public void newMessage(Message message) {
		// TODO Auto-generated method stub
//		List<Message> messages = new ArrayList<>();
		System.out.println("Server User newMessage " + message.getSender() + " " + message.getMessage());
        messages.add(message);
        MessageStorage.addMessage(message);
	}

	public List readMessages() {
		// TODO Auto-generated method stub
		System.out.println("User readMessages " + messages);
		return messages;
	}
	
	
}
