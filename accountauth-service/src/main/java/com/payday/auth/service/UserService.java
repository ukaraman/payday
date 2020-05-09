package com.payday.auth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.payday.auth.dao.UserDAO;
import com.payday.auth.model.User;
import com.payday.common.config.UserPrincipal;

@Service
public class UserService extends AbstractServiceImplement<User, Long>{
	
	private static final Long USER_GROUP_ID = (long) 1;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private UserDAO userDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public UserService(
			UserDAO userDAO,
			BCryptPasswordEncoder bCryptPasswordEncoder
	){
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDAO = userDAO;
	}
	
	public User registerUser(User user){
		user.setActive(true);
		//handle password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//Add the user to the group of users
		return userDAO.save(user);
	}
	
	
	public List<User> getActiveUsers(){
		return userDAO.getActiveUsers();
	}
	
	public UserPrincipal getCurrentUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated =  false;
        
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            isAuthenticated =  true;
        }
         
        if(auth != null && isAuthenticated){
        	logger.info("Principal is "+auth.getPrincipal());
        	/**
        	 * In case a client credentials flow is used, the Principal will be a String corresponding to the Oauth2 client_id of the calling service
        	 * In case local authentication or the authorization code flow is used, the Principal will be an object of type UserPrincipal
        	 */
        	if(! (auth.getPrincipal() instanceof String)) return  (UserPrincipal)auth.getPrincipal();
        }
        
        return null;
	}
	
}
