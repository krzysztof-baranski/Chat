package kb.pl.protocol;

import java.io.Serializable;

/**
 * Hello world!
 *
 */
public class Message implements Serializable {
	private String message;
	private String sender;
	
    public Message(String sender, String messageText) {
    	this.sender = sender;
		this.message = messageText;
	}

	public String getMessage () {
    	return message;
    }
    
    public String getSender () {
    	return sender;
    }
}
