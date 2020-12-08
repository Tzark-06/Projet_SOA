/***************************************************************/
/*********** Display of the room data (GET request) ************/
/***************************************************************/

var baseurl = "http://localhost:8090";

      function loadSensorDataRoom(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET",baseurl + "/CurrentRoomInfo/GEI-213",true);
        xmlhttp.onreadystatechange = function() {
          if(xmlhttp.readyState === 4 && xmlhttp.status === 200){
            var roomInfo = JSON.parse(xmlhttp.responseText);
            
            var lightInfoStr = JSON.stringify(roomInfo.light);
            var lightInfo = JSON.parse(lightInfoStr);
            var tempInfoStr = JSON.stringify(roomInfo.temp);
            var tempInfo = JSON.parse(tempInfoStr);

            var roomName = "GEI Room id: " + roomInfo.roomName;        
            var stateLightButton = "State of the light inside the room: " + roomInfo.switchLight;
            var roomLight = "Current Light stream inside the room: " + lightInfo.value + " " + lightInfo.unit;
            var roomTemp = "Current temperature inside the room: " + tempInfo.value + " " + tempInfo.unit;
            var roomWindows = "Current state of the windows: " + roomInfo.windowsState;
            var roomDoor = "Current state of the door(s): " + roomInfo.doorsState;
            var presence = "Presence of people inside the room: " + roomInfo.presence;
            var alarm = "State alarm the room: " + roomName.alarmState;
           
            var main =  roomName + `<br/>` + `<br/>` + stateLightButton + `<br/>` + roomLight + `<br/>` + roomTemp + `<br/>` + roomWindows + `<br/>` + roomDoor + `<br/>` 
            + presence + `<br/>` + alarm +  `<br/>`;
            
            document.getElementById("currentRoomInfo").innerHTML = main;
          }
        };
        xmlhttp.send();
      }
      window.onload = function(){
        loadSensorDataRoom();
      }