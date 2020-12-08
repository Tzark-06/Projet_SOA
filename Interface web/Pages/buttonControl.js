/***************************************************************/
/************ Button used to call the POST request *************/
/***************************************************************/

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}
function getRandomDouble(max) {
    return Math.floor(Math.random() * Math.floor(max) * 1.00);
}

var light =  getRandomInt(301);          // 0 à 300 lux
var temperature = getRandomDouble(31);   // 0 à 30°C
var pres = getRandomInt(2);              // 0=NO ; 1=YES

var baseurl = "http://localhost:8090/updateRoomInfo/";

function sendSensorDataRoom(){

    var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.open("POST",baseurl + "GEI-213/" + light + "/" + temperature + "/" + pres,true);

    xmlhttp.setRequestHeader("Content-Type", "application/json");

    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200){ 
            document.getElementById("txtbtn").innerHTML = 'Data sensors of the room are sent!';            
        } else {
            document.getElementById("txtbtn").innerHTML = 'An error occured!';  
        }
    };
    xmlhttp.send();
}

