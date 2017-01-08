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
	 private final XmlRpcCl xmlRpcCl;
	 
	 IChatService burlapService;
	 IChatService hessianService;
	 IChatService xmlRpcService;
	 private final ApplicationEventPublisher publisher;
	 List<Message> messageList = new ArrayList<>();
	 IChatService chatService;
	    
	@Autowired
	public CommunicationService(BurlapClient burlapClient, HessianClient hessianClient, XmlRpcCl xmlRpcCl, ApplicationEventPublisher publisher) throws MalformedURLException {
		this.publisher = publisher;
		
		this.burlapClient = burlapClient;
		this.hessianClient = hessianClient;
		this.xmlRpcCl = xmlRpcCl;
		getHessianService();
		getBurlapService();
		getXmlRpcService();
		
		chatService = hessianService;
	}
	
	private void getBurlapService() {
		// TODO Auto-generated method stub
		burlapService = burlapClient.getService();
	}

	private void getHessianService() {
		// TODO Auto-generated method stub
		hessianService = hessianClient.getService();
	}
	
	private void getXmlRpcService() throws MalformedURLException {
        xmlRpcService = xmlRpcCl.getService();
    }

	public void sendMessage(int userId, String username, String messageText) {
		Timestamp timestamp;
		System.out.println("@@@@CommunicationService sendMessage " + messageText);
//		messageText += "messageText :)";
//        Message message = new Message(username, messageText);
		timestamp = new Timestamp(System.currentTimeMillis());
		
        chatService.sendMessage(userId, username, messageText, timestamp.getTime());
    }
	
	@Scheduled(fixedRate = 1000)
	public List<Message> readMessages() {
		 
		 messageList = chatService.readMessages();
//		 System.out.println("@@@ CommunicationService readMessage " + messageList.size() + "!");
		 MessageEvent MessageEvent = new MessageEvent(this, messageList);
			publisher.publishEvent(MessageEvent);
		 return messageList;
	}
	
	@Scheduled(fixedRate = 1000 * 60)
	public void clearList () {
		System.out.println("@@ CommunicationService clear message list");
		MessageStorage.clearList();
	}
	
	public int login (String username, Timestamp timestamp) {
		System.out.println("@@@@@@@@ login " + username );
		return chatService.login(username, timestamp);
	}

	public void setTechnology(String protocol) {
		// TODO Auto-generated method stub
		if (protocol.equals("hessian")) {
			chatService = hessianService;
		} else if (protocol.equals("xmlrpc")) {
			chatService = xmlRpcService;
		} else {
			chatService = burlapService;
		}
	}

	public void logout(int userId) {
		// TODO Auto-generated method stub
		chatService.logout(userId);
	}
}
