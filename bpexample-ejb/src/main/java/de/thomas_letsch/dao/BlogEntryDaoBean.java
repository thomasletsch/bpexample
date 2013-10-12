package de.thomas_letsch.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import de.thomas_letsch.model.BlogEntry;

@Name(BlogEntryDao.NAME)
@Stateless
@JndiName(BlogEntryDao.JNDI_NAME)
public class BlogEntryDaoBean extends AbstractDaoBean<BlogEntry> implements
		BlogEntryDao {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogEntry> find(int maxresults, int firstresult) {
		log.info("find blog entry, max results #0 next result #1", maxresults,
				firstresult);

		Query query = entityManager
				.createQuery("select blogEntry from BlogEntry blogEntry order by blogEntry.created desc");
		return query.setFirstResult(firstresult).setMaxResults(maxresults)
				.getResultList();
	}

	@Override
	public Long count() {
		Query query = entityManager
				.createQuery("select count(blogEntry.id) from BlogEntry blogEntry");
		return this.<Long> getTypedSingleResult(query);
	}
}
