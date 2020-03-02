package com.user.test.factories;

import java.util.Stack;

import com.user.test.objects.user.User;
import com.user.test.util.UserUtil;

public class UserFactory {

    private static char letter = 'A';
    private static Stack<User> stackOfUsers;
    
    /**
     * Implement the getUser factory method.
     * 
     * @return the new User created.
     */
    public static User getUser() {
    	return stackOfUsers.pop();
    }
    public Stack<User> addUsersToStack(User user) {
    	if(stackOfUsers==null){
    		stackOfUsers = new Stack<>();  
    	}
    	stackOfUsers.push(user);
    	return stackOfUsers;
    }
    
    public static void createUser(User user) {
    	
    	user.setName(getRandomName());
    	user.setActive(UserUtil.generateRandomBoolean());
    	
    	if(stackOfUsers==null){
    		stackOfUsers = new Stack<>();  
    	}
    	stackOfUsers.push(user);    	
    }
    
    private static String getRandomName() {

        return new StringBuilder()
			.append(nextLetter())
			.append(nextLetter())
			.append(nextLetter())
			.append(nextLetter())
			.toString();
    }

    private static char nextLetter() {
    	
        if (UserFactory.letter >= 'Z') {
        	UserFactory.letter = 'A';
        }
        
        return UserFactory.letter++;
    }
}
