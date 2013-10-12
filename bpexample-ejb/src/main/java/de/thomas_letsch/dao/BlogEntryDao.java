package de.thomas_letsch.dao;

import java.util.List;

import javax.ejb.Local;

import de.thomas_letsch.model.BlogEntry;

@Local
public interface BlogEntryDao extends Dao<BlogEntry> {

	String NAME = "blogEntryDao";
	String JNDI_NAME = "java:app/bpexample-ejb/BlogEntryDaoBean";

	List<BlogEntry> find(int maxresults, int firstresult);

	Long count();

}
