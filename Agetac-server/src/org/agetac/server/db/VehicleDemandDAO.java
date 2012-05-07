package org.agetac.server.db;

import org.agetac.common.dto.VehicleDemandDTO;

public interface VehicleDemandDAO {

	public abstract void delete(long demandId);

	public abstract void update(VehicleDemandDTO demand);

}