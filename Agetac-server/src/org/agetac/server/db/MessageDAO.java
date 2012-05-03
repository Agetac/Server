package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.server.entities.MessageEntity;
import org.modelmapper.ModelMapper;

public class MessageDAO {

	public static MessageDAO getInstance() {
		return new MessageDAO();
	}

	public void delete(long demandId) {
		PersistenceManager pm = InterventionDAO.getPM();

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
