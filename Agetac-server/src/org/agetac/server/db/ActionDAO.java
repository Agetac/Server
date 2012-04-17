package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.ActionDTO;
import org.agetac.server.entities.ActionEntity;
import org.agetac.server.entities.VehicleDemandEntity;
import org.modelmapper.ModelMapper;

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
	
	public void update(ActionDTO action) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(ActionEntity.class, action.getId());
			ActionEntity obj = (ActionEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			ActionEntity newAction = modelMapper.map(action,
					ActionEntity.class);
			obj.update(newAction);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}

}
