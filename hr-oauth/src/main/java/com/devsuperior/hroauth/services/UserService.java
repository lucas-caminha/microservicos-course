package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
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
	
}
