package com.payday.common.dto;

import java.util.HashSet;
import java.util.Set;


public class UserDTO {
	
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private Set<GroupDTO> groups = new HashSet<GroupDTO>();
	
	public Set<GroupDTO> getGroups() {
		return groups;
	}
	public void setGroups(Set<GroupDTO> groups) {
		this.groups = groups;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
