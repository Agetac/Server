package org.agetac.server.tests.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.ActionDTO.ActionType;
import org.agetac.common.dto.BarrackDTO;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.SourceDTO.SourceType;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.TargetDTO.TargetType;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAgetacClient{

	private AgetacClient c;
	
	public TestAgetacClient(){}
	
	
	@Before
	public void setUp() throws Exception {
		// Mise en place du serveur avec une intervention
		Server.startServer(8989);
		this.c = new AgetacClient("localhost", 8989);
		c.createIntervention();
		initIntervention(0);
	}
	

	private void initIntervention(int id) {
		System.out.println("Initialisation d'une intervention");
		
		InterventionDTO i = c.getIntervention(id);
		i.setName("Intervention Test");
		i.setPosition(new PositionDTO(42,42));
		c.updateIntervention(i);
		
		ActionDTO a = new ActionDTO("act", ActionType.FIRE, new PositionDTO(12,12), new PositionDTO(42,42));
		SourceDTO s = new SourceDTO("src",SourceType.FIRE, new PositionDTO(42,42));
		TargetDTO t = new TargetDTO("cib", TargetType.HUMAN, new PositionDTO(42, 42));
		MessageDTO m = new MessageDTO("un message", new Date());
		VehicleDemandDTO vd = new VehicleDemandDTO("dem", new PositionDTO(12,12), DemandState.ASKED, new GroupDTO("grp", new PositionDTO(12, 12)));
		VehicleDTO v = new VehicleDTO("veh", VehicleState.SUR_LES_LIEUX, VehicleType.VLDP, new PositionDTO(12, 12), new BarrackDTO("Janze"));
		
		c.addAction(i.getId(), a);
		c.addMessage(i.getId(), m);
		c.addSource(i.getId(), s);
		c.addTarget(i.getId(), t);
		c.addVehicle(i.getId(), v);
		c.addVehicleDemand(i.getId(), vd);
		
	}
	
	@After
	public void tearDown() throws Exception {
		// Arret du serveur
		Server.stopServer();
	}
	
	@Test
	public void getAllIntervention() {
		System.out.println("Création d'une intervention");
		
		Collection<InterventionDTO> col = c.getInterventions();
		List<InterventionDTO> li = new ArrayList<InterventionDTO> (col);
		
		//Test d'accès à l'objet
		assertTrue(li.get(0).getId() == 0);
		assertTrue(li.size() > 0);
		
	}
	
	//On récupère directement la liste des objets depuis le serveur (marche pas)
	@Test
	public void getVehicleDemands() {
		Collection<VehicleDemandDTO> col = c.getVehicleDemands(0);
		ArrayList<VehicleDemandDTO> li = new ArrayList<VehicleDemandDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getVehicles() {
		Collection<VehicleDTO> col = c.getVehicles(0);
		ArrayList<VehicleDTO> li = new ArrayList<VehicleDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getSources() {
		Collection<SourceDTO> col = c.getSources(0);
		ArrayList<SourceDTO> li = new ArrayList<SourceDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getTargets() {
		Collection<TargetDTO> col = c.getTargets(0);
		ArrayList<TargetDTO> li = new ArrayList<TargetDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getActions() {
		Collection<ActionDTO> col = c.getActions(0);
		ArrayList<ActionDTO> li = new ArrayList<ActionDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getMessages() {
		Collection<MessageDTO> col = c.getMessages(0);
		ArrayList<MessageDTO> li = new ArrayList<MessageDTO>(col);
		li.get(0).getId();
	}
	
	
	

	//On récupère la liste des objets depuis l'objet interventionDTO
	
	@Test
	public void getVehicleDemandsBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<VehicleDemandDTO> col = inter.getDemands();
		ArrayList<VehicleDemandDTO> li = new ArrayList<VehicleDemandDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getVehiclesBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<VehicleDTO> col = inter.getVehicles();
		ArrayList<VehicleDTO> li = new ArrayList<VehicleDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getSourcesBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<SourceDTO> col = inter.getSources();
		ArrayList<SourceDTO> li = new ArrayList<SourceDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getTargetsBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<TargetDTO> col = inter.getTargets();
		ArrayList<TargetDTO> li = new ArrayList<TargetDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getActionsBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<ActionDTO> col = inter.getActions();
		ArrayList<ActionDTO> li = new ArrayList<ActionDTO>(col);
		li.get(0).getId();
	}
	
	@Test
	public void getMessagesBis() {
		InterventionDTO inter = c.getIntervention(0);
		Collection<MessageDTO> col = inter.getMessages();
		ArrayList<MessageDTO> li = new ArrayList<MessageDTO>(col);
		li.get(0).getId();
	}


}
