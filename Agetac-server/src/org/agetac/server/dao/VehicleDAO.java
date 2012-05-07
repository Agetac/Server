package org.agetac.server.dao;

import org.agetac.common.dto.VehicleDTO;

public interface VehicleDAO {

	public abstract void delete(long vehicleId);

	public abstract void update(VehicleDTO vehicle);

}