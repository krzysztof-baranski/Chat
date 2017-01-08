package kb.pl.server;

import java.util.List;

public interface IUserService {

	int login(String username);

	List<User> getUsers();

	int logout(int userId);

}
