package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.server.entities.VictimEntity;

public class VictimDAO {

	public static VictimDAO getInstance() {
		// TODO Auto-generated method stub
		return new VictimDAO();
	}

	public void delete(long victimId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VictimEntity.class,
					victimId);
			VictimEntity obj = (VictimEntity) pm.getObjectById(idInstance);
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

}
