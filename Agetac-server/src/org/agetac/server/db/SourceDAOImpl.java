package org.agetac.server.db;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.SourceDTO;
import org.agetac.server.entities.SourceEntity;
import org.modelmapper.ModelMapper;

public class SourceDAOImpl {

	public static SourceDAOImpl getInstance() {
		// TODO Auto-generated method stub
		return new SourceDAOImpl();
	}

	public void delete(long sourceId) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(SourceEntity.class,
					sourceId);
			SourceEntity obj = (SourceEntity) pm.getObjectById(idInstance);
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

	public void update(SourceDTO source) {
		PersistenceManager pm = InterventionDAOImpl.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(SourceEntity.class,
					source.getId());
			SourceEntity obj = (SourceEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			SourceEntity newSource = modelMapper.map(source,
					SourceEntity.class);
			obj.update(newSource);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}
}
