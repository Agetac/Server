package org.agetac.server.dao;

import org.agetac.server.dao.impl.JdoDAOFactory;

public abstract class DAOFactory {

	public static DAOFactory getDAOFactory() {
		return new JdoDAOFactory();
	}

	public abstract ActionDAO getActionDAO();

	public abstract InterventionDAO getInterventionDAO();

	public abstract MessageDAO getMessageDAO();

	public abstract SourceDAO getSourceDAO();

	public abstract TargetDAO getTargetDAO();

	public abstract VehicleDAO getVehicleDAO();

	public abstract VehicleDemandDAO getVehicleDemandDAO();

	public abstract VictimDAO getVictimDAO();
}
