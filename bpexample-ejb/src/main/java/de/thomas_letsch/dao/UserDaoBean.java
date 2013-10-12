package de.thomas_letsch.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import de.thomas_letsch.model.User;

@Stateless
@Name(UserDao.NAME)
@JndiName(UserDao.JNDI_NAME)
public class UserDaoBean extends AbstractDaoBean<User> implements UserDao {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@Override
	public User findByUsername(final String username) {

		log.info("find user with username #0", username);

		Query query = entityManager
				.createQuery("select user from User user where user.username = :username");
		query.setParameter("username", username);
		return getSingleResult(query);

	}
}
