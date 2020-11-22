package fr.insa.mas.presenceManagementMS.model;

import java.util.ArrayList;



public class presenceDB {
	private ArrayList<presenceModel> presenceStorage = new ArrayList<presenceModel>();
	
	
	public presenceDB() {
		super();
	}
	
	public ArrayList<presenceModel> getPresenceList(){
		return presenceStorage;
	}
	
public presenceModel getPresenceData(String room){
		
		for(int i = presenceStorage.size()- 1; i >= 0; i--){
			
			if(presenceStorage.get(i).getRoom().equals(room)){
				return presenceStorage.get(i);
			}
		}
		presenceModel virtualLightData = new presenceModel(-1,room);
		
		return virtualLightData;
	}

public void addPresenceData(int value, String room) {
	presenceModel newPresenceData = new presenceModel(value, room);
	presenceStorage.add(newPresenceData);
}
}
