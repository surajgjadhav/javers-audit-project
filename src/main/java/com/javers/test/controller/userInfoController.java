package com.javers.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javers.test.model.User;
import com.javers.test.repo.UserRepository;

@RestController
public class userInfoController {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used to get All User Details
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getAllUserDetails() {
		return userRepository.findAll();
	}

	/**
	 * This method is used to get details of user having given userId
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public Optional<User> getUserDetails(@PathVariable final Integer userId) {
		return userRepository.findById(userId);
	}
}
