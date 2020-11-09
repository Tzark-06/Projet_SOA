package fr.insa.mas.temperatureManagementMS.model;

public class tempDataAcquisition {

	private double value;
	private String unit;
	private String roomName;
	
	public tempDataAcquisition() {};
	
	public tempDataAcquisition(double value, String unit, String roomName) 
	{
		super();
		this.value = value;
		this.unit = unit;
		this.roomName = roomName;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
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
	};
	
	
	
}
