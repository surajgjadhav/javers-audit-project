package com.javers.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javers.test.exception.RecordNotFoundException;
import com.javers.test.model.User;
import com.javers.test.repo.UserRepository;

@RestController
public class userInfoMgmtController {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used to save new User
	 * 
	 * @param newUser
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User saveUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	/**
	 * This is the put request to update User email having given Upload ID
	 * 
	 * @param userId
	 * @param email
	 * @return
	 * @throws RecordNotFoundException
	 */
	@RequestMapping(value = "/email/{userId}", method = RequestMethod.PUT)
	public User updateUserEmailById(@PathVariable("userId") final Integer userId,
			@RequestParam("Email") final String email) throws RecordNotFoundException {
		return userRepository.findById(userId).map(x -> {
			x.setEmail(email);
			return userRepository.save(x);
		}).orElseThrow(() -> new RecordNotFoundException(userId));

	}

	/**
	 * This is the put request to update User address having given Upload ID
	 * 
	 * @param userId
	 * @param email
	 * @return
	 * @throws RecordNotFoundException
	 */
	@RequestMapping(value = "/address/{userId}", method = RequestMethod.PUT)
	public User updateUserAddressById(@PathVariable("userId") final Integer userId,
			@RequestParam("Address") final String address) throws RecordNotFoundException {
		return userRepository.findById(userId).map(x -> {
			x.setAddress(address);
			return userRepository.save(x);
		}).orElseThrow(() -> new RecordNotFoundException(userId));

	}

	/**
	 * This method is used to delete user
	 * 
	 * @param userId
	 */
	@DeleteMapping("/user/{userId}")
	public void deleteBook(@PathVariable Integer userId) {
		userRepository.deleteById(userId);
	}
}
