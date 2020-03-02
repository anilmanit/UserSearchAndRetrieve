package com.user.test.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.user.test.constants.UserConstants;
import com.user.test.factories.UserFactory;
import com.user.test.infra.EntityLogger;
import com.user.test.infra.ResourceLoader;
import com.user.test.objects.user.Admin;
import com.user.test.objects.user.Employee;
import com.user.test.objects.user.Manager;
import com.user.test.objects.user.User;
import com.user.test.objects.user.User.Permission;
import com.user.test.predicate.UserPredicate;

/**
 * The UserRegistryImpl class is used to manage the users persistence in the
 * application.
 */
@Component
public class UserRegistryImpl implements UserRegistry {

	/**
	 * This is the data structure that contains all users by company ID.
	 * Important: for each company, the user list must always remain sorted by
	 * the users' name.
	 */
	public static final Map<Integer, List<User>> REGISTRY = Collections.synchronizedMap(new HashMap<>());
	private static List<User> userList = null;
	/**
	 * This attribute represents the component name.
	 */
	private static final String THIS_COMPONENT_NAME = UserRegistryImpl.class.getName();
	/**
	 * This creates a instance of logger for the component name as class name.
	 */
	private static Logger logger = EntityLogger.getUniqueInstance().getLogger(THIS_COMPONENT_NAME);
	static {

		/*
		 * Important: fill the map registry statically for 2 companies (ID 111
		 * and 333).
		 * 
		 * The user list must use the UserFactory. The factory should be able to
		 * generate a user regardless of the defined parameters. Use this
		 * factory to generate a list of at least 20 users, 5 of them managers
		 * and 2 admins. Random names should generated for users. It is
		 * important to have both active and inactive users of each type.
		 */
		Set<Manager> managerList = null;
		Set<Employee> employeeList = null;
		for (int companyCount = 1; companyCount <= Integer
				.parseInt(ResourceLoader.getPropertyValue(UserConstants.WORKJAM_COMPANY_COUNT)); companyCount++) {
			userList = new ArrayList<User>();
			managerList = new HashSet<>();
			employeeList = new HashSet<>();
			for (int count = 0; count < Integer
					.parseInt(ResourceLoader.getPropertyValue(UserConstants.WORKJAM_USER_COUNT)); count++) {
				User user;
				if (count < 5) {
					user = new Manager();
					UserFactory.createUser(user);
				} else if (count >= 5 && count < 7) {
					user = new Admin();
					UserFactory.createUser(user);
				} else {
					user = new Employee();
					UserFactory.createUser(user);
				}
				if (user instanceof Manager && user.getPermission().toString().equals(Permission.MANAGER.toString())) {

					managerList.add((com.user.test.objects.user.Manager) user);

				} else if (user instanceof Employee && user.getPermission().toString().equals(Permission.EMPLOYEE.toString())) {

					employeeList.add((com.user.test.objects.user.Employee) user);
				}
				if (user instanceof Admin && user.getPermission().toString().equals(Permission.ADMIN.toString())) {
						((Admin) user).getManagerList().addAll(managerList);

				} else if (user instanceof Manager && user.getPermission().toString().equals(Permission.MANAGER.toString())) {
						((Manager) user).getEmployeeList().addAll(employeeList);
				}
				userList.add(user);

				
			}
			if (companyCount == 1)
				REGISTRY.put(Integer.parseInt(ResourceLoader.getPropertyValue(UserConstants.WORKJAM_COMPANY_ID_1)),
						userList);
			else
				REGISTRY.put(Integer.parseInt(ResourceLoader.getPropertyValue(UserConstants.WORKJAM_COMPANY_ID_2)),
						userList);

		}

	}

	/**
	 * Returns an ordered and filtered list of users related to the specified
	 * company ID.
	 * 
	 * @param companyId
	 *            the company ID.
	 * @param filter
	 *            the predicate that will be used to filter users. If null is
	 *            specified, no filter will be used.
	 * @return a list of users.
	 */
	@Override
	public List<User> getUsers(int companyId, Predicate<User> filter) {
		logger.log(Level.INFO, "Inside getUsers :: ");
		if (filter == null) {

			final List<User> userList = REGISTRY.get(companyId);
			logger.log(Level.INFO, "Inside getUsers List Of Users:: " + REGISTRY);

			UserPredicate.filterUser(userList, filter);

			if (userList != null)
				return Collections.unmodifiableList(userList);
		} else {

			final List<User> userList = REGISTRY.get(companyId);
			List<User> userListFilter = null;
			logger.log(Level.INFO, "Inside getUsers List Of Users before predicate :: " + REGISTRY);
			userListFilter = UserPredicate.filterUser(userList, filter);
			logger.log(Level.INFO, "Inside getUsers List Of Users after predicate :: " + userListFilter);

			if (userListFilter != null)
				return Collections.unmodifiableList(userListFilter);
		}

		/*
		 * Complete the method to return a filtered list based on the specified
		 * filter predicate
		 */

		return Collections.emptyList();
	}

	/**
	 * Adds a user to the related company ID user list.
	 * 
	 * @param companyId
	 *            the company ID .
	 * @param user
	 *            the user to add.
	 * @return true if the user was successfully added.
	 */
	@Override
	public boolean addUser(int companyId, User user) {

		if (user == null)
			throw new IllegalArgumentException("Invalid user (null).");

		/*
		 * Complete the method to add the specified user into the registry
		 */

		int registrySize = REGISTRY.get(companyId).size();
		logger.log(Level.INFO, "Inside addUser registrySize :: " + registrySize);
		REGISTRY.get(companyId).add(user);
		// REGISTRY.put(companyId, addUserList);
		int registrySizeAddUser = REGISTRY.get(companyId).size();
		logger.log(Level.INFO, "Inside addUser registrySizeAddUser :: " + registrySizeAddUser);
		if (registrySizeAddUser > registrySize)
			return true;

		return false;
	}
}
