package kb.pl.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import kb.pl.protocol.Message;

@Component
public class MainWindow extends JFrame implements ApplicationListener {
	
//	private JFrame frame;
	private Label label;
	private JTextField writeMessageField;
	private JButton send;
	private JTextArea receivedMessages;
	private ButtonGroup technologies;
	private JPanel radios;
	private JPanel message;
	private final ApplicationEventPublisher publisher;
	private static String userName;
	
	@Autowired
	private final CommunicationService communicationService;
	
	private static int userId;
	long timestamp;
	
    @Autowired
	public MainWindow(ApplicationEventPublisher publisher, CommunicationService communicationService /*LoginPanel loginPanel, AppPanel appPanel*/) {
//	    this.loginPanel = loginPanel;
//	    this.appPanel = appPanel;
	    initialize();
	    this.publisher = publisher;
	    this.communicationService = communicationService;
	    userId = communicationService.login(userName);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		System.out.println("@@@ Main Windows initialize ");
		userName = JOptionPane.showInputDialog("Podaj imię");
    	if (userName.equals("")) {
    		userName = "anon";
    	}

//    	User user = new User(userName);
//    	user.login(userName);
    	
    	//frame = new JFrame("Chat " + userName);
    	/*frame.*/
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                communicationService.logout(userId);
                System.exit(0);
            }
        };
        addWindowListener(exitListener);
    	label = new Label();
    	label.setText("Napisz wiadomość");
    	label.setSize(50, 30);
    	
    	/*frame.*/
    	setSize(500, 400);
    	setTitle("Chat " + userName);
    	
    	writeMessageField = new JTextField();
    	writeMessageField.setPreferredSize(new Dimension(400 - label.getWidth(), 30));
    	writeMessageField.setText("Napisz wiadomość...");
    	writeMessageField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("aaaa" + e);
			}
		});
    	
    	send = new JButton("Wyślij");
    	send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Send button pressed");
//				MessageEvent MessageEvent = new MessageEvent(this, writeMessageField.getText());
//				publisher.publishEvent(MessageEvent);
				communicationService.sendMessage(userId, userName, writeMessageField.getText());
				//communicationService.readMessages();
			}
		});
    	
    	receivedMessages = new JTextArea();
    	receivedMessages.setOpaque(true);
    	receivedMessages.setBackground(Color.white);
    	receivedMessages.setText("Witaj " + userName + "!");
    	receivedMessages.setEditable(false);
    	receivedMessages.setSize(350, 200);
    	
    	JRadioButton hessianRadioButton = new JRadioButton("Hessian");
    	hessianRadioButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				receivedMessages.setText(receivedMessages.getText() +
						"\nWybrana technologia: Hessian");
				
				communicationService.setTechnology("hessian");
			}
		});
    	JRadioButton burlapRadioButton = new JRadioButton("Burlap");
    	burlapRadioButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				receivedMessages.setText(receivedMessages.getText() +
						"\nWybrana technologia: Burlap");
				
				communicationService.setTechnology("burlap");
			}
		});
    	JRadioButton xmlRpcRadioButton = new JRadioButton("XML RPC");
    	xmlRpcRadioButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				receivedMessages.setText(receivedMessages.getText() +
						"\nWybrana technologia: XML RPC");

				communicationService.setTechnology("xmlrpc");
			}
		});
    	hessianRadioButton.setSelected(true);
    	receivedMessages.setText(receivedMessages.getText() +
				"\nWybrana technologia: Hessian");
    	
    	technologies = new ButtonGroup();
    	technologies.add(hessianRadioButton);
    	technologies.add(burlapRadioButton);
    	technologies.add(xmlRpcRadioButton);
    	
    	radios = new JPanel();
    	radios.setLayout(new GridLayout(1, 3));
    	radios.add(hessianRadioButton);
    	radios.add(burlapRadioButton);
    	radios.add(xmlRpcRadioButton);
    	/*frame.*/
    	add(radios, BorderLayout.NORTH);

    	/*frame.*/
    	add(receivedMessages, BorderLayout.CENTER);
    	
    	message = new JPanel(); 
    	message.setLayout(new FlowLayout());
//	    	message.add(label);
    	message.add(writeMessageField);
    	message.add(send);
    	
    	/*frame.*/
    	add(message, BorderLayout.SOUTH);
 
        System.out.println( "Hello World!" );
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		System.out.println("@@@MainWindow " + event);
//		communicationService.login(userName);
		
		if (event.getClass().equals(MessageEvent.class)) {
			System.out.println("### MainWindow " + event.getClass() + " " + event.getSource());
			onMessageReceived(event);	
		}
		
//		if (event.getClass().equals(Mess))
	}

	private void onMessageReceived(ApplicationEvent event) {
		// TODO Auto-generated method stub
		System.out.println("@@@ MainWindow onMEssageReceived " + timestamp);
		List<Message> list = new ArrayList<>();
		list = ((MessageEvent) event).getMessages();
		System.out.println(list);
//		for (Message message : list) {
//		Message message = list.get(list.size() - 1);
		
		int index = findIndexOfMessage(list, timestamp);
		if (index < 0) {
			return;
		}
		Message message;
		
		for (int i = index; i<list.size(); i++) {
			message = list.get(i);
			if (!message.getSenderName().equals(userName)) {
				receivedMessages.setText(receivedMessages.getText() + message.getSenderName() + 
					": " + message.getMessage() + "\n");
				System.out.println("@@@ MainWindow " + message.getTimestamp() + " " +
					message.getSenderId() +  " " + message.getSenderName() + 
					": " + message.getMessage() + "\n" );
				System.out.println("@@ " + userId + " " + userName);
			} else {
				// weż wiadomość i wyrównaj do prawej!!
			}
		}
		timestamp = list.get(list.size() -1).getTimestamp();
//		}
//		receivedMessages.setText(receivedMessages.getText());
	}

	private int findIndexOfMessage(List<Message> list, long timestamp) {
		// TODO Auto-generated method stub
		long lastMessageTimestamp = timestamp;
		Message m;
		
		for (int i = 0; i<list.size();i++) {
			m = list.get(i);
			long ts = m.getTimestamp();
			if (ts > lastMessageTimestamp) {
				return i;
			}
		}
		return -1;
	}

}
