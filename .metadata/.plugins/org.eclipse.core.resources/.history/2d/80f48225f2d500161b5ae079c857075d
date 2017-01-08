package kb.pl.client;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	
	private final String userName;
	private final int userId;
	
	@Autowired
	private CommunicationService communicationService;
	
	public User (int id, String userName) {
		this.userId = id;
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
	
	public int getUserId () {
		return this.userId;
	}

//	public void login(String un) {
//		// TODO Auto-generated method stub
//		communicationService.login(un);
//	}
	
}
