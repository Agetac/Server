package org.agetac.server.entities;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class VehicleEntity {

	public enum VehicleState {
		DISPO_CASERNE, ALERTE, PARTIS, SUR_LES_LIEUX, TRANSPORT_HOPITAL, DISPO_RADIO, TEMPS_DEPASSE, DEMOBILISE
	}

	public enum VehicleType {
		BEA, BRS, BLS, EMB, BLSP, CAEM, CCFM, CCGC, CCGCLC, DA, EPS, ESPM, FMOGP, FPT, MPR, PCM, PEVSD, SAC_PS, UTP, VPRO, VRCB, VICB, VAR, VL, VLCC, VLDP, VLCGD, VLCS, VLCG, VLHR, VLSV, VLOS, VLS, VNRBC, VPL, VPHV, VRAD, VSAV, VSM, VSR, VTP, VTU, VCYNO
	}

	private String name;
	private VehicleState state;
	private VehicleType type;
	
	private PositionEntity position;
	
	@NotPersistent
	private BarrackEntity barrack;

	/* TODO Need to figure out how to handle this relationship. */
	/*
	 * UPDATE 07/05: Well, we don't manage it that well. We should start by
	 * removing the VehicleResource.add(VehicleDTO vehicle) method..
	 */
	private InterventionEntity intervention;

	private Date arrivalTime, demandTime, retTime;

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getDemandTime() {
		return demandTime;
	}

	public void setDemandTime(Date demandTime) {
		this.demandTime = demandTime;
	}

	public Date getRetTime() {
		return retTime;
	}

	public void setRetTime(Date retTime) {
		this.retTime = retTime;
	}

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;

	public VehicleEntity() {
	}

	public VehicleEntity(String n, VehicleState s, VehicleType t,
			PositionEntity p, BarrackEntity b) {
		this.name = n;
		this.state = s;
		this.type = t;
		this.position = p;
		this.barrack = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleState getState() {
		return state;
	}

	public void setState(VehicleState state) {
		this.state = state;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public PositionEntity getPosition() {
		return position;
	}

	public void setPosition(PositionEntity position) {
		this.position = position;
	}

	public BarrackEntity getBarrack() {
		return barrack;
	}

	public void setBarrack(BarrackEntity barrack) {
		this.barrack = barrack;
	}

	public long getId() {
		return id;
	}

	public void update(VehicleEntity vehicle) {
		this.type = vehicle.getType();
		this.position = vehicle.getPosition();
		this.name = vehicle.getName();
		this.state = vehicle.getState();
		this.barrack = vehicle.getBarrack();
		this.demandTime = vehicle.getDemandTime();
		this.retTime = vehicle.getRetTime();
		this.arrivalTime = vehicle.getArrivalTime();
	}
}
