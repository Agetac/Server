package org.agetac.server.tests.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestVehicleDemands {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addVehicleDemandTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Cr�ation de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Cr�ation d'une vehicleDemand
		VehicleDemandDTO s = new VehicleDemandDTO("dem", new PositionDTO(12,12), DemandState.ASKED, new GroupDTO("grp", new PositionDTO(12, 12)));
		
		//Ajout de la vehicleDemand
		client.addVehicleDemand(inter.getId(), s);
		
		//R�cup�ration des vehicleDemands de l'intervention
		List<VehicleDemandDTO> vehicleDemands = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(inter.getId()));
		
		//Il doit y avoir une ressource
		assertTrue(inter.getDemands().size() < vehicleDemands.size());
		assertTrue(vehicleDemands.get(0).getName().equals("testVehicleDemand"));
		
	}
	
	@Test
	public void retreiveVehicleDemandTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Cr�ation de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Cr�ation d'une vehicleDemand
		VehicleDemandDTO s = new VehicleDemandDTO("dem", new PositionDTO(12,12), DemandState.ASKED, new GroupDTO("grp", new PositionDTO(12, 12)));
		
		//Ajout de la vehicleDemand
		client.addVehicleDemand(inter.getId(), s);
		
		//R�cup�ration des vehicleDemands de l'intervention
		List<VehicleDemandDTO> vehicleDemands = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(inter.getId()));
		
		//On verifie l'�xistence de la ressource
		assertTrue(vehicleDemands.get(0).getName().equals("testVehicleDemand"));
		
	}

	
	@Test
	public void updateVehicleDemandTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Cr�ation de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Cr�ation d'une vehicleDemand
		VehicleDemandDTO s = new VehicleDemandDTO("dem", new PositionDTO(12,12), DemandState.ASKED, new GroupDTO("grp", new PositionDTO(12, 12)));
		
		//Ajout de la vehicleDemand
		s = client.addVehicleDemand(inter.getId(), s);
		
		s.setName("testUpdate");
		client.updateVehicleDemand(s);
		
		//R�cup�ration des vehicleDemands de l'intervention
		List<VehicleDemandDTO> vehicleDemands = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(inter.getId()));

		//Le nom doit avoir �t� modifier
		assertTrue(vehicleDemands.get(0).getName().equals("testUpdate"));
		
	}
	
	@Test
	public void deleteVehicleDemandTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Cr�ation de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Cr�ation d'une vehicleDemand
		VehicleDemandDTO s = new VehicleDemandDTO("dem", new PositionDTO(12,12), DemandState.ASKED, new GroupDTO("grp", new PositionDTO(12, 12)));
		
		//Ajout de la vehicleDemand
		s = client.addVehicleDemand(inter.getId(), s);

		client.deleteVehicleDemand(s.getId());
		
		//R�cup�ration des vehicleDemands de l'intervention
		List<VehicleDemandDTO> vehicleDemands = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(inter.getId()));

		//La liste doit �tre vide
		assertTrue(vehicleDemands.size()==0);
	}
}
