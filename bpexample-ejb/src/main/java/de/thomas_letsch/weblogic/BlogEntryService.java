package de.thomas_letsch.weblogic;

import javax.ejb.Local;

import de.thomas_letsch.model.BlogEntry;

@Local
public interface BlogEntryService {

	String NAME = "blogEntryService";
	String JNDI_NAME = "java:app/bpexample-ejb/BlogEntryServiceBean";

	BlogEntry getInstance();

	boolean newInstance();

	boolean persistOrUpdate();

	boolean delete();

	Long getId();

	void setId(Long id);

	void remove();
}
