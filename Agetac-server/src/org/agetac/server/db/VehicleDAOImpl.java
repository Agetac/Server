package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.VehicleDTO;
import org.agetac.server.entities.VehicleEntity;
import org.modelmapper.ModelMapper;

public class VehicleDAOImpl implements VehicleDAO {

	public static VehicleDAO getInstance() {
		return new VehicleDAOImpl();
	}

	/* (non-Javadoc)
	 * @see org.agetac.server.db.VehicleDAO#delete(long)
	 */
	@Override
	public void delete(long vehicleId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VehicleEntity.class, vehicleId);
			VehicleEntity obj = (VehicleEntity) pm.getObjectById(idInstance);
			if (obj == null) return;

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
	 * @see org.agetac.server.db.VehicleDAO#update(org.agetac.common.dto.VehicleDTO)
	 */
	@Override
	public void update(VehicleDTO vehicle) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(VehicleEntity.class, vehicle.getId());
			VehicleEntity obj = (VehicleEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			VehicleEntity newVehicle = modelMapper.map(vehicle,
					VehicleEntity.class);
			obj.update(newVehicle);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

}
