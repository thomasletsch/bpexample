package de.thomas_letsch.bootstrap;

import javax.ejb.Local;

@Local
public interface ApplicationBootstrap {
  String NAME = "applicationBootstrap";
  String JNDI_NAME = "java:app/bpexample-bootstrap/ApplicationBootstrapBean";

  void init();
}