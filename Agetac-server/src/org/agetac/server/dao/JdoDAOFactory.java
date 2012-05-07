package org.agetac.server.dao;

import org.agetac.server.dao.impl.ActionDAOImpl;
import org.agetac.server.dao.impl.InterventionDAOImpl;
import org.agetac.server.dao.impl.MessageDAOImpl;
import org.agetac.server.dao.impl.SourceDAOImpl;
import org.agetac.server.dao.impl.TargetDAOImpl;
import org.agetac.server.dao.impl.VehicleDAOImpl;
import org.agetac.server.dao.impl.VehicleDemandDAOImpl;
import org.agetac.server.dao.impl.VictimDAOImpl;

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
