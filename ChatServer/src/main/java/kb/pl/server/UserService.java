package kb.pl.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kb.pl.protocol.Message;

@Service
public class UserService implements IUserService {
	ArrayList<User> users = new ArrayList<>();
	public void login(String userName) {
		User user = new User(userName);
		users.add(user);
	}
	
	public void newMessage (String username, Message message) {
		for (int i = 0; i< users.size(); i++) {
			users.get(i).newMessage(username, message);
		}
	}
	
	public List readMessage () {
		List messages = new ArrayList<>();
		for (int i = 0; i< users.size(); i++) {
			messages.add(users.get(i).readMessages());
		}
		System.out.println("UserService readMessages " + messages);
		return messages;
	}
	
}
