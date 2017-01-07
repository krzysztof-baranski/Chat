package kb.pl.client;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	
	private final String userName;
	
	@Autowired
	private CommunicationService communicationService;
	
	public User (String userName) {
		this.userName = userName;
	}
//
//	public static void login(int id, String username) {
//		// TODO Auto-generated method stub
//		
//	}

//	public int getId() {
//		// TODO Auto-generated method stub
//		return this.userId;
//	}	
	
	public String getUserName () {
		return this.userName;
	}

//	public void login(String un) {
//		// TODO Auto-generated method stub
//		communicationService.login(un);
//	}
	
}
