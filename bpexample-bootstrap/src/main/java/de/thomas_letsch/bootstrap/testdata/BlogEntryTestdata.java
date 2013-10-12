package de.thomas_letsch.bootstrap.testdata;

import javax.ejb.Local;

@Local
public interface BlogEntryTestdata {

	String NAME = "blogEntryTestdata";
	String JNDI_NAME = "java:app/bpexample-bootstrap/BlogEntryTestdataBean";

	void insert();

}
