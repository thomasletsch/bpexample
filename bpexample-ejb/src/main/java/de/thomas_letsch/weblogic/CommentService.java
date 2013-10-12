package de.thomas_letsch.weblogic;

import javax.ejb.Local;

import de.thomas_letsch.model.Comment;

@Local
public interface CommentService {

	String NAME = "commentService";
	String JNDI_NAME = "java:app/bpexample-ejb/CommentServiceBean";

	Comment getInstance();

	void save();

	void remove();

}
