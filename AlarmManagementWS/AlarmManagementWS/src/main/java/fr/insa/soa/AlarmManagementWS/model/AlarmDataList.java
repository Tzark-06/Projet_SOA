package fr.insa.soa.AlarmManagementWS.model;

import java.util.ArrayList;


public class AlarmDataList {

private ArrayList<AlarmAcquisition> alarmStorage = new ArrayList<AlarmAcquisition>();
	
	public AlarmDataList(){
		super();
	}
	
	//Return all state values of the alarm of the room
	public ArrayList<AlarmAcquisition> getAlarmStateList(){
		return alarmStorage;
	}
	
	//Retrieve the last state of the alarm
	public AlarmAcquisition getAlarmState(String room){
		
		for(int i = alarmStorage.size()- 1; i >= 0; i--){
			
			if(alarmStorage.get(i).getRoom().equals(room)){
				return alarmStorage.get(i);
			}
		}
		AlarmAcquisition virtualAlarm = new AlarmAcquisition(room, "OFF");
		
		return virtualAlarm;
	}
	
	public void addAlarmData(String state, String roomName){
		AlarmAcquisition newAlarm = new AlarmAcquisition(roomName, state);
		alarmStorage.add(newAlarm);
	}
}
