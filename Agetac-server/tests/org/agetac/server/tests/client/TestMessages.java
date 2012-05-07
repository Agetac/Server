package org.agetac.server.tests.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMessages {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addMessageTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une message
		MessageDTO s = new MessageDTO("un message de test", new Date());
		
		//Ajout de la message
		client.addMessage(inter.getId(), s);
		
		//Récupération des messages de l'intervention
		List<MessageDTO> messages = new ArrayList<MessageDTO>(client.getMessages(inter.getId()));
		
		//Il doit y avoir une ressource
		assertTrue(inter.getMessages().size() < messages.size());
		assertTrue(messages.get(0).getText().equals("un message de test"));
		
	}
	
	@Test
	public void retreiveMessageTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une message
		MessageDTO s = new MessageDTO("un message de test", new Date());
		
		//Ajout de la message
		client.addMessage(inter.getId(), s);
		
		//Récupération des messages de l'intervention
		List<MessageDTO> messages = new ArrayList<MessageDTO>(client.getMessages(inter.getId()));
		
		//On verifie l'éxistence de la ressource
		assertTrue(messages.get(0).getText().equals("un message de test"));
		
	}


	
}
