package fr.insa.soa.RoomManagementWS.Controller;

import fr.insa.soa.RoomManagementWS.model.*;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.RoomManagementWS.model.*;

@CrossOrigin
@RestController
public class RoomListResource {
	
	roomList GEIroomList = new roomList();
	
	@GetMapping("/rooms")
	public ArrayList<String> getRooms(){
		return GEIroomList.getRoomList();
	}
	
	@GetMapping("/allRoomInfo/{room}")
	public roomInfoList getAllInfoForARoom(@PathVariable String room){
		return GEIroomList.getAllInfo(room);
	}
	
	@GetMapping("/CurrentRoomInfo/{room}")
	public roomEnvironment getCurrentRoomInfo(@PathVariable String room){
		
		RestTemplate newRestTemplate = new RestTemplate();
		
		LightDataAcquisition lightData = newRestTemplate.getForObject("http://localhost:8081/CurrentLight/" + room, LightDataAcquisition.class);
		tempDataAcquisition tempData = newRestTemplate.getForObject("http://localhost:8082/CurrentTemp/" + room, tempDataAcquisition.class);
		//presence...
				
		roomEnvironment currentInfo = new roomEnvironment(room, lightData, tempData,   
				GEIroomList.getSpecificRoomInfo(room).getWindowsState(), 
				GEIroomList.getSpecificRoomInfo(room).getDoorsState(), "YES");
		
		return currentInfo;
	}
	
	
	@PostMapping("/updateRoomInfo/{room}/{light}/{temp}")
	public void updateRoomInfo(@PathVariable String room, @PathVariable int light, @PathVariable double temp){
		
		RestTemplate newRestTemplate = new RestTemplate();
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LightDataAcquisition newLight = new LightDataAcquisition();
		newLight.setRoomName(room);
		newLight.setValue(light);
		newLight.setUnit("Lux");

		HttpEntity<LightDataAcquisition> httpEntity = new HttpEntity<>(newLight, headers);*/
	
		LightDataAcquisition lightData = newRestTemplate.postForObject("http://localhost:8081/LightAcquisition/"+room+"/"+light+"/Lux", null, LightDataAcquisition.class);
		tempDataAcquisition tempData = newRestTemplate.postForObject("http://localhost:8082/tempAcquisition/"+room+"/"+temp+"/degC", null, tempDataAcquisition.class);
		
		LightDataAcquisition newLight = new LightDataAcquisition(light, "Lux", room);
		tempDataAcquisition newTemp = new tempDataAcquisition(temp, "degC", room);
		
		//Traitement scénarios...
		
		
		roomEnvironment newRoomInfo = new roomEnvironment(room,  newLight, newTemp, "CLOSED", "OPEN", "YES");
		
		GEIroomList.addRoomToList(newRoomInfo);
	}
	
	
	

}
