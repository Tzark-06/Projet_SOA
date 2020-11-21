package fr.insa.mas.presenceManagementMS.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
import fr.insa.mas.presenceManagementMS.model.*;

@CrossOrigin
@RestController
public class presenceRessource {
	
	private presenceDB presenceDataList = new presenceDB();
	
	@GetMapping(value="/CurrentPresence/{room}")
	public presenceModel currentPresence(@PathVariable String room) {
		return presenceDataList.getPresenceData(room);
	}
	
	@GetMapping(value="/PresenceListRoom/{room}")
	public ArrayList<presenceModel> presenceListRoom(@PathVariable String room){
		
		ArrayList<presenceModel> presenceListOfTheRoom = new ArrayList<presenceModel>();
		
		for(int i = 0; i < presenceDataList.getPresenceList().size(); i++){

			if(presenceDataList.getPresenceList().get(i).getRoom().equals(room)){
				presenceListOfTheRoom.add(presenceDataList.getPresenceList().get(i));
			}
		}	
		return presenceListOfTheRoom;
	}
	
	@GetMapping(value="/PresenceList")
	public presenceDB presenceList(){
		return presenceDataList;
	}
	
		
	@GetMapping(value="/PresenceDataNumber")
	public String presenceDataNumber(){
		
		return ("The number of presence data collected by the sensor is " + Integer.toString(presenceDataList.getPresenceList().size()));
	}
	
	
	@PostMapping("/PresenceAcquisition/{room}/{value}")
	public void addPresenceAcq(@PathVariable String room, @PathVariable int value){
		presenceDataList.addPresenceData(value, room);
	}
	
	

}
