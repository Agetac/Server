package org.agetac.server.db;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.agetac.common.model.impl.Intervention;

public class PersistenceManagerProxy {

	public static PersistenceManager getPM() {
		PersistenceManagerFactory pmf = JDOHelper
				.getPersistenceManagerFactory("jdo.properties");
		return pmf.getPersistenceManager();
	}

	private static PersistenceManagerProxy instance = new PersistenceManagerProxy();

	public static PersistenceManagerProxy getInstance() {
		return instance;
	}

	/**
	 * Ajoute une intervention dans le dépos. Note : Ecrase l'ancienne
	 * intervention si la clé est déjà présente.
	 * 
	 * @param intervention
	 *            L'intervention à ajouter.
	 */
	public synchronized <T> void put(T obj) {
		PersistenceManager pm = PersistenceManagerProxy.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(obj);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Retourne l'instance d'intervention associé au uniqueID s'il est présent,
	 * null si non trouvé.
	 * 
	 * @param uniqueID
	 *            L'uniqueID à trouver
	 * @return L'instance de l'intervention ou null.
	 */
	public synchronized <T> T getSingle(Class<T> cls, String uid) {
		PersistenceManager pm = PersistenceManagerProxy.getPM();

		try {
			Object idInstance = pm.newObjectIdInstance(cls, uid);

			T obj = (T) pm.getObjectById(idInstance);
			return obj;
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Assure que l'intervention associer a uniqueID n'est plus présente dans le
	 * dépos.
	 * 
	 * @param uniqueID
	 *            l'uniqueID a supprimer si présent.
	 */
	public synchronized <T> void delete(Class<T> cls, String uniqueID) {
		PersistenceManager pm = PersistenceManagerProxy.getPM();

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

	/**
	 * Retourne les valeurs de la collection a cet instant.
	 * 
	 * @return Collection d'instance d'intervention
	 */
	public synchronized <T> List<T> getList(Class<T> cls) {
		PersistenceManager pm = PersistenceManagerProxy.getPM();
		Query query = pm.newQuery(cls);

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) query.execute();
		return results;
	}

	public <T> List<T> getList(Class<T> cls, String q) {
		PersistenceManager pm = PersistenceManagerProxy.getPM();
		Query query = pm.newQuery(cls, q);

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) query.execute();
		return results;
	}
}