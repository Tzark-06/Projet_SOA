package fr.insa.soa.RoomManagementWS.Controller;

import fr.insa.soa.RoomManagementWS.model.*;

import java.awt.PageAttributes.OriginType;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.RoomManagementWS.model.*;

import org.json.JSONException;

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
		
		//SENSORS
		LightDataAcquisition lightData = newRestTemplate.getForObject("http://localhost:8081/CurrentLight/" + room, LightDataAcquisition.class);
		tempDataAcquisition tempData = newRestTemplate.getForObject("http://localhost:8082/CurrentTemp/" + room, tempDataAcquisition.class);
		presenceModel presence = newRestTemplate.getForObject("http://localhost:8084/CurrentPresence/" + room, presenceModel.class);
		String detectPresence = "";
		
		if(presence.getValue() == 1){
			detectPresence = "YES";
		}
		else {
			detectPresence = "NO";
		}
		
		//ACTUATORS
		AlarmAcquisition alarm = newRestTemplate.getForObject("http://localhost:8089/CurrentAlarmState/" + room, AlarmAcquisition.class);
		//switch...
		windowModel windows = newRestTemplate.getForObject("http://localhost:8085/CurrentWindowState/" + room, windowModel.class);
		String windowState = "";
		
		if(windows.getState() == 1){
			windowState = "OPENED";
		}
		else {
			windowState = "CLOSED";
		}
		
		//door...
		
		/************ SCENARIOS **************/
		
		/********************************************************************************************************/
		/*Scénario 1 : */
		/********************************************************************************************************/
		
		/********************************************************************************************************/
		/*Scénario 2 : Scenario 2: After 7pm the rooms are locked (windows and door closed), lights off. 
		If the sensor detects the presence of an individual, the alarm is triggered and the police are notified! */
		Date currDate = new Date();
		SimpleDateFormat pattern = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		String currentTime = pattern.format(currDate);
		
		if(currentTime.contains(" 19:") && detectPresence.equals("YES")){
			alarm.setAlarmState("ON");
		}
		else {
			alarm.setAlarmState("OFF");
		}
		//System.out.println("Current date: " + currentTime);	
		/********************************************************************************************************/
		
		/*************************************/
		
		//Update of the room environment information
		roomEnvironment currentInfo = new roomEnvironment(room, lightData, tempData,   
				windowState, "CLOSED", detectPresence,"ON",alarm.getAlarmState());
		
		return currentInfo;
	}
	
	
	@PostMapping("/updateRoomInfo/{room}/{light}/{temp}/{presence}")
	public void updateRoomInfo(@PathVariable String room, @PathVariable int light, @PathVariable double temp, @PathVariable int presence) {
	
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LightDataAcquisition newLight = new LightDataAcquisition();
		newLight.setRoomName(room);
		newLight.setValue(light);
		newLight.setUnit("Lux");

		HttpEntity<LightDataAcquisition> httpEntity = new HttpEntity<>(newLight, headers);*/
	
		/*RestTemplate newRestTemplate = new RestTemplate();
		
		
		LightDataAcquisition lightData = newRestTemplate.postForObject("http://localhost:8081/LightAcquisition/"+room+"/"+light+"/Lux", null, LightDataAcquisition.class);
		tempDataAcquisition tempData = newRestTemplate.postForObject("http://localhost:8082/tempAcquisition/"+room+"/"+temp+"/degC", null, tempDataAcquisition.class);
		presenceModel presData = newRestTemplate.postForObject("http://localhost:8084/PresenceAcquisition/"+room+"/"+presence, null, presenceModel.class);
		
		LightDataAcquisition newLight = new LightDataAcquisition(light, "Lux", room);
		tempDataAcquisition newTemp = new tempDataAcquisition(temp, "degC", room);
		presenceModel currentPresence = new presenceModel(presence, room);
		
		String detectPresence = "";
		
		if(presence == 1){
			detectPresence = "YES";
		}
		else {
			detectPresence = "NO";
		}
		
		roomEnvironment newRoomInfo = new roomEnvironment(room,  newLight, newTemp, "CLOSED", "OPEN",detectPresence,"ON","OFF");
		
		GEIroomList.addRoomToList(newRoomInfo);*/
		
		String createLightUrl = "http://localhost:8081/createLightData";
		//String lightSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSensor/DATA";
		String lightSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSensor/DATA";
		
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    String auth = "admin:admin";
	    headers.set( "Authorization", auth);
	    // ty=4
	    //X-M2M-Origin: admin:admin
	    
	    try{
	    	JSONObject lightJsonObject = new JSONObject();

			lightJsonObject.put("Value", light);
			lightJsonObject.put("unit", "Lux");
		    lightJsonObject.put("Room", room);
		    
		    
		    HttpEntity<String> request = new HttpEntity<String>(lightJsonObject.toString(), headers);
		    //HttpEntity<LightDataAcquisition> request = new HttpEntity<LightDataAcquisition>(lightJsonObject, headers);
		    LightDataAcquisition lightData = restTemplate.postForObject(lightSensorUrl, request, LightDataAcquisition.class);
 
	    
	    tempDataAcquisition tempData = new tempDataAcquisition(25,"degC","GEI-213");
	    roomEnvironment newRoomInfo = new roomEnvironment(room,  lightData, tempData, "CLOSED", "OPEN","YES","ON","OFF");
		
		GEIroomList.addRoomToList(newRoomInfo);		
		
	    } catch(JSONException jex){
	    	System.out.println(jex.toString());
	    }
	    
	}
	
	
	

}