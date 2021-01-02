package fr.insa.soa.RoomManagementWS.Controller;

import fr.insa.soa.RoomManagementWS.model.*;

import java.io.IOException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import fr.insa.soa.RoomManagementWS.model.*;
import org.json.JSONException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
public class RoomListResource {
	
	//Sensors and actuators addresses inside the OM2M platform
	private String lightSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSensor/DATA";
	private String tempSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/TemperatureSensor/DATA";
	private String presenceSensorUrl = "http://localhost:8282/~/mn-cse/mn-name/PresenceSensor/DATA";
	private String switchLightUrl = "http://localhost:8282/~/mn-cse/mn-name/LightSwitchActuator/STATE";
	private String windowActuatorUrl = "http://localhost:8282/~/mn-cse/mn-name/windowActuator/STATE";
	private String doorActuatorUrl = "http://localhost:8282/~/mn-cse/mn-name/DoorActuator/STATE";
	private String alarmUrl = "http://localhost:8282/~/mn-cse/mn-name/Alarm/STATE";
	
	private String roomEnvironmentUrl = "http://localhost:8282/~/mn-cse/mn-name";
	
	private HTTPMethodsRoomManagement httpController = new HTTPMethodsRoomManagement();
	
	@GetMapping ("/getRoomInfo/{room}/{scenario}")
	public roomEnvironment changeEnvironment(@PathVariable String room, @PathVariable int scenario) throws IOException, JSONException {
		roomEnvironment roomObj = new roomEnvironment();
		roomObj = httpController.getDataFromOM2M(roomEnvironmentUrl, room);
				
		roomObj.setRoomName(room);
		
		//Scenario 1 :
		if(scenario == 1) {
			if(roomObj.getTemp().getValue() > 25 && roomObj.getWindowsState() == "CLOSED"){
			  roomObj.setWindowsState("OPEN");
			  httpController.sendDataToOM2M(windowActuatorUrl, "", "OPEN");
			 } else if(roomObj.getTemp().getValue() < 18 && roomObj.getWindowsState() == "OPEN"){
			  roomObj.setWindowsState("CLOSED");
			  httpController.sendDataToOM2M(windowActuatorUrl, "", "CLOSED");
			 }

			if(roomObj.getPresence() == "YES") {
				roomObj.setSwitchLight("ON");
				httpController.sendDataToOM2M(switchLightUrl, "", "ON");
			}else if(roomObj.getPresence() == "NO") {
				roomObj.setSwitchLight("OFF");
				httpController.sendDataToOM2M(switchLightUrl, "", "OFF");
			}
		
		}
		
		/******************************************************************************************************************************
		 * Scenario 2 :	
		 * After 7 p.m., if the motion sensor indicates the presence of a person inside the room and the windows and doors are closed,
		 *  then the alarm goes off and the police are notified directly.
		 ******************************************************************************************************************************/
		else if(scenario == 2){
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
			Date myDate = new Date();
			String date_str = simpleDateFormat.format(myDate); // convert date to string 
			 
			System.out.println(date_str);
			
			if(date_str.contains(" 19:")){
			/*We can suggest that a person can be inside because:
				- we forgot him (he was sleeping...)
				- a thief went through a vent
			*/
				if(roomObj.getPresence() == "YES" && roomObj.getWindowsState() == "CLOSED" && roomObj.getDoorsState() == "CLOSED") {
					roomObj.setAlarmState("ON");
				}

			}
		}
		
		return roomObj;
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
