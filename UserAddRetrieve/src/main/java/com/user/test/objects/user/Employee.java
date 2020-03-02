package com.user.test.objects.user;


import java.time.LocalDate;
import java.util.UUID;


public class Employee implements User {

    private UUID uuid;
    private String name;
    private LocalDate birthDay;
    
    private boolean active;

    public Employee() {
    	
        this.uuid = UUID.randomUUID();
    }

    @Override
    public UUID getUuid() {
    	
        return this.uuid;
    }

    @Override
    public String getName() {
    	
        return this.name;
    }

    @Override
    public void setName(String name) {
    	
        this.name = name;
    }

    @Override
    public boolean isActive() {
    	
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
    	
        this.active = active;
    }

    @Override
    public Permission getPermission() {
    	
        return Permission.EMPLOYEE;
    }
    
    @Override
    public LocalDate getBirthDay() {
    	
		return this.birthDay;
	}

    @Override
	public void setBirthDay(LocalDate birthDay) {
		
		this.birthDay = birthDay;
	}

    @Override
    public String toString() {
    	
        final StringBuilder buffer = new StringBuilder()
        	.append("Id: ").append(this.uuid)
            .append(", Name: ").append(this.name)
            .append(", Birthday: ").append(this.birthDay)
            .append(", IsActive: ").append(this.active)
            .append(", Permission: ").append(getPermission());
        
        return buffer.toString();
    }
}
