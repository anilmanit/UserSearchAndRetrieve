package com.user.test.objects.user;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Manager implements User {

	private final Set<Employee> employeeList = new HashSet<>();
	
    private UUID uuid;
    private String name;
    private LocalDate birthDay;
    
    private boolean active;

    public Manager() {
    	
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
    	
        return active;
    }

    @Override
    public void setActive(boolean active) {
    	
        this.active = active;
    }

    @Override
    public Permission getPermission() {
    	
        return Permission.MANAGER;
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
        	.append("Id: ").append(uuid)
            .append(", Name: ").append(this.name)
            .append(", Birthday: ").append(this.birthDay)
            .append(", IsActive: ").append(this.active)
            .append(", Permission: ").append(getPermission())
            .append(", Employees: ").append(employeeList.stream().map(User::getUuid).collect(Collectors.toSet()).toString());
        
        return sb.toString();
    }

    public Set<Employee> getEmployeeList() {
    	
        return this.employeeList;
    }

    public void addEmployees(Employee... employee) {
    	
        this.employeeList.addAll(Arrays.asList(employee));
    }
}
