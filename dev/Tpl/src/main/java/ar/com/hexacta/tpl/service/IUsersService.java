package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.User;

public interface IUsersService {
	List<User> findAllUsers();

	User findUser(Long userId);
	
	User findUserByUsernameAndPassword(String username, String password);

	void createUser(final User book);

	void updateUser(final User book);

	void deleteUser(final User book);

	void deleteUserById(Long userId);

}
