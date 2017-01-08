package kb.pl.server;

import java.sql.Timestamp;
import java.util.List;

public interface IUserService {

	int login(String username, Timestamp timestamp);

	List<User> getUsers();

	int logout(int userId);

}
