package com.wal.models;

import java.util.Date;

public class Event {
	private String eventName;
	private Date startTime;
	private Date endTime;
	private Location location;
	private String ownerId;
	private EventStatus eventStatus;
	
	
	
	
	
	public Event(String eventName, Date startTime, Date endTime, Location location, String ownerId) {
		this.eventName = eventName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.ownerId = ownerId;
		this.eventStatus = EventStatus.ACTIVE;
	}
	
	
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}


	public String getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}


	public EventStatus getEventStatus() {
		return eventStatus;
	}


	public void setEventStatus(EventStatus eventStatus) {
		this.eventStatus = eventStatus;
	}
	
	
	
	
	
}
