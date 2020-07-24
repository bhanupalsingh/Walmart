package com.wal.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.wal.manager.EventManager;
import com.wal.models.Building;
import com.wal.models.Event;
import com.wal.models.Location;
import com.wal.models.Room;
import com.wal.models.User;
import com.wal.service.EventService;



public class Driver {
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		EventManager eventManager = new EventManager();
		EventService eventService = new EventService(eventManager);
	
	
		User user1 = new User("a1","a");
		User user2 = new User("b1","b");
		User user3 = new User("c1","c");
		User user4 = new User("d1","d");
		User user5 = new User("e1","e");
		User user6 = new User("f1","f");
		
		
		
		Room r1 = new Room("r1");
		Room r2 = new Room("r2");
		ArrayList<Room> b1rooms = new ArrayList<>();
		b1rooms.add(r1);
		b1rooms.add(r2);
		Building b1 = new Building("b1",b1rooms);
		
		Room r3 = new Room("r3");
		Room r4 = new Room("r4");
		ArrayList<Room> b2rooms = new ArrayList<>();
		b2rooms.add(r3);
		b2rooms.add(r4);
		Building b2 = new Building("b2",b2rooms);
		
		eventManager.addBuilding(b1);
		eventManager.addBuilding(b2);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		
		Date startDate1 = null;
		Date endDate1 = null;
		Date startDate2 = null;
		Date endDate2 = null;
		try {
			startDate1 = sdf.parse("2020-12-12 13:00:00");
			endDate1 = sdf.parse("2020-12-12 14:00:00");
			
			startDate2 = sdf.parse("2020-12-14 13:00:00");
			endDate2 = sdf.parse("2020-12-14 17:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Location l1 = new Location(b1,r1);
		Location l2 = new Location(b2,r3);
		
		
		Event e1 = new Event("e1", startDate1, endDate1, l1 , "a1");
		Event e2 = new Event("e2", startDate2, endDate2, l2 , "a4");
		
		ArrayList<User> userForEvent1 = new ArrayList<>();
		ArrayList<User> userForEvent2 = new ArrayList<>();
		
		userForEvent1.add(user1);
		userForEvent1.add(user2);
		userForEvent1.add(user3);
		
		userForEvent2.add(user4);
		userForEvent2.add(user5);
		userForEvent2.add(user6);
		
		
		eventService.createEvent(e1, userForEvent1);
		eventService.createEvent(e2, userForEvent2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		eventService.getInviteesResponse("e1");
		System.out.println("---------");
		eventService.getRoomAvailibility("b1", startDate1 , endDate1);
		System.out.println("---------");

		eventService.MeetingBasedOnUser("a1", startDate1 , endDate1);
		System.out.println("---------");

		
		eventService.cancelEvent("e1");
		System.out.println("----------");
		
		
		eventService.MeetingBasedOnUser("a1", startDate1 , endDate1);
		System.out.println("---------");

		
		eventService.getInviteesResponse("e1");
		System.out.println("---------");
		
		
//		while(true) {
//			try {
//				String[] s = sc.nextLine().split(" ");	
//				
//				String type = s[0];
//				
//				switch(type) {
//				case "CREATE_EVENT":
//					
//					break;
//					
//				case "UPDATE_EVENT":
//					
//					break;
//				case "CANCEL_EVENT":
//					
//					break;
//				}
//				
//				
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//			
//			
//		}
	
	
	
	
	}
}
