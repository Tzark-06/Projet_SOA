package fr.insa.soa.RoomManagementWS.model;


public class roomEnvironment {
	
	private String roomName;
	private int lightValue;
	private int tempValue;
	private String windowsState;	// 2 values: "OPEN", "CLOSED"
	private String doorsState;		// 2 values: "OPEN", "CLOSED"
	private String presence;		//if there is at least one person inside : "YES", else "NO"
	
	public roomEnvironment(String roomName, int lightValue, int tempValue, String windowsState, String doorsState, String presence) {
		super();
		this.roomName = roomName;
		this.lightValue = lightValue;
		this.tempValue = tempValue;
		this.windowsState = windowsState;
		this.doorsState = doorsState;
		this.presence = presence;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public int getLightValue() {
		return lightValue;
	}

	public void setLightValue(int lightValue) {
		this.lightValue = lightValue;
	}

	public int getTempValue() {
		return tempValue;
	}

	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
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
	
	

	
}
