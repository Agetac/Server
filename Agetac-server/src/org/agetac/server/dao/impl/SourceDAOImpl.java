package org.agetac.server.dao.impl;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.SourceDTO;
import org.agetac.server.db.SourceDAO;
import org.agetac.server.entities.SourceEntity;
import org.modelmapper.ModelMapper;

public class SourceDAOImpl implements SourceDAO {

	/* (non-Javadoc)
	 * @see org.agetac.server.db.SourceDAO#delete(long)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see org.agetac.server.db.SourceDAO#update(org.agetac.common.dto.SourceDTO)
	 */
	@Override
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
