package com.user.test.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.user.test.objects.user.User;

public class UserPredicate implements Predicate {
	public static Predicate<User> isEmployee() {
		return p -> p.isActive() && (p.getPermission().toString().equalsIgnoreCase("EMPLOYEE"));
	}

	public static Predicate<User> isManager() {
		return p -> p.isActive() && (p.getPermission().toString().equalsIgnoreCase("MANAGER"));
	}

	public static Predicate<User> isAdmin() {
		return p -> p.isActive() && (p.getPermission().toString().equalsIgnoreCase("ADMIN"));
	}

	public static Predicate<User> isInActiveEmployee() {
		return p -> !p.isActive() && (p.getPermission().toString().equalsIgnoreCase("EMPLOYEE"));
	}

	public static Predicate<User> isInActiveManager() {
		return p -> !p.isActive() && (p.getPermission().toString().equalsIgnoreCase("MANAGER"));
	}

	public static Predicate<User> isInactiveAdmin() {
		return p -> !p.isActive() && (p.getPermission().toString().equalsIgnoreCase("ADMIN"));
	}

	public static Predicate<User> isActiveUser() {
		return p -> p.isActive() && (p.getPermission().toString().equalsIgnoreCase("MANAGER")
				|| p.getPermission().toString().equalsIgnoreCase("EMPLOYEE")
				|| p.getPermission().toString().equalsIgnoreCase("ADMIN"));
	}

	public static List<User> filterUser(List<User> users, Predicate<User> predicate) {
		if (predicate != null)
			return users.stream().filter(predicate).collect(Collectors.<User> toList());
		return new ArrayList<User>();
	}

	@Override
	public boolean test(Object t) {
		// TODO Auto-generated method stub
		return false;
	}
}
