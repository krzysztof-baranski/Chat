package kb.pl.server;

import java.util.List;

public interface IUserService {

	void login(String username);

	List<User> getUsers();

}
