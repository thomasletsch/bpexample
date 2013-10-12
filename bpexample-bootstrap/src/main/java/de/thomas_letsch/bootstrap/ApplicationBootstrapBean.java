package de.thomas_letsch.bootstrap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.seam.annotations.JndiName;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.log.Log;

import de.thomas_letsch.bootstrap.testdata.BlogEntryTestdata;
import de.thomas_letsch.bootstrap.testdata.CommentTestdata;
import de.thomas_letsch.bootstrap.testdata.UserTestdata;

@Stateless
@Name(ApplicationBootstrap.NAME)
@JndiName(ApplicationBootstrap.JNDI_NAME)
public class ApplicationBootstrapBean implements ApplicationBootstrap {

	@Logger
	private Log log;

	@EJB
	private UserTestdata userTestdata;

	@EJB
	private BlogEntryTestdata blogEntryTestdata;

	@EJB
	private CommentTestdata commentTestdata;

	@Observer("org.jboss.seam.postInitialization")
	public final void init() {
		log.debug("received org.jboss.seam.postInitialization event");

		userTestdata.insert();
		blogEntryTestdata.insert();
		commentTestdata.insert();

		log.info("insert testdata complete");
	}
}
