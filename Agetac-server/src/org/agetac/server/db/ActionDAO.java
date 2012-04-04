package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.server.entities.ActionEntity;

public class ActionDAO {

	public static ActionDAO getInstance() {
		// TODO Auto-generated method stub
		return new ActionDAO();
	}

	public void delete(long actionId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(ActionEntity.class, actionId);
			ActionEntity obj = (ActionEntity) pm.getObjectById(idInstance);
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
