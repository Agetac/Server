package org.agetac.server.dao.impl;

import org.agetac.server.dao.ActionDAO;
import org.agetac.server.dao.DAOFactory;
import org.agetac.server.dao.InterventionDAO;
import org.agetac.server.dao.MessageDAO;
import org.agetac.server.dao.SourceDAO;
import org.agetac.server.dao.TargetDAO;
import org.agetac.server.dao.VehicleDAO;
import org.agetac.server.dao.VehicleDemandDAO;
import org.agetac.server.dao.VictimDAO;

public class JdoDAOFactory extends DAOFactory {

	@Override
	public ActionDAO getActionDAO() {
		return new ActionDAOImpl();
	}

	@Override
	public InterventionDAO getInterventionDAO() {
		return new InterventionDAOImpl();
	}

	@Override
	public MessageDAO getMessageDAO() {
		return new MessageDAOImpl();
	}

	@Override
	public SourceDAO getSourceDAO() {
		return new SourceDAOImpl();
	}

	@Override
	public TargetDAO getTargetDAO() {
		return new TargetDAOImpl();
	}

	@Override
	public VehicleDAO getVehicleDAO() {
		return new VehicleDAOImpl();
	}

	@Override
	public VehicleDemandDAO getVehicleDemandDAO() {
		return new VehicleDemandDAOImpl();
	}

	@Override
	public VictimDAO getVictimDAO() {
		return new VictimDAOImpl();
	}

}
