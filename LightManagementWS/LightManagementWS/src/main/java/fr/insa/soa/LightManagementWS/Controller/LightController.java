package fr.insa.soa.LightManagementWS.Controller;


import fr.insa.soa.LightManagementWS.model.*;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LightController {
	
	private LightDataDB LightRoomAcquisitionList = new LightDataDB();
	
	
	@GetMapping(value="/CurrentLight/{room}")
	public LightDataAcquisition currentLight(@PathVariable String room){
		return LightRoomAcquisitionList.getLightData(room);
	}
	
	@GetMapping(value="/LightListRoom/{room}")
	public ArrayList<LightDataAcquisition> lightListRoom(@PathVariable String room){
		
		ArrayList<LightDataAcquisition> lightListOfTheRoom = new ArrayList<LightDataAcquisition>();
		
		for(int i = 0; i < LightRoomAcquisitionList.getLightDataList().size(); i++){

			if(LightRoomAcquisitionList.getLightDataList().get(i).getRoomName().equals(room)){
				lightListOfTheRoom.add(LightRoomAcquisitionList.getLightDataList().get(i));
			}
		}	
		return lightListOfTheRoom;
	}
	
	
	@GetMapping(value="/LightList")
	public LightDataDB lightList(){
		return LightRoomAcquisitionList;
	}
	
		
	@GetMapping(value="/LightDataNumber")
	public String lightDataNumber(){
		
		return ("The number of light data collected by the sensor is " + Integer.toString(LightRoomAcquisitionList.getLightDataList().size()));
	}
	
	
	@PostMapping("/LightAcquisition/{room}/{value}/{unit}")
	public void addLightAcq(@PathVariable String room, @PathVariable int value, @PathVariable String unit){
		LightRoomAcquisitionList.addLightData(value, unit, room);
	}
	
	
	
}

