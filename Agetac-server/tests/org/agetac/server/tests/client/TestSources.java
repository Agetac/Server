package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.SourceDTO.SourceType;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSources {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addSourceTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une source
		SourceDTO s = new SourceDTO("testSource",SourceType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la source
		client.addSource(inter.getId(), s);
		
		//Récupération des sources de l'intervention
		List<SourceDTO> sources = new ArrayList<SourceDTO>(client.getSources(inter.getId()));
		
		assertTrue(inter.getSources().size() < sources.size());
		assertTrue(sources.get(0).getName().equals("testSource"));
		
	}
	
	@Test
	public void retreiveSourceTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une source
		SourceDTO s = new SourceDTO("testSource",SourceType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la source
		client.addSource(inter.getId(), s);
		
		//Récupération des sources de l'intervention
		List<SourceDTO> sources = new ArrayList<SourceDTO>(client.getSources(inter.getId()));
		
		assertTrue(sources.get(0).getName().equals("testSource"));
		
	}

	
	@Test
	public void updateSourceTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une source
		SourceDTO s = new SourceDTO("testSource",SourceType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la source
		s = client.addSource(inter.getId(), s);
		
		s.setName("testUpdate");
		client.updateSource(s);
		
		//Récupération des sources de l'intervention
		List<SourceDTO> sources = new ArrayList<SourceDTO>(client.getSources(inter.getId()));
		System.out.println(sources.get(0).getId());
		assertTrue(sources.get(0).getName().equals("testUpdate"));
		
	}
}
