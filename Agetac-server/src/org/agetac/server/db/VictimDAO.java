package org.agetac.server.db;

import org.agetac.common.dto.VictimDTO;

public interface VictimDAO {

	public abstract void delete(long victimId);

	public abstract void update(VictimDTO victim);

}