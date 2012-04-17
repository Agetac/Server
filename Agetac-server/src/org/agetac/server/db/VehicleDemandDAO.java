package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.server.entities.VehicleDemandEntity;

public class VehicleDemandDAO {

	public static VehicleDemandDAO getInstance() {
		return new VehicleDemandDAO();
	}

	public void delete(long demandId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VehicleDemandEntity.class, demandId);
			VehicleDemandEntity obj = (VehicleDemandEntity) pm.getObjectById(idInstance);
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

	public void update(VehicleDemandDTO demand, long interId) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VehicleDemandEntity.class, demand.getId());
			VehicleDemandEntity obj = (VehicleDemandEntity) pm.getObjectById(idInstance);
			
			// si l'objet n'existe pas, on l'ajoute
			if (obj == null) {
				InterventionDAO dao = new InterventionDAO();
				dao.addVehicleDemand(interId, demand);
			
			// sinon on le met à jour
			} else {
				pm.makePersistent(obj);
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

}
