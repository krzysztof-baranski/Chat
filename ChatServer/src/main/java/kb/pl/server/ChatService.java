package kb.pl.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kb.pl.protocol.IChatService;
import kb.pl.protocol.Message;
import kb.pl.protocol.MessageStorage;

@Service
public class ChatService implements IChatService {
	private IUserService userService;
	private List<User> users = new ArrayList<>();
	
	@Autowired
    public ChatService(IUserService userService) {
        this.userService = userService;
    }

	@Override
	public boolean sendMessage(int senderId, String senderName, String message, long timestamp) {
		// TODO Auto-generated method stub
		System.out.println("@@@@@ ChatService sendMessage " + message);
		
		users = this.userService.getUsers();
	
		System.out.println("@@@ ChatService users " + users);
		Message mess = new Message(senderId, senderName, message, timestamp);
		
		MessageStorage.addMessage(mess);
		return true;
	}
	
	 public List<Message> readMessages() {
		 System.out.println("@@@ ChatService readMessages ");
		 if (MessageStorage.getMessages().size() > 0) {
			 System.out.println(MessageStorage.getMessages().size() +" " + MessageStorage.getMessages().get(0).getMessage());
		 }
	 
		 return MessageStorage.getMessages();
	 }

	@Override
	public int login(String username, Timestamp timestamp) {
		// TODO Auto-generated method stub
		return userService.login(username, timestamp);
	}

	@Override
	public int logout(int userId) {
		// TODO Auto-generated method stub
		return userService.logout(userId);
	}
}
