package fr.insa.mas.switchManagmentMS.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import fr.insa.mas.switchManagmentMS.model.switchDB;
import fr.insa.mas.switchManagmentMS.model.switchModel;


@CrossOrigin
@RestController
public class switchRessource {
	private switchDB switchList = new switchDB();
	
	@GetMapping(value="/CurrentSwitchState/{room}")
	public switchModel currentWindowState(@PathVariable String room) {
		return switchList.getswitchState(room);
	}
	@GetMapping(value="/switchList")
	public switchDB switchList() {
		return switchList;
	}
	
	@PostMapping("/switchState/{room}/{state}")
	public void changeSwitchState(@PathVariable String room, @PathVariable int state) {
		switchList.changeState(state, room);
	}
}
