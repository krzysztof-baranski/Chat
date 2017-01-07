package kb.pl.protocol;

import java.util.List;

public interface IChatService {

	void sendMessage(String username, String message);

	void login(String string);

	List<Message> readMessages();

}
