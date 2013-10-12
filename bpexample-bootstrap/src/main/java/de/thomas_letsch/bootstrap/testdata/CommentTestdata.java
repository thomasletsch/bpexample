package de.thomas_letsch.bootstrap.testdata;

import javax.ejb.Local;

@Local
public interface CommentTestdata {

	String NAME = "commentTestdata";
	String JNDI_NAME = "java:app/bpexample-bootstrap/CommentTestdataBean";

	void insert();

}
