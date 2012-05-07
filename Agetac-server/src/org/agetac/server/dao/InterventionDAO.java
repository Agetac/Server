package org.agetac.server.dao;

import java.util.Collection;

import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VictimDTO;
import org.agetac.server.entities.InterventionEntity;

public interface InterventionDAO {

	public abstract VehicleDemandDTO addVehicleDemand(long interId,
			final VehicleDemandDTO vehicleDemandDTO);

	public abstract MessageDTO addMessage(long interId, MessageDTO messageDTO);

	public abstract SourceDTO addSource(long interId, SourceDTO sourceDTO);

	public abstract TargetDTO addTarget(long interId, TargetDTO targetDTO);

	public abstract ActionDTO addAction(long interId, ActionDTO actionDTO);

	public abstract VehicleDTO addVehicle(long interId, VehicleDTO vehicleDTO);

	public abstract Collection<VehicleDemandDTO> retrieveVehicleDemands(
			long interId);

	public abstract Collection<TargetDTO> retrieveTargets(long interId);

	public abstract Collection<MessageDTO> retrieveMessages(long interId);

	public abstract Collection<SourceDTO> retrieveSources(long interId);

	public abstract Collection<ActionDTO> retrieveActions(long interId);

	public abstract Collection<VehicleDTO> retrieveVehicles(long interId);

	public abstract void add(long interId, VictimDTO victimDTO);

	public abstract Collection<VictimDTO> retrieveVictims(long interId);

	public abstract void add(InterventionEntity entity);

	public abstract void update(InterventionDTO intervention);

	public abstract Collection<InterventionDTO> retrieveAll();

	public abstract InterventionDTO retrieveOne(long id);

}