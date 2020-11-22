package fr.insa.soa.RoomManagementWS.model;

import java.util.ArrayList;

public class windowDB {

	private ArrayList<windowModel> windowStorage = new ArrayList<windowModel>();
	
	public windowDB() {
		super();
	}
	
	public ArrayList<windowModel> getWindowState(){
		return windowStorage;
	}
	
	public windowModel getWindowState(String room) {
		for(int i = windowStorage.size()-1; i>=0; i--) {
			if(windowStorage.get(i).getRoom().equals(room)) {
				return windowStorage.get(i);
			}
		}
		windowModel virtualWindow = new windowModel(-1, room);
		return virtualWindow;
	}
	
	public void changeState(int state, String room) {
		windowModel newWindow = new windowModel(state, room);
		windowStorage.add(newWindow);
	}
}
