package kb.pl.protocol;

import java.util.ArrayList;
import java.util.List;

public class MessageStorage {

	private static List <Message> messages = new ArrayList<Message>();
	public MessageStorage() {
		
	}
	
	public static List<Message> getMessages() {
		return messages;
	}

	public static void addMessage(Message mess) {
		// TODO Auto-generated method stub
		messages.add(mess);
	}
	
	public static void clearList () {
		messages.clear();
	}

}
