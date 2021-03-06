package fr.insa.soa.RoomManagementWS.model;


public class roomEnvironment {
	
	private String roomName;
	private LightDataAcquisition light;
	private tempDataAcquisition temp;
	private String windowsState;	// 2 values: "OPEN", "CLOSED"
	private String doorsState;		// 2 values: "OPEN", "CLOSED"
	private String presence;		//if there is at least one person inside : "YES", else "NO"
	private String switchLight; 	//2 values: "ON", "OFF"
	private String alarmState;
	
	public roomEnvironment(){
		super();
	}
	
	public roomEnvironment(String roomName, LightDataAcquisition light, tempDataAcquisition temp, String windowsState, 
			String doorsState, String presence, String switchLight, String alarmState) {
		super();
		this.roomName = roomName;
		this.light = light;
		this.temp = temp;
		this.windowsState = windowsState;
		this.doorsState = doorsState;
		this.presence = presence;
		this.switchLight = switchLight;
		this.alarmState = alarmState;
	}
	
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public LightDataAcquisition getLight() {
		return light;
	}

	public void setLight(LightDataAcquisition light) {
		this.light = light;
	}

	public tempDataAcquisition getTemp() {
		return temp;
	}

	public void setTemp(tempDataAcquisition temp) {
		this.temp = temp;
	}

	public String getWindowsState() {
		return windowsState;
	}

	public void setWindowsState(String windowsState) {
		this.windowsState = windowsState;
	}

	public String getDoorsState() {
		return doorsState;
	}

	public void setDoorsState(String doorsState) {
		this.doorsState = doorsState;
	}

	public String getPresence() {
		return presence;
	}

	public void setPresence(String presence) {
		this.presence = presence;
	}
	
	public String getSwitchLight() {
		return switchLight;
	}

	public void setSwitchLight(String switchLight) {
		this.switchLight = switchLight;
	}


	public String getAlarmState() {
		return alarmState;
	}


	public void setAlarmState(String alarmState) {
		this.alarmState = alarmState;
	}

	
}
