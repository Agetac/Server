package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.Intervention;
import org.agetac.util.PersistenceManagerUtils;

/**
 * Représentation mémoire de toutes les interventions Aucun stockage persistant.
 * Cette base de donnée doit être thread-safe.
 */

public class Interventions {

	/** Singleton */
	private static Interventions theInstance = new Interventions();

	/** Assure qu'aucun client ne puisse instancier cette classe */
	private Interventions() {
		// do nothing.
	}

	/**
	 * Retourne l'instance du singleton.
	 * 
	 * @return L'instance de Interventions.
	 */
	public static Interventions getInstance() {
		return theInstance;
	}

	/**
	 * Ajoute une intervention dans le dépos. Note : Ecrase l'ancienne
	 * intervention si la clé est déjà présente.
	 * 
	 * @param intervention
	 *            L'intervention à ajouter.
	 */
	public synchronized void addIntervention(Intervention intervention) {
		PersistenceManager pm = PersistenceManagerUtils.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(intervention);
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
	public synchronized Intervention getIntervention(String uid) {
		PersistenceManager pm = PersistenceManagerUtils.getPM();

		try {
			Object idInstance = pm.newObjectIdInstance(Intervention.class, uid);
			Intervention intervention = (Intervention) pm
					.getObjectById(idInstance);
			return intervention;
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
	public synchronized void deleteIntervention(String uniqueID) {
		PersistenceManager pm = PersistenceManagerUtils.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(Intervention.class,
					uniqueID);
			Intervention intervention = (Intervention) pm
					.getObjectById(idInstance);
			if (intervention == null)
				return;

			pm.deletePersistent(intervention);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
}
