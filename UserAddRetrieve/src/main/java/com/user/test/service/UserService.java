package com.user.test.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.test.objects.user.User;
import com.user.test.registry.UserRegistryImpl;

@Service
public class UserService {
	/**
	 * The user registry instance that contains users.
	 */
	@Autowired
	private UserRegistryImpl userRegistry;
	/*
	 * Add method(s) to return users from the UserRegistry. This class shall
	 * support filtering based in specified user permissions and the active
	 * state.
	 */
	public List<User> getUsers(int companyId, Predicate<User> filter){
		
		return userRegistry.getUsers(companyId, filter);
		
	}

	/*
	 * Add method to add a user into the UserRegistry.
	 */
	public boolean addUser(int companyId, User user){
		
		return userRegistry.addUser(companyId,user);
	}
	
}
