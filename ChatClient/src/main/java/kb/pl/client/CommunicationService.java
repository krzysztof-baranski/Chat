package kb.pl.client;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kb.pl.protocol.IChatService;
import kb.pl.protocol.Message;
import kb.pl.protocol.MessageStorage;

@Component
public class CommunicationService {
	 private final BurlapClient burlapClient;
	 private final HessianClient hessianClient;
//	    private final XmlRpc xmlRpc;
	 IChatService burlapService;
	 IChatService hessianService;
	 private final ApplicationEventPublisher publisher;
	 List<Message> messageList = new ArrayList<>();
	 IChatService chatService;
	    
	@Autowired
	public CommunicationService(BurlapClient burlapClient, HessianClient hessianClient, ApplicationEventPublisher publisher) throws MalformedURLException {
		this.publisher = publisher;
		
		this.burlapClient = burlapClient;
		this.hessianClient = hessianClient;
		//        this.xmlRpc = xmlRpc;
		getHessianService();
		getBurlapService();
		//        xmlRpc();
		
		chatService = hessianService;
	}
	
	private void getBurlapService() {
		// TODO Auto-generated method stub
		burlapService = burlapClient.getService();
		System.out.println("@@@@@ getBurlapService " + burlapService);
		
	}

	private void getHessianService() {
		// TODO Auto-generated method stub
		hessianService = hessianClient.getService();
	}

	public void sendMessage(int userId, String username, String messageText) {
		System.out.println("@@@@CommunicationService sendMessage " + messageText);
//		messageText += "messageText :)";
//        Message message = new Message(username, messageText);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
        chatService.sendMessage(userId, username, messageText, timestamp.getTime());
//        CHAT_SERVICES.get(getSelectedTechnology()).sendMessage(channelData.getId(), getLoggedUser().getId(), message);
//        LOGGER.info("Message send on channel <{}>", channelData.getName());
    }
	
	@Scheduled(fixedRate = 1000)
	public List<Message> readMessages() {
		 System.out.println("@@@ CommunicationService readMessage ");

		 messageList = chatService.readMessages();
		 System.out.println("@@@ CommunicationService readMessage " + messageList.size() + "!");
		 MessageEvent MessageEvent = new MessageEvent(this, messageList);
			publisher.publishEvent(MessageEvent);
		 return messageList;
	}
	
	@Scheduled(fixedRate = 1000 * 60)
	public void clearList () {
		System.out.println("@@ CommunicationService clear message list");
		MessageStorage.clearList();
	}
	
	public int login (String username) {
		System.out.println("@@@@@@@@ login " + username );
		return chatService.login(username);
	}

	public void setTechnology(String protocol) {
		// TODO Auto-generated method stub
		if (protocol.equals("hessian")) {
			chatService = hessianService;
		} else if (protocol.equals("xmlrpc")) {
//			chatService = xmlRpcService;
			// temporary fix
			chatService = burlapService;
		} else {
			chatService = burlapService;
		}
	}

	public void logout(int userId) {
		// TODO Auto-generated method stub
		chatService.logout(userId);
	}
}
