package fr.insa.soa.RoomManagementWS.model;

import java.util.ArrayList;

public class roomInfoList {
	
	private ArrayList<roomEnvironment> roominfolist;
	

	public roomInfoList(roomEnvironment newRoomInfo){
		this.roominfolist = new ArrayList<roomEnvironment>();
		this.roominfolist.add(newRoomInfo);
	}
	
	public ArrayList<roomEnvironment> getRoomInfoList(){
		return this.roominfolist;
	}
	
	public void addRoomInfoToList(roomEnvironment newRoomInfo){
		this.roominfolist.add(newRoomInfo);
	}
	
	public roomEnvironment getCurrentRoomInfo(){
		return roominfolist.get(roominfolist.size()-1);
	}
}
