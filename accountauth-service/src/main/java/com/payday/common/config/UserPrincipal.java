package com.payday.common.config;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class UserPrincipal extends User implements OAuth2User{
	
	/**
	 * 
	 */
	
//	First Name, Last Name, Phone Number,
//	Email Address, Password, Gender, Date of Birth
	
	private static final long serialVersionUID = 1L;
	private String email;
	private Map<String, Object> attributes;
	
	public UserPrincipal(
			String username,
			String password,
			boolean enabled,
			boolean accountNonExpired,
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection authorities,
			String email,
			String phoneNumber,
			String gender,
			Date dateOfBirth)
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
	
    @Override
    public String getName() {
        return this.email;
    }
	
}
