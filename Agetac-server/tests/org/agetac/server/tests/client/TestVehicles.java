package org.agetac.server.tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.BarrackDTO;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestVehicles {

	@BeforeClass
	public static void startServer() throws Exception {
		Server.startServer(8989);
	}
	
	@AfterClass
	public static void stopServer() throws Exception {
		Server.stopServer();
	}
	
	@Test
	public void addVehicleTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une vehicle
		VehicleDTO s = new VehicleDTO("veh", VehicleState.SUR_LES_LIEUX, VehicleType.VLDP, new PositionDTO(12, 12), new BarrackDTO("Janze"));
		
		//Ajout de la vehicle
		client.addVehicle(inter.getId(), s);
		
		//Récupération des vehicles de l'intervention
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>(client.getVehicles(inter.getId()));
		
		//Il doit y avoir une ressource
		assertTrue(inter.getVehicles().size() < vehicles.size());
		assertTrue(vehicles.get(0).getName().equals("testVehicle"));
		
	}
	
	@Test
	public void retreiveVehicleTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une vehicle
		VehicleDTO s = new VehicleDTO("veh", VehicleState.SUR_LES_LIEUX, VehicleType.VLDP, new PositionDTO(12, 12), new BarrackDTO("Janze"));
		
		//Ajout de la vehicle
		client.addVehicle(inter.getId(), s);
		
		//Récupération des vehicles de l'intervention
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>(client.getVehicles(inter.getId()));
		
		//On verifie l'éxistence de la ressource
		assertTrue(vehicles.get(0).getName().equals("testVehicle"));
		
	}

	
	@Test
	public void updateVehicleTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une vehicle
		VehicleDTO s = new VehicleDTO("veh", VehicleState.SUR_LES_LIEUX, VehicleType.VLDP, new PositionDTO(12, 12), new BarrackDTO("Janze"));
		
		//Ajout de la vehicle
		s = client.addVehicle(inter.getId(), s);
		
		s.setName("testUpdate");
		client.updateVehicle(s);
		
		//Récupération des vehicles de l'intervention
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>(client.getVehicles(inter.getId()));

		//Le nom doit avoir été modifier
		assertTrue(vehicles.get(0).getName().equals("testUpdate"));
		
	}
	
	@Test
	public void deleteVehicleTest() throws Exception {
		
		//Initialisation du client
		AgetacClient client = new AgetacClient("localhost", 8989);
		
		//Création de l'intervention
		InterventionDTO inter = client.createIntervention();
		
		//Création d'une vehicle
		VehicleDTO s = new VehicleDTO("veh", VehicleState.SUR_LES_LIEUX, VehicleType.VLDP, new PositionDTO(12, 12), new BarrackDTO("Janze"));
		
		//Ajout de la vehicle
		s = client.addVehicle(inter.getId(), s);

		client.deleteVehicle(s.getId());
		
		//Récupération des vehicles de l'intervention
		List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>(client.getVehicles(inter.getId()));

		//La liste doit être vide
		assertTrue(vehicles.size()==0);
	}
}
