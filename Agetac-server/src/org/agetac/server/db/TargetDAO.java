package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.TargetDTO;
import org.agetac.server.entities.ActionEntity;
import org.agetac.server.entities.TargetEntity;
import org.modelmapper.ModelMapper;

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
	
	public void update(TargetDTO target) {
		PersistenceManager pm = InterventionDAO.getPM();

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
