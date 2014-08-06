package ar.com.hexacta.tpl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;
import ar.com.hexacta.tpl.service.ILoginService;

public class LoginServiceImpl implements ILoginService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUser(username);
	}
}
