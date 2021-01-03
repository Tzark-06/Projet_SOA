# Automatic GEI's rooms management 

---
## Team
Thomas Zennaro <br/>
Victor Le Roch <br/>
(Valentin Licini) 

---
## Description 
We develop a Web application (Proof-of-Concept) for managing  GEI's rooms. This application allows some actions inside a room without the intervention of a human. For instance, our application is able to open and close of doors, windows, turn on/off room lighting...
For that, we implement services for the different sensors and actuators. We retrieve data from sensors and analyze them to enable setting actuators. 

---
## Project Content 

### RoomManagementWS folder
- services models
- main controller which interact with OM2M (GET/POST requests) + scenarios implementation
- the main method of the application (RoomManagementWsApplication.java) 

### OM2M
- sensors (Temperature, Light, Presence)
- actuators (Alarm, windows, doors, light switch)
  
### Web interface
- dashboard for each scenario
- a button to generate POST requests to OM2M (to update the sensors)

## Application running 

### OM2M
- start the IN-CSE "start" located at .\OM2M_rooms\OM2M_rooms\in-cse
- Open the web page of OM2M : <br/>
  ¤ http://localhost:8080/webpage <br/>
  ¤ id = admin <br/>
  ¤ password = admin <br/>
- start the MN-CSE "start" located at .\OM2M_rooms\OM2M_rooms\mn-cse

### Application under Eclispe
- On the RoomManagementWS projet, run "RoomManagementWsApplication.java" located at 
  .\RoomManagementWS\RoomManagementWS\src\main\java\fr\insa\soa\RoomManagementWS
- Port: 8090
  
### Web interface
- execute the "mainPage.html" located at .\Interface web

