package kb.pl.protocol;

/**
 * Hello world!
 *
 */
public class Message {
	private String message;
	private String sender;
	
    public Message(String messageText) {
		this.message = messageText;
	}

	public String getMessage () {
    	return message;
    }
    
    public String getSender () {
    	return sender;
    }
}
