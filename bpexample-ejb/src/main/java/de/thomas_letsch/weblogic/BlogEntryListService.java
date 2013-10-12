package de.thomas_letsch.weblogic;

import java.util.List;

import javax.ejb.Local;

import de.thomas_letsch.model.BlogEntry;

@Local
public interface BlogEntryListService {

	String NAME = "blogEntryListService";
	String JNDI_NAME = "java:app/bpexample-ejb/BlogEntryListServiceBean";

	List<BlogEntry> getResultList();

	int getNextFirstResult();

	int getPreviousFirstResult();

	Integer getFirstResult();

	void setFirstResult(Integer firstResult);

	boolean isPreviousExists();

	boolean isNextExists();

	void remove();

}
