package fr.insa.mas.temperatureManagementMS.controller;

import fr.insa.mas.temperatureManagementMS.model.*;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class temperatureRessource {
	
	private tempDataDB tempRoomAcquisitionList = new tempDataDB();
	
	
	@GetMapping(value="/CurrentTemp/{room}")
	public tempDataAcquisition currentTemp(@PathVariable String room){
		return tempRoomAcquisitionList.getTempData(room);
	}
	
	@GetMapping(value="/tempListRoom/{room}")
	public ArrayList<tempDataAcquisition> tempListRoom(@PathVariable String room){
		
		ArrayList<tempDataAcquisition> tempListOfTheRoom = new ArrayList<tempDataAcquisition>();
		
		for(int i = 0; i < tempRoomAcquisitionList.getTempDataList().size(); i++){

			if(tempRoomAcquisitionList.getTempDataList().get(i).getRoomName().equals(room)){
				tempListOfTheRoom.add(tempRoomAcquisitionList.getTempDataList().get(i));
			}
		}	
		return tempListOfTheRoom;
	}
	
	
	@GetMapping(value="/tempList")
	public tempDataDB tempList(){
		return tempRoomAcquisitionList;
	}
	
		
	@GetMapping(value="/tempDataNumber")
	public String tempDataNumber(){
		
		return ("The number of temperature data collected by the sensor is " + Integer.toString(tempRoomAcquisitionList.getTempDataList().size()));
	}
	
	
	@PostMapping("/tempAcquisition/{room}/{value}/{unit}")
	public void addTempToList(@PathVariable String room, @PathVariable double value, @PathVariable String unit){
		tempRoomAcquisitionList.addTempData(value, unit, room);
	}
	
	

}
