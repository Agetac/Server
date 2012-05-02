package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.server.entities.VehicleDemandEntity;
import org.modelmapper.ModelMapper;

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

	public void update(VehicleDemandDTO demand) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VehicleDemandEntity.class, demand.getId());
			VehicleDemandEntity obj = (VehicleDemandEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			
			ModelMapper modelMapper = new ModelMapper();
			VehicleDemandEntity newDemand = modelMapper.map(demand,
					VehicleDemandEntity.class);
			obj.update(newDemand);
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

}
