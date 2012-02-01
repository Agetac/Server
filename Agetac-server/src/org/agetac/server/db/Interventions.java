package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.Intervention;
import org.agetac.util.PersistenceManagerUtils;

/**
 * Repr�sentation m�moire de toutes les interventions Aucun stockage persistant.
 * Cette base de donn�e doit �tre thread-safe.
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
	 * Ajoute une intervention dans le d�pos. Note : Ecrase l'ancienne
	 * intervention si la cl� est d�j� pr�sente.
	 * 
	 * @param intervention
	 *            L'intervention � ajouter.
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
	 * Retourne l'instance d'intervention associ� au uniqueID s'il est pr�sent,
	 * null si non trouv�.
	 * 
	 * @param uniqueID
	 *            L'uniqueID � trouver
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
	 * Assure que l'intervention associer a uniqueID n'est plus pr�sente dans le
	 * d�pos.
	 * 
	 * @param uniqueID
	 *            l'uniqueID a supprimer si pr�sent.
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
