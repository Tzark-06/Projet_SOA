package fr.insa.mas.windowManagementMS.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.mas.windowManagementMS.model.*;

@CrossOrigin
@RestController
public class windowController {
	
	private windowDB windowList = new windowDB();
	
	@GetMapping(value="/CurrentWindowState/{room}")
	public windowModel currentWindowState(@PathVariable String room) {
		return windowList.getWindowState(room);
	}
	@GetMapping(value="/windowList")
	public windowDB windowList() {
		return windowList;
	}
	
	@PostMapping("/windowState/{room}/{state}")
	public void changeWindowState(@PathVariable String room, @PathVariable int state) {
		windowList.changeState(state, room);
	}
}
