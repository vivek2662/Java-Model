package venkat.cars.model.service;

import java.util.List;

import venkat.cars.model.domain.Role;
import venkat.cars.model.domain.User;

public interface UserService  {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoletoUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();

}
