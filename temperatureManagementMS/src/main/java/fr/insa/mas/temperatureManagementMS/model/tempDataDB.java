package fr.insa.mas.temperatureManagementMS.model;

import java.util.ArrayList;

public class tempDataDB {
	private ArrayList<tempDataAcquisition> tempStorage = new ArrayList<tempDataAcquisition>();
	public tempDataDB(){
		super();
	}
	//Return all temperature data acquisitions
	public ArrayList<tempDataAcquisition> getTempDataList(){
		return tempStorage;
	}
	//Retrieve the last temperature data value from the sensor
		public tempDataAcquisition getTempData(String room){
			
			for(int i = tempStorage.size()- 1; i > 0; i--){
				
				if(tempStorage.get(i).getRoomName().equals(room)){
					return tempStorage.get(i);
				}
			}
			tempDataAcquisition virtualTempData = new tempDataAcquisition(-1,"°C",room);
			
			return virtualTempData;
		}
		
		public void addTempData(double value, String unit, String roomName){
			tempDataAcquisition newTempData = new tempDataAcquisition(value, unit, roomName);
			tempStorage.add(newTempData);
		}
		
}