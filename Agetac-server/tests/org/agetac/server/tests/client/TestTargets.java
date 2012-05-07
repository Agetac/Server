package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.TargetDTO.TargetType;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestTargets {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addTargetTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une target
		TargetDTO s = new TargetDTO("testTarget",TargetType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la target
		client.addTarget(inter.getId(), s);
		
		//Récupération des targets de l'intervention
		List<TargetDTO> targets = new ArrayList<TargetDTO>(client.getTargets(inter.getId()));
		
		//Il doit y avoir une ressource
		assertTrue(inter.getTargets().size() < targets.size());
		assertTrue(targets.get(0).getName().equals("testTarget"));
		
	}
	
	@Test
	public void retreiveTargetTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une target
		TargetDTO s = new TargetDTO("testTarget",TargetType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la target
		client.addTarget(inter.getId(), s);
		
		//Récupération des targets de l'intervention
		List<TargetDTO> targets = new ArrayList<TargetDTO>(client.getTargets(inter.getId()));
		
		//On verifie l'éxistence de la ressource
		assertTrue(targets.get(0).getName().equals("testTarget"));
		
	}

	
	@Test
	public void updateTargetTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une target
		TargetDTO s = new TargetDTO("testTarget",TargetType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la target
		s = client.addTarget(inter.getId(), s);
		
		s.setName("testUpdate");
		client.updateTarget(s);
		
		//Récupération des targets de l'intervention
		List<TargetDTO> targets = new ArrayList<TargetDTO>(client.getTargets(inter.getId()));

		//Le nom doit avoir été modifier
		assertTrue(targets.get(0).getName().equals("testUpdate"));
		
	}
	
	@Test
	public void deleteTargetTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une target
		TargetDTO s = new TargetDTO("toDeleteTarget",TargetType.FIRE, new PositionDTO(42,42));
		
		//Ajout de la target
		s = client.addTarget(inter.getId(), s);

		client.deleteTarget(s.getId());
		
		//Récupération des targets de l'intervention
		List<TargetDTO> targets = new ArrayList<TargetDTO>(client.getTargets(inter.getId()));

		//La liste doit être vide
		assertTrue(targets.size()==0);
	}
}
