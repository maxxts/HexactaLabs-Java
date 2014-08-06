package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.IUsersService;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;

public class UsersServiceImpl implements IUsersService {
	private static int MIN_LENGTH_USERNAME = 3;
	private static int MIN_LENGTH_PASSWORD = 6;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUser(Long userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	public boolean createUser(User user) {
		if (validateUser(user)){
			userRepository.save(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		if (!validateUser(user)){
			userRepository.update(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

	
	private boolean validateUsername(String username){
		if (username.length() < MIN_LENGTH_USERNAME){
			return false;
		}
		boolean nonAlphanumeric = username.matches("^.*[^a-zA-Z0-9 ].*$");
		if(nonAlphanumeric){
			return false;
		}
		//TODO: ver si hay mas validaciones para hacer
		return true;
	}
	
	private boolean validatePassword(String password){
		if (password.length() < MIN_LENGTH_PASSWORD){
			return false;
		}
		if (password.contains(" ")){
			return false;
		}
		//TODO: ver si hay mas validaciones para hacer
		return true;
	}
	
	private boolean validateUser(User user){
		return (validateUsername(user.getUsername()) && validatePassword(user.getPassword()));
	}
}
