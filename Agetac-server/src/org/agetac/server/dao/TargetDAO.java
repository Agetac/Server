package org.agetac.server.dao;

import org.agetac.common.dto.TargetDTO;

public interface TargetDAO {

	public abstract void delete(long targetId);

	public abstract void update(TargetDTO target);

}