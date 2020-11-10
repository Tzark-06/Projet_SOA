package fr.insa.soa.RoomManagementWS.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import fr.insa.soa.RoomManagementWS.model.*;

@CrossOrigin
@RestController
public class RoomListResource {
	
	roomList GEIroomList = new roomList();
	
	@GetMapping("/rooms")
	public ArrayList<String> getRooms(){
		return GEIroomList.getRoomList();
	}
	
	/*@PostMapping("/addRoom/{room}/{light}/{temp}/{presence}")
	public void addRoomList(@PathVariable String room){
		GEIroomList.addRoomToList(room);
	}
	
	
	@PostMapping("/updateRoomInfo/{room}")
	public void addRoomList(@PathVariable String room){
		GEIroomList.addRoomToList(room);
	}*/
	

}
