package de.thomas_letsch.weblogic.security;

import javax.ejb.Local;

@Local
public interface AuthenticatorService {
	String NAME = "authenticator";
	String JNDI_NAME = "java:app/bpexample-ejb/AuthenticatorServiceBean";

	boolean authenticate();
}
