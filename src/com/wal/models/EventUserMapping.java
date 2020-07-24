package com.wal.models;

public class EventUserMapping {
	private String eventId;
	private String userId;
	private Response response;
	
	public EventUserMapping(String eventId, String userId, Response response) {
		this.eventId = eventId;
		this.userId = userId;
		this.response = response;
	}
	
	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}


	
	
	

}
