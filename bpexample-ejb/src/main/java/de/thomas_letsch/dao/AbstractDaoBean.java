package de.thomas_letsch.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;

import de.thomas_letsch.model.AbstractEntity;

public abstract class AbstractDaoBean<E extends AbstractEntity> implements
		Dao<E> {

	protected final Class<E> entityClass;

	@In
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	protected AbstractDaoBean() {
		super();
		entityClass = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected E getSingleResult(final Query query) {
		return this.<E> getTypedSingleResult(query);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getTypedSingleResult(final Query query) {
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void persist(final E instance) {
		entityManager.persist(instance);
	}

	@Override
	public E find(final long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public void remove(final E instance) {
		entityManager.remove(instance);
	}

	@Override
	public E merge(final E instance) {
		E merge = entityManager.merge(instance);
		entityManager.flush();
		return merge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		final StringBuilder builder = new StringBuilder();
		builder.append("select e from ");
		builder.append(entityClass.getSimpleName());
		builder.append(" e");

		return entityManager.createQuery(
				builder.toString())
				.getResultList();
	}
}
