package fr.insa.soa.RoomManagementWS.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class HTTPMethodsRoomManagement {
	
	
	public HTTPMethodsRoomManagement(){};
	
	//For HTTP GET Methods 
	
	/* Enter your code here...*/
	public roomEnvironment getDataFromOM2M(String myUrl, String roomName) throws IOException, JSONException{
		
		//Initialisation
		roomEnvironment room = new roomEnvironment(roomName, new LightDataAcquisition(0, "lux", roomName),
				new tempDataAcquisition(0, "degC", roomName), "CLOSED", "CLOSED", "YES", "ON", "OFF");
		
		String[] parameters = {"/LightSensor/DATA","/TemperatureSensor/DATA","/PresenceSensor/DATA", "Alarm/STATE", "DoorActuator/STATE", "LightSwitchActuator/STATE", "WindowActuator/STATE"}; 
		
		for(int i = 0; i < parameters.length; i++) {
			String newUrl = myUrl + parameters[i] + "/la";
			URL url = new URL (newUrl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);	
			con.setRequestProperty("X-M2M-Origin", "admin:admin");
			con.setRequestProperty("Accept", "application/json");
				
			int responseCode = con.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				in.close();
				
				
				JSONObject obj = new JSONObject(response.toString());
				String dataValue = obj.getJSONObject("m2m:cin").getString("con");
				
				switch(i) {
					case 0:
						int lightValue = Integer.parseInt(dataValue);
						LightDataAcquisition light = new LightDataAcquisition(lightValue, "lux", roomName);
						room.setLight(light);
						break;
					case 1:
						double tempValue = Double.parseDouble(dataValue);
						tempDataAcquisition temp = new tempDataAcquisition(tempValue, "deg", roomName);
						room.setTemp(temp);
						break;
					case 2:
						room.setPresence(dataValue);
						break;
					case 3:
						room.setAlarmState(dataValue);
						break;
					case 4:
						room.setDoorsState(dataValue);
						break;
					case 5:
						room.setSwitchLight(dataValue);
						break;
					case 6:
						room.setWindowsState(dataValue);
						break;
				}
				
			}
				
		}

		return room;
				
	}
	
	
	//For HTTP POST Methods 
	public void sendDataToOM2M(String myUrl, String dataType, String dataValue) throws IOException, JSONException {
		
		URL url = new URL (myUrl);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; ty=4");
		con.setRequestProperty("X-M2M-Origin", "admin:admin");
		con.setRequestProperty("Accept", "application/json");
		
		con.setDoOutput(true);			
			
		JSONObject jsonInput= new JSONObject("{\"m2m:cin\": {\"cnf\": \"" + dataType +  "\", \"con\": \"" + dataValue + "\"} }");
		String jsonInputString = jsonInput.toString();
				
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
