package de.thomas_letsch.dao;

import java.util.List;

import javax.ejb.Local;

import de.thomas_letsch.model.BlogEntry;
import de.thomas_letsch.model.Comment;

@Local
public interface CommentDao extends Dao<Comment> {

	String NAME = "commentDao";
	String JNDI_NAME = "java:app/bpexample-ejb/CommentDaoBean";

	List<Comment> findComments(BlogEntry blogEntry);

}
