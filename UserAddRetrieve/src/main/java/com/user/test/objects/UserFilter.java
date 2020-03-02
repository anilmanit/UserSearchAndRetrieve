package com.user.test.objects;

import com.user.test.objects.user.User.Permission;

public class UserFilter{

	/**
	 * Defines the user active state to filter.
	 * 
     * null:   No filter, all users returned
     * True:   Only active users returned
     * False:  Only inactive users returned
	 */
    private final Boolean active;
    
    /**
     * Defines the user permission to filter.
     * 
     * Employee:   All users returned (including managers and admins)
     * Manager:    All admins and managers returned.
     * Admin:      All admins returned.
     */
    private final Permission permission;

    public UserFilter(Boolean active, Permission permission) {
    	
        this.active = active;
        this.permission = permission;
    }

    public Boolean isActive() {
    	
        return this.active;
    }

    public Permission getPermission() {
    	
        return this.permission;
    }

	
}
