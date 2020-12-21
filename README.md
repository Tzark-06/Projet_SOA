### Automatic GEI's rooms management ###

## Project Content ##

# RoomManagementWS folder
- services models
- main controller which interact with OM2M (GET/POST requests) + scenarios implementation
- the main method of the application (RoomManagementWsApplication.java) 

# OM2M
- sensors (Temperature, Light, Presence)
- actuators (Alarm, windows, doors, light switch)
  
# Web interface
- dashboard for each scenario
- a button to generate POST requests to OM2M (to update the sensors)

## Application running ##

# OM2M
- start the IN-CSE "start" located at .\OM2M_rooms\OM2M_rooms\in-cse
- Open the web page of OM2M :
  ¤ http://localhost:8080/webpage
  ¤ id = admin
  ¤ password = admin
- start the MN-CSE "start" located at .\OM2M_rooms\OM2M_rooms\mn-cse

# Application under Eclispe
- On the RoomManagementWS projet, run "RoomManagementWsApplication.java" located at 
  .\RoomManagementWS\RoomManagementWS\src\main\java\fr\insa\soa\RoomManagementWS
- Port: 8090
  
# Web interface
- execute the "mainPage.html" located at .\Interface web

