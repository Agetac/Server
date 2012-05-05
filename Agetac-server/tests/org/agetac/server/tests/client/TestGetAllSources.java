package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.SourceDTO.SourceType;
import org.agetac.server.Server;
import org.junit.Test;

public class TestGetAllSources {

	@Test
	public void test() throws Exception {
		// Mise en place du serveur avec une intervention
		Server.startServer(8989);
		AgetacClient c = new AgetacClient("localhost", 8989);
		c.createIntervention();
		
		SourceDTO s = new SourceDTO("src",SourceType.FIRE, new PositionDTO(42,42));
		
		c.addSource(0, s);
		
		Collection<SourceDTO> col = c.getSources(0);
		ArrayList<SourceDTO> li = new ArrayList<SourceDTO>(col);
		li.get(0).getId();
	}

}
