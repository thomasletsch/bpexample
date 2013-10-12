package de.thomas_letsch.bootstrap.testdata;

import javax.ejb.Local;

@Local
public interface UserTestdata {

	String NAME = "userTestdata";
	String JNDI_NAME = "java:app/bpexample-bootstrap/UserTestdataBean";

	void insert();
}
