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
