package com.wal.models;

public class Location {
	private Building building;
	private Room roomId;
	
	
	
	
	public Location(Building building, Room roomId) {
		this.building = building;
		this.roomId = roomId;
	}
	
	
	public Building getBuilding() {
		return this.building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public Room getRoomId() {
		return roomId;
	}
	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}
	
	
	
}
