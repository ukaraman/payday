package com.payday.auth.dao;

import java.util.List;

import com.payday.auth.model.User;

public interface UserDAO extends AbstractDAO<User, Long> {
	
	public User loadByUsername(String username);
	public List<User> getActiveUsers();

}
