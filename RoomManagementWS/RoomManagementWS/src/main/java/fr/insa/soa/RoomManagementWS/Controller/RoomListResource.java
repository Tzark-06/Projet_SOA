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
	
	private HTTPMethodsRoomManagement httpController = new HTTPMethodsRoomManagement();
	
	
	
	
	@PostMapping("/updateRoomInfo/{room}/{light}/{temp}/{presence}")
	public void updateRoomInfo(@PathVariable String room, @PathVariable int light, @PathVariable double temp, @PathVariable int presence) throws IOException, JSONException {
		
		httpController.sendDataToOM2M(lightSensorUrl, "integer", Integer.toString(light));
		httpController.sendDataToOM2M(tempSensorUrl, "double", Double.toString(temp));
		
		String presenceString = "";
		if(presence == 1)  presenceString = "YES";
		else presenceString = "NO";
		
		httpController.sendDataToOM2M(presenceSensorUrl, " ", presenceString);
		
	}
	
	@PostMapping("/update")
	public void updateTest(){
		System.out.println("coucou");
	}

}
