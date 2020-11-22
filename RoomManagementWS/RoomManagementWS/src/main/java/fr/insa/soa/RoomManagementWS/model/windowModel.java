package fr.insa.soa.RoomManagementWS.model;

public class windowModel {
	private int state;
	private String room;
	
	public windowModel (){};
	
	public windowModel(int state, String room) {
		super();
		this.state=state;
		this.room=room;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
	
}
