package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userClient;
	
	public User findByEmail(String email) {
		User user = userClient.findByEmail(email).getBody();
		if(user == null) {
			logger.error("Email Not Found: " + email);
			throw new IllegalArgumentException("Email Not Found");
		}
		logger.error("Email Found: " + email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userClient.findByEmail(username).getBody();
		if(user == null) {
			logger.error("Email Not Found: " + username);
			throw new IllegalArgumentException("Email Not Found");
		}
		logger.error("Email Found: " + username);
		return user;
	}
	
}
