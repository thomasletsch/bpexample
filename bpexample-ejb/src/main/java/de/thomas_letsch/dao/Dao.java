package de.thomas_letsch.dao;

import java.util.List;

import de.thomas_letsch.model.AbstractEntity;

public interface Dao<E extends AbstractEntity> {

	void persist(E instance);

	E find(long id);

	void remove(E instance);

	E merge(E instance);

	List<E> findAll();

}
