package com.wal.service;

import java.util.ArrayList;
import java.util.Date;

import com.wal.manager.EventManager;
import com.wal.models.Building;
import com.wal.models.Event;
import com.wal.models.EventUserMapping;
import com.wal.models.Response;
import com.wal.models.Room;
import com.wal.models.User;

public class EventService {
	private EventManager eventManager;

	public EventService(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	public void createEvent(Event event, ArrayList<User> users) {
		ArrayList<Event> eventList = eventManager.getEventsbasedOnTime(event.getStartTime() ,event.getEndTime());
		boolean resourceConflict = false;
		for(int i=0;i<eventList.size();i++) {
			ArrayList<EventUserMapping> eventMapping =  eventManager.getEventUserMapping(eventList.get(i).getEventName());
			for(int j=0;j<eventMapping.size();j++) {
				if(users.indexOf(eventMapping.get(j).getUserId())>=0){
					System.out.println("Resource conflict. not able to schedule meeting");
					resourceConflict = true; 
					break;
				}
			}
			
			if(resourceConflict) {
				break;
			}
		}
		
		if(!resourceConflict) {
			//check room availibilty then add entry
			ArrayList<Room> availableRooms = getRoomAvailibility(event.getLocation().getBuilding().getBuildingId(),event.getStartTime(),event.getEndTime());
			if(availableRooms.indexOf(event.getLocation().getRoomId()) >= 0) {
				eventManager.addEvent(event);
				System.out.println("Event Create successfully");
				
				ArrayList<EventUserMapping> eventMapping = new ArrayList<>();
				for(int z=0;z<users.size();z++) {
					EventUserMapping eum = new EventUserMapping(event.getEventName(),users.get(z).getUserId(),Response.NOACTION);
					eventMapping.add(eum);
				}				
				eventManager.addEventMapping(event.getEventName(),eventMapping);
				
			}else {
				System.out.println("Room is not available");
			}
		}
		
		
	}
	
	public void updateEvent(Event event,ArrayList<User> users) {
		eventManager.cancelEvent(event.getEventName());
		createEvent(event,users);
	}

	public void cancelEvent(String eventName) {
		eventManager.cancelEvent(eventName);
	}
	
	
	public void getInviteesResponse(String eventName) {
		Event event = eventManager.getEvent(eventName);
		if(event == null) {
			System.out.println("This event is not exists.");
			return;
		}
		
		ArrayList<EventUserMapping> eventUserMapping = eventManager.getEventUserMapping(eventName);
		for(int i=0;i<eventUserMapping.size();i++) {
			EventUserMapping eventMapping = eventUserMapping.get(i);
			System.out.println(eventMapping.getUserId() +":" + eventMapping.getResponse());
		}
	}
	
	
	public ArrayList<Room> getRoomAvailibility(String buildingId , Date startTime , Date endTime) {
		ArrayList<Event> eventList = eventManager.getAllActiveEventBasedOnBuilding(buildingId);
		Building building = eventManager.getBuildingDetails(buildingId);
		
		
		ArrayList<Room> rooms = building.getRooms();
		
		for(int i=0;i<eventList.size();i++) {
			Event event = eventList.get(i);
			
			if(startTime.compareTo(event.getStartTime()) >= 0 && startTime.compareTo(event.getEndTime()) <= 0) {
				rooms.remove(event.getLocation().getRoomId());
			}else if(endTime.compareTo(event.getStartTime()) >= 0 && endTime.compareTo(event.getEndTime()) <= 0) {
				rooms.remove(event.getLocation().getRoomId());
			}
			
		}
		
		
		System.out.println("Available rooms:-"+rooms.size());
		for(int i=0;i<rooms.size();i++) {
			System.out.println(rooms.get(i).getRoomId());
		}
		
		return rooms;
	}
	
	
	
	public void MeetingBasedOnUser(String userId , Date start , Date end) {
		ArrayList<Event> eventList = eventManager.getEventsbasedOnTime(start , end);
		
		for(int i=0;i<eventList.size();i++) {
			Event event = eventList.get(i);
			
			ArrayList<EventUserMapping> eventMapping =  eventManager.getEventUserMapping(event.getEventName());
			
			for(int j=0;j<eventMapping.size();j++) {
				if(eventMapping.get(j).getUserId().equals(userId)) {
					System.out.println("Event:-"+event.getEventName());
					break;
				}
			}
		}
	}
	
	
	
	
	
}
