package org.agetac.server.db;

import java.lang.reflect.Field;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.annotations.PrimaryKey;

import org.agetac.common.model.impl.Intervention;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class SimpleDAO {

	private static PersistenceManagerFactory pmfInstance;

	private static PersistenceManagerFactory getPMF() {

		if (pmfInstance == null)
			pmfInstance = JDOHelper
					.getPersistenceManagerFactory("jdo.properties");

		return pmfInstance;
	}

	private static SimpleDAO daoInstance;

	public static SimpleDAO getInstance() {
		if (daoInstance == null)
			// TODO Use factories?
			daoInstance = new SimpleDAO();

		return daoInstance;
	}

	private static PersistenceManager getPM() {
		return getPMF().getPersistenceManager();
	}

	public synchronized <T> void add(T objTransient) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(objTransient);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public synchronized <T> void update(T objTransient) {

		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			T obj;
			Object keyValue = findPrimaryKey(objTransient);
			// we do an update, we expect the transient object to have a primary
			// key set.
			assert keyValue != null;

			Object idInstance = pm.newObjectIdInstance(objTransient.getClass(),
					keyValue);

			T objPersistent = (T) pm.getObjectById(idInstance);
			// we do an update, we expect the object to exist in the store.
			assert objPersistent != null;

			// TODO Here we need to merge fields.. great quoi.
			// https://issues.apache.org/jira/browse/JDO-686
			// http://apache-jdo.2283334.n4.nabble.com/Updating-datastore-with-transient-instance-td3213307.html

			Mapper mapper = new DozerBeanMapper();
			mapper.map(objTransient, objPersistent);

			obj = objPersistent;

			pm.makePersistent(obj);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public synchronized <T> T getOne(Class<T> cls, String uid) {
		PersistenceManager pm = getPM();

		try {
			Object idInstance = pm.newObjectIdInstance(cls, uid);

			T obj = (T) pm.getObjectById(idInstance);
			return obj;
		} catch (Exception ex) {
			return null;
		}

	}

	public synchronized <T> void delete(Class<T> cls, String uniqueID) {
		PersistenceManager pm = SimpleDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(Intervention.class,
					uniqueID);
			@SuppressWarnings("unchecked")
			T obj = (T) pm.getObjectById(idInstance);
			if (obj == null)
				return;

			pm.deletePersistent(obj);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public synchronized <T> List<T> getMany(Class<T> cls) {
		PersistenceManager pm = SimpleDAO.getPM();
		Query query = pm.newQuery(cls);

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) query.execute();
		return results;
	}

	public <T> List<T> getMany(Class<T> cls, String q) {
		PersistenceManager pm = SimpleDAO.getPM();
		Query query = pm.newQuery(cls, q);

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) query.execute();
		return results;
	}

	private static <T> Object findPrimaryKey(T tInstance) {
		return findPrimaryKeyRec(tInstance, tInstance.getClass());
	}

	private static <T> Object findPrimaryKeyRec(T tInstance, Class cls) {
		// Recursively search for primary key.
		if (tInstance == null) {
			return null;
		}

		// Search fields.
		for (Field l : cls.getDeclaredFields()) {
			if (l.getAnnotation(PrimaryKey.class) != null) {
				l.setAccessible(true);
				try {
					return l.get(tInstance);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return null;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		// Search superClass fields.
		return findPrimaryKeyRec(tInstance, cls.getSuperclass());
	}

}