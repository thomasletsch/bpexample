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
import de.thomas_letsch.model.Comment;

@Stateless
@Name(CommentDao.NAME)
@JndiName(CommentDao.JNDI_NAME)
public class CommentDaoBean extends AbstractDaoBean<Comment> implements
		CommentDao {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findComments(final BlogEntry blogEntry) {
		log.info("find comment for blog entry #0", blogEntry);

		Query query = entityManager
				.createQuery("select comment from Comment comment where comment.blogEntry.id = :blogEntryId order by comment.created asc");

		query.setParameter("blogEntryId", blogEntry.getId());

		return query.getResultList();
	}
}
