package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.VictimDTO;
import org.agetac.server.entities.VictimEntity;
import org.modelmapper.ModelMapper;

public class VictimDAOImpl {

	public static VictimDAOImpl getInstance() {
		// TODO Auto-generated method stub
		return new VictimDAOImpl();
	}

	public void delete(long victimId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

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

	public void update(VictimDTO victim) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VictimEntity.class,
					victim.getId());
			VictimEntity obj = (VictimEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			VictimEntity newVictim = modelMapper.map(victim,
					VictimEntity.class);
			obj.update(newVictim);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

}
