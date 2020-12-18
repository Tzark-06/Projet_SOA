package fr.insa.soa.RoomManagementWS.Controller;

import fr.insa.soa.RoomManagementWS.model.*;

import java.awt.PageAttributes.OriginType;
import java.io.IOException;
import java.net.URL;
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
	
	private String lightSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSensor/DATA";
	private String tempSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/TemperatureSensor/DATA";
	private String presenceSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/PresenceSensor/DATA";
	private String switchLightUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSwitchActuator/DATA";
	private String windowActuatorUrl = "http://localhost:8282/~/mn-cse/mn-name/windowActuator/DATA";
	
	private HTTPMethodsRoomManagement httpController = new HTTPMethodsRoomManagement();
	
	@GetMapping ("/getRoomInfo/{room}/{scenario}")
	public void changeEnvironment(@PathVariable String room, @PathVariable int scenario)throws IOException, JSONException {
		roomEnvironment roomObj = new roomEnvironment();
		room = httpController.getDataFromOM2M(roomEnvironmentUrl, room);
		
		//Scenario 1 :
		if(scenario == 1) {
			if(roomObj.getTemp() > 25 && roomObj.getWindowsState() == "CLOSED"){
			  roomObj.setWindowState("OPEN");
			  httpController.sendDataToOM2M(windowActuatorUrl, "", "OPEN");
			 } else if(roomObj.getTemp < 18 && roomObj.getWindowsState() == "OPEN"){
			  roomObj.setWindowState("CLOSED");
			  httpController.sendDataToOM2M(windowActuatorUrl, "", "CLOSED");
			 }

			if(roomObj.getPresence == "YES") {
				roomObj.setSwitchLight("ON");
				httpController.sendDataToOM2M(switchLightUrl, "", "ON");
			}else if(roomObj.getPresence == "NO") {
				roomObj.setSwitchLight("OFF");
				httpController.sendDataToOM2M(switchLightUrl, "", "OFF");
			}
			
		}else if(scenario == 2){
			
			if(roomObj.getPresence == "NO" && roomObj.getWindowsState() == "OPEN") {
				roomObj.setWindowState("CLOSED");
				httpController.sendDataToOM2M(windowActuatorUrl, "", "CLOSED");
			}
			
			
			
		}
		
    }
    
	
	
	@PostMapping("/updateRoomInfo/{room}/{light}/{temp}/{presence}")
	public void updateRoomInfo(@PathVariable String room, @PathVariable int light, @PathVariable double temp, @PathVariable int presence) throws IOException, JSONException {
		
		httpController.sendDataToOM2M(lightSensorUrl, "integer", Integer.toString(light));
		httpController.sendDataToOM2M(tempSensorUrl, "double", Double.toString(temp));
		
		String presenceString = "";
		if(presence == 1)  presenceString = "YES";
		else presenceString = "NO";
		
		httpController.sendDataToOM2M(presenceSensorUrl, " ", presenceString);
		
	}
	
	

}
