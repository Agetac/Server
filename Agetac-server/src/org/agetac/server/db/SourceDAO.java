package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.server.entities.SourceEntity;

public class SourceDAO {

	public static SourceDAO getInstance() {
		// TODO Auto-generated method stub
		return new SourceDAO();
	}

	public void delete(long sourceId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(SourceEntity.class,
					sourceId);
			SourceEntity obj = (SourceEntity) pm.getObjectById(idInstance);
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
