package fr.insa.soa.RoomManagementWS.model;

import java.util.ArrayList;

public class roomList {
	
	ArrayList<roomInfoList> roomlist;
	
	public roomList(){
		this.roomlist = new ArrayList<roomInfoList>();
	}
	
	public ArrayList<String> getRoomList(){
		ArrayList<String> nameRoomList = new ArrayList<String>();
		for(int i = 0; i <roomlist.size(); i++){
			nameRoomList.add(roomlist.get(i).getRoomInfoList().get(0).getRoomName());
		}
		return nameRoomList;
	}
	
	public void addRoomToList(roomEnvironment roomInfo){
		
		boolean roomFound = false;
		
		for(int i = 0; i < roomlist.size(); i++){
			if (roomlist.get(i).getRoomInfoList().get(0).getRoomName().equals(roomInfo.getRoomName())){
				roomlist.get(i).addRoomInfoToList(roomInfo);
				roomFound = true;
			}
		}
		if(roomFound == false){
			roomlist.add(new roomInfoList(roomInfo));
		}
		
	}
}
