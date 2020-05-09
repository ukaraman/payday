package com.payday.login.dao;


import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.payday.login.model.Post;

@Repository
@Transactional
public class PostDAOImplement extends AbstractDAOImplement<Post, Long> implements PostDAO{

}
