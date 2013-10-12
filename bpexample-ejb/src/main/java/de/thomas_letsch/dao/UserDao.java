package de.thomas_letsch.dao;

import javax.ejb.Local;

import de.thomas_letsch.model.User;

@Local
public interface UserDao extends Dao<User> {

	String NAME = "userDao";
	String JNDI_NAME = "java:app/bpexample-ejb/UserDaoBean";

	User findByUsername(String username);

}
