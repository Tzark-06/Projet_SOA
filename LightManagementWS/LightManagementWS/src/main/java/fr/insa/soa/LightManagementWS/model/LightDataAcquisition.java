package fr.insa.soa.LightManagementWS.model;



public class LightDataAcquisition {
	
	private int value;
	private String unit;
	private String roomName;
	
	public LightDataAcquisition(){};
	
	public LightDataAcquisition(int value, String unit, String roomName) {
		super();
		this.value = value;
		this.unit = unit;
		this.roomName = roomName;
	}
	
	public LightDataAcquisition saveLightDataAcquisition(LightDataAcquisition newData){
		LightDataAcquisition newLight = new LightDataAcquisition(newData.getValue(), newData.getUnit(), newData.getRoomName());
		return newLight;
	}

	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}
