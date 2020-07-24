package com.wal.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import com.wal.models.Building;
import com.wal.models.Event;
import com.wal.models.EventStatus;
import com.wal.models.EventUserMapping;


public class EventManager {
	
		private HashMap<String, Event> events;
		private HashMap<String , ArrayList<EventUserMapping>> usersInEvent;
		private HashMap<String,Building> building;
		private ArrayList<Event> ModifiedOrCancelhistory;
		
		
		public EventManager() {
			events = new HashMap<>();
			usersInEvent = new HashMap<>();
			building = new HashMap<>();
			ModifiedOrCancelhistory = new ArrayList<>();
		}


		public void cancelEvent(String eventName) {
			if(events.containsKey(eventName)) {
				events.get(eventName).setEventStatus(EventStatus.CANCELLED);
				ModifiedOrCancelhistory.add(events.get(eventName));
				events.remove(eventName);
				System.out.println("Event cancelled successfully");
			}else {
				System.out.println("No event found");
			}
		}
		
		public void addBuilding(Building build) {
			building.put(build.getBuildingId(), build);
		}
		
		
		
		public void addEvent(Event event) {
			if(events.containsKey(event.getEventName())) {
				System.out.println("Event is already there");
			}else {
				events.put(event.getEventName(),event);
			}
		}
		
		public void addEventMapping(String key , ArrayList<EventUserMapping> value) {
			usersInEvent.put(key, value);
		}


		public ArrayList<EventUserMapping> getEventUserMapping(String eventName) {
			return usersInEvent.get(eventName);
		}
		
		public ArrayList<Event> getAllActiveEventBasedOnBuilding(String buildingName) {
			ArrayList<Event> eventList = new ArrayList<>();
			
			for(Entry<String, Event> map  : events.entrySet()) {
				if(map.getValue().getEventStatus().equals(EventStatus.ACTIVE) &&  map.getValue().getLocation().getBuilding().getBuildingId().equals(buildingName) ) {
					eventList.add(map.getValue());
				}
			}
			return eventList;
		}
		
		
		public Building getBuildingDetails(String buildingName) {
			return building.get(buildingName);
		}
		
		public ArrayList<Event> getEventsbasedOnTime(Date start , Date end) {
			ArrayList<Event> eventList = new ArrayList<>();
			
			for(Entry<String, Event> map  : events.entrySet()) {
				if(map.getValue().getStartTime().compareTo(start) >= 0 && map.getValue().getStartTime().compareTo(end) <=0) {
					eventList.add(map.getValue());
				}
			}
			return eventList;
		}
		
		public Event getEvent(String eventName) {
			if(events.containsKey(eventName)) {
				return events.get(eventName);
			}
			return null;
		}
		
		
		
}
