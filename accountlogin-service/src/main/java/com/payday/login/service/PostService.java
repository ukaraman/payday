package com.payday.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payday.login.dao.PostDAO;
import com.payday.login.model.Post;

@Service
public class PostService extends AbstractServiceImplement<Post, Long> {
	
	private PostDAO postDAO;
	
	@Autowired
	public PostService(PostDAO postDAO){
		this.postDAO = postDAO;
	}

}
