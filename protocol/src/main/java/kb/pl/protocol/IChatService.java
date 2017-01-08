package kb.pl.protocol;

import java.sql.Timestamp;
import java.util.List;

public interface IChatService {

	void sendMessage(int userId, String username, String message, long timestamp);

	int login(String username, Timestamp timestamp);

	int logout(int userId);
	
	List<Message> readMessages();

}
