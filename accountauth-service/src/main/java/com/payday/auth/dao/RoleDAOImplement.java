package com.payday.auth.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.payday.auth.model.Role;

@Repository
@Transactional
public class RoleDAOImplement extends AbstractDAOImplement<Role, Long> implements RoleDAO{

}
