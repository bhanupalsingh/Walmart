package com.wal.models;

import java.util.ArrayList;

public class Building {
	private String buildingId;
	private ArrayList<Room> rooms ;
	
	
	
	public Building(String buildingId, ArrayList<Room> rooms) {
		this.buildingId = buildingId;
		this.rooms = rooms;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	
	
	
}
