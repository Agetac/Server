package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.ActionDTO.ActionType;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestIntervention {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void createInterventionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		Collection<InterventionDTO> list_inter_1 = client.getInterventions();
		
		//Création d'une intervention
		InterventionDTO inter = client.createIntervention();
		
		//Récupération des actions de l'intervention
		Collection<InterventionDTO> list_inter_2 = client.getInterventions();
		
		//Il doit y avoir une ressource
		assertTrue(list_inter_1.size() < list_inter_2.size());
		assertTrue(inter != null);
		
	}
	
	@Test
	public void retreiveAllInterventionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		Collection<InterventionDTO> list_inter_1 = client.getInterventions();
		
		//Création d'une intervention
		InterventionDTO inter = client.createIntervention();
		
		//Récupération des actions de l'intervention
		Collection<InterventionDTO> list_inter_2 = client.getInterventions();
		
		//Il doit y avoir une ressource
		assertTrue(list_inter_1.size()+1 == list_inter_2.size());
		assertTrue(list_inter_2.size()>=1);
		
	}

	
	@Test
	public void updateInterventionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création d'une intervention
		InterventionDTO inter = client.createIntervention();
		
		String name = inter.getName();
		
		inter.setName("updatedName");
		
		client.updateIntervention(inter);
		
		//Il doit y avoir une ressource
		assertTrue(client.getIntervention(inter.getId()).getName().equals("updatedName"));
		
	}

}
