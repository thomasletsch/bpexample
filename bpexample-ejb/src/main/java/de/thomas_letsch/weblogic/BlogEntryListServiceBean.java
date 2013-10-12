package de.thomas_letsch.weblogic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import de.thomas_letsch.dao.BlogEntryDao;
import de.thomas_letsch.model.BlogEntry;

@Name(BlogEntryListService.NAME)
@JndiName(BlogEntryListService.JNDI_NAME)
@Stateful
@Scope(ScopeType.EVENT)
public class BlogEntryListServiceBean implements BlogEntryListService {

	private static final int MAX_RESULTS = 5;

	@Logger
	private Log log;

	@EJB
	private BlogEntryDao blogEntryDao;

	private List<BlogEntry> resultList;

	private int firstResult = 0;

	@Override
	public List<BlogEntry> getResultList() {
		log.info("load blog entries");
		if (resultList == null) {
			resultList = blogEntryDao.find(MAX_RESULTS, firstResult);
		}
		return resultList;
	}

	@Override
	public int getNextFirstResult() {
		return firstResult + MAX_RESULTS;
	}

	@Override
	public int getPreviousFirstResult() {
		return MAX_RESULTS >= firstResult ? 0 : firstResult - MAX_RESULTS;
	}

	@Override
	public Integer getFirstResult() {
		return firstResult;
	}

	@Override
	public void setFirstResult(Integer firstResult) {
		log.info("set first result " + firstResult);
		this.firstResult = firstResult;
		this.resultList = null;
	}

	@Override
	public boolean isPreviousExists() {
		return firstResult > 0;
	}

	@Override
	public boolean isNextExists() {
		return blogEntryDao.count() > MAX_RESULTS + firstResult;
	}

	@Remove
	@Destroy
	@Override
	public void remove(){}

}
