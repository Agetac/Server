package org.agetac.server.db;

import org.agetac.common.dto.SourceDTO;

public interface SourceDAO {

	public abstract void delete(long sourceId);

	public abstract void update(SourceDTO source);

}