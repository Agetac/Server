package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.server.entities.TargetEntity;

public class TargetDAO {

	public static TargetDAO getInstance() {
		// TODO Auto-generated method stub
		return new TargetDAO();
	}

	public void delete(long targetId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(TargetEntity.class,
					targetId);
			TargetEntity obj = (TargetEntity) pm.getObjectById(idInstance);
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
