package kb.pl.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kb.pl.protocol.IChatService;

@Service
public class ChatService implements IChatService {
	private final IUserService userService;
	
	@Autowired
    public ChatService(/*IChannelService channelService,*/ IUserService userService) {
//        this.channelService = channelService;
        this.userService = userService;
    }

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("@@@@@ChatService sendMessage " + message);
	}

	@Override
	public void login(String username) {
		// TODO Auto-generated method stub
		userService.login(username);
	}
}
