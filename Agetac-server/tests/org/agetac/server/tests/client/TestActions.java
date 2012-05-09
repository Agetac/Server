package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

public class TestActions {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addActionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une action
		ActionDTO s = new ActionDTO("testAction",ActionType.FIRE, new PositionDTO(42,42), new PositionDTO(42,43));
		
		//Ajout de la action
		client.addAction(inter.getId(), s);
		
		//Récupération des actions de l'intervention
		List<ActionDTO> actions = new ArrayList<ActionDTO>(client.getActions(inter.getId()));
		
		//Il doit y avoir une ressource
		assertTrue(inter.getActions().size() < actions.size());
		
		//On vérifie qu'elle correspond
		assertTrue(actions.get(0).getName().equals("testAction"));
		assertTrue(actions.get(0).getType().equals(ActionType.FIRE));
		assertTrue(actions.get(0).getOrigin().getLatitude() == 42 && actions.get(0).getOrigin().getLongitude() == 42);
		assertTrue(actions.get(0).getAim().getLatitude() == 42 && actions.get(0).getAim().getLongitude() == 43);
	}
	
	@Test
	public void retreiveActionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une action
		ActionDTO s = new ActionDTO("testAction",ActionType.FIRE, new PositionDTO(42,42), new PositionDTO(42,43));
		
		//Ajout de la action
		client.addAction(inter.getId(), s);
		
		//Récupération des actions de l'intervention
		List<ActionDTO> actions = new ArrayList<ActionDTO>(client.getActions(inter.getId()));
		
		//On verifie l'éxistence de la ressource
		assertTrue(actions.get(0).getName().equals("testAction"));
		assertTrue(actions.get(0).getType().equals(ActionType.FIRE));
		assertTrue(actions.get(0).getOrigin().getLatitude() == 42 && actions.get(0).getOrigin().getLongitude() == 42);
		assertTrue(actions.get(0).getAim().getLatitude() == 42 && actions.get(0).getAim().getLongitude() == 43);
	}

	
	@Test
	public void updateActionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une action
		ActionDTO s = new ActionDTO("testAction",ActionType.FIRE, new PositionDTO(42,42), new PositionDTO(42,43));
		
		//Ajout de la action
		s = client.addAction(inter.getId(), s);
		
		s.setName("testUpdate");
		s.setPosition(new PositionDTO(22,22));
		s.setOrigin(new PositionDTO(22,22));
		s.setAim(new PositionDTO(22,23));
		s.setType(ActionType.HUMAN);
		
		client.updateAction(s);
		
		//Récupération des actions de l'intervention
		List<ActionDTO> actions = new ArrayList<ActionDTO>(client.getActions(inter.getId()));

		//Le nom doit avoir été modifier
		assertTrue(actions.get(0).getName().equals("testUpdate"));
		assertTrue(actions.get(0).getType().equals(ActionType.HUMAN));
		assertTrue(actions.get(0).getOrigin().getLatitude() == 22 && actions.get(0).getOrigin().getLongitude() == 22);
		assertTrue(actions.get(0).getAim().getLatitude() == 22 && actions.get(0).getAim().getLongitude() == 23);
	}
	
	@Test
	public void deleteActionTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une action
		ActionDTO s = new ActionDTO("toDeleteAction",ActionType.FIRE, new PositionDTO(42,42), new PositionDTO(42,43));
		
		//Ajout de la action
		s = client.addAction(inter.getId(), s);

		client.deleteAction(s.getId());
		
		//Récupération des actions de l'intervention
		List<ActionDTO> actions = new ArrayList<ActionDTO>(client.getActions(inter.getId()));

		//La liste doit être vide
		assertTrue(actions.size()==0);
	}
}
