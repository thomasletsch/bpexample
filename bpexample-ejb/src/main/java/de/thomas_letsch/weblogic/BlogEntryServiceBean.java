package de.thomas_letsch.weblogic;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import de.thomas_letsch.dao.BlogEntryDao;
import de.thomas_letsch.model.BlogEntry;
import de.thomas_letsch.model.User;

@Name(BlogEntryService.NAME)
@JndiName(BlogEntryService.JNDI_NAME)
@Stateful
@Scope(ScopeType.CONVERSATION)
public class BlogEntryServiceBean implements BlogEntryService {

	private static final long serialVersionUID = 1L;

	@Logger
	private Log log;

	@EJB
	private BlogEntryDao blogEntryDao;

	@In(required = false)
	private User currentUser;

	private Long id;

	private BlogEntry instance;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		log.info("set blogentry id " + id);
		this.id = id;
	}

	@Override
	public BlogEntry getInstance() {
		if (instance == null || (id != null && id != instance.getId())) {
			instance = blogEntryDao.find(id);
		}
		log.info("return blog entry #0", instance);
		return instance;
	}

	@Override
	public boolean newInstance() {
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setAuthor(currentUser);
		instance = blogEntry;
		id = null;
		return true;
	}

	@Override
	public boolean persistOrUpdate() {
		if (instance.getId() == null) {
			blogEntryDao.persist(instance);
		} else {
			instance = blogEntryDao.merge(instance);
		}
		id = instance.getId();
		return true;
	}

	@Override
	public boolean delete() {
		log.info("delete blog entry " + instance);
		blogEntryDao.remove(instance);

		instance = null;
		id = null;

		return true;
	}

	@Remove
	@Destroy
	@Override
	public void remove() {
	}
}
