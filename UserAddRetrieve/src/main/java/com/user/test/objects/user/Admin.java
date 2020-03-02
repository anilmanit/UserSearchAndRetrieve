package com.user.test.objects.user;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Admin implements User {

	private final Set<Manager> managerList = new HashSet<>();
	
    private UUID uuid;
    private String name;
    private LocalDate birthDay;
    
    private boolean active;

    public Admin() {
    	
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
    	
        return Permission.ADMIN;
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
    	
        final StringBuilder sb = new StringBuilder()
        	.append("Id: ").append(this.uuid)
            .append(", Name: ").append(this.name)
            .append(", Birthday: ").append(this.birthDay)
            .append(", IsActive: ").append(this.active)
            .append(", Permission: ").append(getPermission())
            .append(", Managers: ").append(managerList.stream().map(User::getUuid).collect(Collectors.toSet()).toString());
        
        return sb.toString();
    }

    public Set<Manager> getManagerList() {
        return this.managerList;
    }

    public void addManagers(Manager... managers) {
        managerList.addAll(Arrays.asList(managers));
    }
}
