package fr.insa.mas.switchManagmentMS.model;

import java.util.ArrayList;

import fr.insa.mas.switchManagmentMS.model.switchModel;

public class switchDB {
private ArrayList<switchModel> switchStorage = new ArrayList<switchModel>();
	
	public switchDB() {
		super();
	}
	
	public ArrayList<switchModel> getswitchState(){
		return switchStorage;
	}
	
	public switchModel getswitchState(String room) {
		for(int i = switchStorage.size()-1; i>=0; i--) {
			if(switchStorage.get(i).getRoom().equals(room)) {
				return switchStorage.get(i);
			}
		}
		switchModel virtualswitch = new switchModel(-1, room);
		return virtualswitch;
	}
	
	public void changeState(int state, String room) {
		switchModel newswitch = new switchModel(state, room);
		switchStorage.add(newswitch);
	}
}
