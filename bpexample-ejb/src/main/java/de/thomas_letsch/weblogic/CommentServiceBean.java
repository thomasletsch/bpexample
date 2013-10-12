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

import de.thomas_letsch.dao.CommentDao;
import de.thomas_letsch.model.Comment;
import de.thomas_letsch.model.User;

@Name(CommentService.NAME)
@JndiName(CommentService.JNDI_NAME)
@Stateful
@Scope(ScopeType.EVENT)
public class CommentServiceBean implements CommentService {

	@Logger
	private Log log;

	@In(required = false)
	private User currentUser;

	@In(create = true)
	private BlogEntryService blogEntryService;

	@EJB
	private CommentDao commentDao;

	private Comment instance;

	@Override
	public Comment getInstance() {
		if (instance == null) {
			log.info("create new comment instance");
			instance = new Comment();
			instance.setAuthor(currentUser);
			instance.setBlogEntry(blogEntryService.getInstance());
		}

		log.info("return comment instance " + instance);

		return instance;

	}

	@Override
	public void save() {
		log.info("persist comment " + instance);
		instance.getBlogEntry().addComment(instance);
		commentDao.persist(instance);

		instance = null;
	}

	@Remove
	@Destroy
	@Override
	public void remove(){}

}
