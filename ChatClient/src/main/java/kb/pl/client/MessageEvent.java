package kb.pl.client;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import kb.pl.protocol.Message;
import kb.pl.protocol.MessageStorage;

@SuppressWarnings("serial")
public class MessageEvent extends ApplicationEvent {

//    private final List<Message> messages;
    private final List<Message> messages;

    public MessageEvent(Object source, List<Message> messageList) {
        super(source);
//        this.messages = string;
        this.messages = messageList;
        System.out.println("@@@ MessageEvent " + messageList.toString());
    }

    public List<Message> getMessages() {
    	System.out.println("@@@MessageEvent " + MessageStorage.getMessages() + " " +messages.size());
//        return MessageStorage.getMessages();
    	return messages;
    }

}
