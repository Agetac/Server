package org.agetac.server.dao.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.ActionDTO;
import org.agetac.server.dao.ActionDAO;
import org.agetac.server.entities.ActionEntity;
import org.modelmapper.ModelMapper;

public class ActionDAOImpl implements ActionDAO {

	/* (non-Javadoc)
	 * @see org.agetac.server.db.ActionDAO#delete(long)
	 */
	@Override
	public void delete(long actionId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

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
	
	/* (non-Javadoc)
	 * @see org.agetac.server.db.ActionDAO#update(org.agetac.common.dto.ActionDTO)
	 */
	@Override
	public void update(ActionDTO action) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

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
