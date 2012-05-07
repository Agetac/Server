package org.agetac.server.dao.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.TargetDTO;
import org.agetac.server.dao.TargetDAO;
import org.agetac.server.entities.TargetEntity;
import org.modelmapper.ModelMapper;

public class TargetDAOImpl implements TargetDAO {

	/* (non-Javadoc)
	 * @see org.agetac.server.db.TargetDAO#delete(long)
	 */
	@Override
	public void delete(long targetId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

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
	
	/* (non-Javadoc)
	 * @see org.agetac.server.db.TargetDAO#update(org.agetac.common.dto.TargetDTO)
	 */
	@Override
	public void update(TargetDTO target) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(TargetEntity.class,
					target.getId());
			TargetEntity obj = (TargetEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			TargetEntity newTarget = modelMapper.map(target,
					TargetEntity.class);
			obj.update(newTarget);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}
}
