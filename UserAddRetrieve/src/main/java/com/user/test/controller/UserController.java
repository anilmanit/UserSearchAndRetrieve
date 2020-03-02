package com.user.test.controller;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.test.infra.EntityLogger;
import com.user.test.objects.UserFilter;
import com.user.test.objects.user.Employee;
import com.user.test.objects.user.User;
import com.user.test.objects.user.User.Permission;
import com.user.test.predicate.UserPredicate;
import com.user.test.service.UserService;
@RestController
@RequestMapping("/home")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * This attribute represents the component name.
	 */
	private static final String THIS_COMPONENT_NAME = UserController.class.getName();
	/**
	 * This creates a instance of logger for the component name as class name.
	 */
	private static Logger logger = EntityLogger.getUniqueInstance().getLogger(THIS_COMPONENT_NAME);
	/*
	 * Write a method that handles a RESTful GET request and returns users from UserService in the JSON format.
	 * Important: when returning users, the birthday date must not be included in the transfered data.
	 * 
	 * Add a path parameter for the company ID.
	 * Add an optional parameter to filter users based on user permissions (admin, manager or employee).
	 * Add an optional parameter to filter users based the active state.
	 * 
	 * @see UserService
	 * @see User.Permission
	 */
	@RequestMapping(value = "/user/getuser/{companyid}", method ={ RequestMethod.POST,RequestMethod.GET} ,
    		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<User> getUsers(@PathVariable int companyid,UserFilter filter) {
		Predicate<User> filterPredicate=null;
		if(filter.isActive()){
			logger.log(Level.INFO, "Inside Active User Filter :: ");
			filterPredicate=UserPredicate.isActiveUser();
			

		}else if(filter.getPermission().toString().equals(Permission.ADMIN.toString())){
			logger.log(Level.INFO, "Inside ADMIN User Filter :: ");
			filterPredicate=UserPredicate.isAdmin();
			
		}else if(filter.getPermission().toString().equals(Permission.EMPLOYEE.toString())){
			logger.log(Level.INFO, "Inside EMPLOYEE User Filter :: ");
			filterPredicate=UserPredicate.isEmployee();
		}else if(filter.getPermission().toString().equals(Permission.MANAGER.toString())){
			logger.log(Level.INFO, "Inside MANAGER User Filter :: ");
			filterPredicate=UserPredicate.isManager();
		}
		//Predicate<User> filterPredicate=(Predicate<User>) filter;
		return userService.getUsers(companyid, filterPredicate);

	}
	
	@RequestMapping(value = "/user/getuserwithoutfilter/{companyid}", method = RequestMethod.GET , produces = "application/json")
	public List<User> getUsersWithoutFilter(@PathVariable int companyid) {
		logger.log(Level.INFO, "Inside getUsersWithoutFilter Method :: ");
		return userService.getUsers(companyid, null);

	}
	/*
	 * Write a method that handles a RESTful POST request that adds a user using the UserService.
	 * 
	 * @see UserService
	 */
   
    @RequestMapping(value= "/user/adduser/{companyid}", method ={ RequestMethod.POST,RequestMethod.GET} ,
    		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public boolean addUser( @PathVariable int companyid,  Employee user) {
    	logger.log(Level.INFO, "Inside adduser Method :: ");
		return userService.addUser(companyid, user);

	}
}
