package kb.pl.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kb.pl.protocol.Message;
import kb.pl.server.IUserService;;

@Service
public class UserService implements IUserService {
	public ArrayList<User> users = new ArrayList<>();
	public final ArrayList<Message> messages = new ArrayList<>();
	private int lastUserId = -1; 
	private User user;
	
	public int login(String userName, Timestamp userTimestamp) {
		boolean isUserNameExist = false;
		System.out.println("@@ UserService login " + userName );
		// check if username is already taken
		// if so - return with index = -1
		isUserNameExist = checkIsUserExist(userName);
		if (isUserNameExist) {
			return -1;
		}
		lastUserId++;
		user = new User(lastUserId, userName, userTimestamp);
		users.add(user);
		System.out.println("@@@ UserService login " + users.size());
		return lastUserId;
	}
	
	private boolean checkIsUserExist(String userName) {
		// TODO Auto-generated method stub
		for (User user : users) {
			if (user.getUsername().equals(userName)) {
				return true;
			}
		}
		return false;
	}

	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public int logout(int userId) {
		// TODO Auto-generated method stub
		int index = findIndex(userId);
		if (index > -1) {
			return users.indexOf(users.remove(index));
		}
		return -1;
	}

	private int findIndex(int userId) {
		// TODO Auto-generated method stub
		User user;
		for (int i=0;i<users.size();i++) {
			user = users.get(i);
			if (user.getUserId() == userId) {
				return i;
			}
		}
		return -1;
	}
	
}
