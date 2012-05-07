package org.agetac.server.dao;

import org.agetac.common.dto.ActionDTO;

public interface ActionDAO {

	public abstract void delete(long actionId);

	public abstract void update(ActionDTO action);

}