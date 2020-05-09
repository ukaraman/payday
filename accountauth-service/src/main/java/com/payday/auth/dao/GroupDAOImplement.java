package com.payday.auth.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.payday.auth.model.Group;

@Repository
@Transactional
public class GroupDAOImplement extends AbstractDAOImplement<Group, Long> implements GroupDAO{

}
