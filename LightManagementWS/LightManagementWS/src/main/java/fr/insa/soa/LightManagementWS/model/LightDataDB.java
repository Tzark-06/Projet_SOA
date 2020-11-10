package fr.insa.soa.LightManagementWS.model;

import java.util.ArrayList;


public class LightDataDB {

	private ArrayList<LightDataAcquisition> lightStorage = new ArrayList<LightDataAcquisition>();
	
	public LightDataDB(){
		super();
	}
	
	//Return all light data acquisitions
	public ArrayList<LightDataAcquisition> getLightDataList(){
		return lightStorage;
	}
	
	//Retrieve the last light data value from the sensor
	public LightDataAcquisition getLightData(String room){
		
		for(int i = lightStorage.size()- 1; i >= 0; i--){
			
			if(lightStorage.get(i).getRoomName().equals(room)){
				return lightStorage.get(i);
			}
		}
		LightDataAcquisition virtualLightData = new LightDataAcquisition(-1,"lux",room);
		
		return virtualLightData;
	}
	
	public void addLightData(int value, String unit, String roomName){
		LightDataAcquisition newLightData = new LightDataAcquisition(value, unit, roomName);
		lightStorage.add(newLightData);
	}
	
	
}

