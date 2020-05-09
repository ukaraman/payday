package com.payday.auth.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.payday.auth.dao.UserDAO;
import com.payday.auth.model.Group;
import com.payday.auth.model.Role;
import com.payday.auth.model.User;
import com.payday.auth.service.GroupService;
import com.payday.common.config.UserPrincipal;

@Service
public class CustomUserDetailsService extends DefaultOAuth2UserService implements UserDetailsService{
	
	private UserDAO userDAO;
	private GroupService groupService;
	
	@Autowired
	public CustomUserDetailsService(UserDAO userDAO, GroupService groupService){
		this.userDAO = userDAO;
		this.groupService = groupService;
	}
	
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try{
	                User domainUser = userDAO.loadByUsername(username);
	                return this.create(domainUser);
                }catch(UsernameNotFoundException e){
                    throw new RuntimeException(e);
                }
    }
	
	public UserPrincipal create(User user, Map<String, Object> attributes) {
		
        UserPrincipal userPrincipal =  this.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
	
	public UserPrincipal create(User user){
		
		boolean enabled = user.isActive();
        boolean accountNonExpired = user.isActive();
        boolean credentialsNonExpired = user.isActive();
        boolean accountNonLocked = user.isActive();

        Set<Role> roles = new HashSet<Role>();
        Set<Group> groups = user.getGroups();

        for(Group group : groups){
            roles.addAll(group.getRoles());
        }

        List<String> springSecurityRoles = treatRoles(roles);
        List<GrantedAuthority> authList = getGrantedAuthorities(springSecurityRoles);

        return new UserPrincipal(
            user.getUsername(),
            user.getPassword(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authList,
            user.getEmail(),
            user.getPhoneNumber(),
            user.getGender(),
            user.getDateOfBirth()
        );

        
	}
	
	
	public List<String> treatRoles(Set<Role> roles) {
        List<String> security_roles = new ArrayList<String>();

        for (Role userRole : roles) {
        	security_roles.add(userRole.getName());
        }

        return security_roles;
    }
	
	
	 public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
	 
	@Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
    		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
        		
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
	
	
	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws Exception{
        
		User user = new User();
	    return create(user, oAuth2User.getAttributes());
	}
	
	public User registerUser(User user){
		
		user.setActive(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		Group userGroup = groupService.findByID(new Long(1));
		if (userGroup != null) {
			user.getGroups().add(userGroup);
		}
		
		User savedUser = userDAO.save(user);
		
		return userDAO.loadByUsername(savedUser.getUsername());
	}
	
}