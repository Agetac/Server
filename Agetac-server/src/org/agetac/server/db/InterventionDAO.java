package org.agetac.server.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VictimDTO;
import org.agetac.server.entities.ActionEntity;
import org.agetac.server.entities.InterventionEntity;
import org.agetac.server.entities.MessageEntity;
import org.agetac.server.entities.SourceEntity;
import org.agetac.server.entities.TargetEntity;
import org.agetac.server.entities.VehicleDemandEntity;
import org.agetac.server.entities.VehicleEntity;
import org.agetac.server.entities.VictimEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class InterventionDAO {
	
	public static InterventionDAO getInstance() {
		return new InterventionDAO();
	}

	public void addVehicleDemand(long interId, final VehicleDemandDTO vehicleDemandDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		VehicleDemandEntity vehicleDemand = modelMapper.map(vehicleDemandDTO, VehicleDemandEntity.class);
		vehicleDemandDTO.setId(vehicleDemand.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);
			inter.getVehicleDemands().add(vehicleDemand);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void addMessage(long interId, MessageDTO messageDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		MessageEntity message = modelMapper.map(messageDTO, MessageEntity.class);
		messageDTO.setId(message.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			inter.getMessages().add(message);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void addSource(long interId, SourceDTO sourceDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		SourceEntity source = modelMapper.map(sourceDTO, SourceEntity.class);
		sourceDTO.setId(source.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);
			inter.getSources().add(source);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void addTarget(long interId, TargetDTO targetDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		TargetEntity target = modelMapper.map(targetDTO, TargetEntity.class);
		targetDTO.setId(target.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);
			inter.getTargets().add(target);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

	}
	
	public void addAction(long interId, ActionDTO actionDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		ActionEntity action = modelMapper.map(actionDTO, ActionEntity.class);
		actionDTO.setId(action.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm.getObjectById(interKey);
			inter.getActions().add(action);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
	
	public void addVehicle(long interId, VehicleDTO vehicleDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		VehicleEntity vehicle = modelMapper.map(vehicleDTO, VehicleEntity.class);
		vehicleDTO.setId(vehicle.getId());

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);
			inter.getVehicles().add(vehicle);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

	}

	public Collection<VehicleDemandDTO> retrieveVehicleDemands(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<VehicleDemandDTO> vehicleDemands = new ArrayList<VehicleDemandDTO>();
			for (VehicleDemandEntity vde : inter.getVehicleDemands())
				vehicleDemands
						.add(modelMapper.map(vde, VehicleDemandDTO.class));

			return vehicleDemands;
		} finally {

			pm.close();
		}

	}

	public Collection<TargetDTO> retrieveTargets(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<TargetDTO> ret = new ArrayList<TargetDTO>();
			for (TargetEntity entity : inter.getTargets())
				ret.add(modelMapper.map(entity, TargetDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}

	public Collection<MessageDTO> retrieveMessages(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<MessageDTO> ret = new ArrayList<MessageDTO>();
			for (MessageEntity entity : inter.getMessages())
				ret.add(modelMapper.map(entity, MessageDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}

	public Collection<SourceDTO> retrieveSources(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<SourceDTO> ret = new ArrayList<SourceDTO>();
			for (SourceEntity entity : inter.getSources())
				ret.add(modelMapper.map(entity, SourceDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}

	public Collection<ActionDTO> retrieveActions(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<ActionDTO> ret = new ArrayList<ActionDTO>();
			for (ActionEntity entity : inter.getActions())
				ret.add(modelMapper.map(entity, ActionDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}
	
	public Collection<VehicleDTO> retrieveVehicles(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<VehicleDTO> ret = new ArrayList<VehicleDTO>();
			for (SourceEntity entity : inter.getSources())
				ret.add(modelMapper.map(entity, VehicleDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}
	
	public void add(long interId, VictimDTO victimDTO) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		ModelMapper modelMapper = new ModelMapper();
		VictimEntity victim = modelMapper.map(victimDTO, VictimEntity.class);

		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);
			inter.getVictims().add(victim);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public Collection<VictimDTO> retrieveVictims(long interId) {
		PersistenceManager pm = getPM();
		try {

			Object interKey = pm.newObjectIdInstance(InterventionEntity.class,
					interId);

			InterventionEntity inter = (InterventionEntity) pm
					.getObjectById(interKey);

			ModelMapper modelMapper = new ModelMapper();
			List<VictimDTO> ret = new ArrayList<VictimDTO>();
			for (VictimEntity entity : inter.getVictims())
				ret.add(modelMapper.map(entity, VictimDTO.class));

			return ret;
		} finally {

			pm.close();
		}
	}

	private static PersistenceManagerFactory pmfInstance;

	public static PersistenceManager getPM() {
		return getPMF().getPersistenceManager();
	}

	private static PersistenceManagerFactory getPMF() {

		if (pmfInstance == null)
			pmfInstance = JDOHelper
					.getPersistenceManagerFactory("jdo.properties");

		return pmfInstance;
	}

	public void add(InterventionEntity entity) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			pm.makePersistent(entity);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

	}
	
	public void update(InterventionDTO intervention) {
		PersistenceManager pm = InterventionDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(InterventionEntity.class,
					intervention.getId());
			InterventionEntity obj = (InterventionEntity) pm.getObjectById(idInstance);
			if (obj == null) return;
			ModelMapper modelMapper = new ModelMapper();
			InterventionEntity newInter = modelMapper.map(intervention, InterventionEntity.class);
			obj.update(newInter);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		
	}

	public Collection<InterventionDTO> retrieveAll() {
		PersistenceManager pm = getPM();
		Query query = pm.newQuery(InterventionEntity.class);

		@SuppressWarnings("unchecked")
		Collection<InterventionEntity> results = (Collection<InterventionEntity>) query
				.execute();

		ModelMapper modelMapper = new ModelMapper();
		List<InterventionDTO> ret = new ArrayList<InterventionDTO>();
		for (InterventionEntity entity : results) {
			InterventionDTO dto = modelMapper
					.map(entity, InterventionDTO.class);
			ret.add(dto);

			for (VictimEntity victim : entity.getVictims()) {
				dto.getVictims().add(modelMapper.map(victim, VictimDTO.class));
			}

			for (SourceEntity source : entity.getSources()) {
				dto.getSources().add(modelMapper.map(source, SourceDTO.class));
			}

			for (TargetEntity target : entity.getTargets()) {
				dto.getTargets().add(modelMapper.map(target, TargetDTO.class));
			}

			for (MessageEntity message : entity.getMessages()) {
				dto.getMessages().add(
						modelMapper.map(message, MessageDTO.class));
			}
		}

		return ret;
	}

	public InterventionDTO retrieveOne(long id) {
		PersistenceManager pm = getPM();
		Object interKey = pm.newObjectIdInstance(InterventionEntity.class, id);

		InterventionEntity entity = (InterventionEntity) pm
				.getObjectById(interKey);

		ModelMapper modelMapper = new ModelMapper();

		InterventionDTO dto = modelMapper.map(entity, InterventionDTO.class);

		for (VictimEntity victim : entity.getVictims()) {
			dto.getVictims().add(modelMapper.map(victim, VictimDTO.class));
		}

		for (SourceEntity source : entity.getSources()) {
			dto.getSources().add(modelMapper.map(source, SourceDTO.class));
		}

		for (TargetEntity target : entity.getTargets()) {
			dto.getTargets().add(modelMapper.map(target, TargetDTO.class));
		}

		for (MessageEntity message : entity.getMessages()) {
			dto.getMessages().add(modelMapper.map(message, MessageDTO.class));
		}
		
		for (VehicleDemandEntity vde : entity.getVehicleDemands())
			dto.getDemands().add(modelMapper.map(vde, VehicleDemandDTO.class));

		return dto;
	}
}
