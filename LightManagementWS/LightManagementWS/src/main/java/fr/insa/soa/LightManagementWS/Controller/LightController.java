package fr.insa.soa.LightManagementWS.Controller;


import fr.insa.soa.LightManagementWS.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
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
	
	
	/*@PostMapping("/LightAcquisition/{room}/{value}/{unit}")
	public void addLightAcq(@PathVariable String room, @PathVariable int value, @PathVariable String unit){
		LightRoomAcquisitionList.addLightData(value, unit, room);
	}*/
	
	@PostMapping(value = "/createLightData", consumes = "application/json", produces = "application/json")
	public LightDataAcquisition createLightData(@RequestBody LightDataAcquisition lightData) {
	    LightRoomAcquisitionList.addNewLightData(lightData);
	    return lightData;
	}
	
	@PostMapping("/LightAcquisition/{room}/{value}/{unit}")
	public void addLightAcq(@PathVariable String room, @PathVariable int value, @PathVariable String unit) throws IOException, JSONException{

		URL url = new URL ("http://localhost:8282/~/mn-cse/mn-name/LightSensor/DATA");
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; ty=4");
		con.setRequestProperty("X-M2M-Origin", "admin:admin");
		con.setRequestProperty("Accept", "application/json");
		
		con.setDoOutput(true);			
			
		JSONObject jsonInput= new JSONObject("{\"m2m:cin\": {\"cnf\": \"int\", \"con\": \"" + 150 + "\"} }");
		String jsonInputString = jsonInput.toString();
		System.out.println("JSON obj = " + jsonInputString);
		
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInputString.getBytes("utf-8");
		    os.write(input, 0, input.length);			
		}
		
		int HttpResult = con.getResponseCode(); 
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		    StringBuilder response = new StringBuilder();
		    String responseLine = null;
		    while ((responseLine = br.readLine()) != null) {
		        response.append(responseLine.trim());
		    }
		    System.out.println(response.toString());
		}else {
		    System.out.println(con.getResponseMessage());
		}
		
		
	}
	
		
	
	
}

