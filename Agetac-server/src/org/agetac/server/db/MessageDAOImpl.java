package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.server.entities.MessageEntity;

public class MessageDAOImpl implements MessageDAO {

	public static MessageDAO getInstance() {
		return new MessageDAOImpl();
	}

	/* (non-Javadoc)
	 * @see org.agetac.server.db.MessageDAO#delete(long)
	 */
	@Override
	public void delete(long demandId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(MessageEntity.class, demandId);
			MessageEntity obj = (MessageEntity) pm.getObjectById(idInstance);
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
