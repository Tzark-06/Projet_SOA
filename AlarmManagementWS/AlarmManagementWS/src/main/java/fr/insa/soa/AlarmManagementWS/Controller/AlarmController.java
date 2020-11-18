package fr.insa.soa.AlarmManagementWS.Controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.AlarmManagementWS.model.*;

@CrossOrigin
@RestController
public class AlarmController {
	
	private AlarmDataList alarmRoomStateList = new AlarmDataList();
	
	
	@GetMapping(value="/CurrentAlarmState/{room}")
	public AlarmAcquisition currentAlarmState(@PathVariable String room){
		return alarmRoomStateList.getAlarmState(room);
	}
	
	@GetMapping(value="/alarmStateListRoom/{room}")
	public ArrayList<AlarmAcquisition> alarmStateListRoom(@PathVariable String room){
		
		ArrayList<AlarmAcquisition> alarmStateListOfTheRoom = new ArrayList<AlarmAcquisition>();
		
		for(int i = 0; i < alarmRoomStateList.getAlarmStateList().size(); i++){

			if(alarmRoomStateList.getAlarmStateList().get(i).getRoom().equals(room)){
				alarmStateListOfTheRoom.add(alarmRoomStateList.getAlarmStateList().get(i));
			}
		}	
		return alarmStateListOfTheRoom;
	}
	
	
	@GetMapping(value="/alarmStateList")
	public AlarmDataList lightList(){
		return alarmRoomStateList;
	}
	
	
	@PostMapping("/alarmStateAcquisition/{room}/{state}")
	public void addLightAcq(@PathVariable String room, @PathVariable String state){
		alarmRoomStateList.addAlarmData(room, state);
	}
	
	
	
}