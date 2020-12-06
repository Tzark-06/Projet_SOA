/*Button used to call the POST request*/

//var btn = document.querySelector("div.POSTrequest input[name='btn]");
var btn = document.querySelector('input');
//var txt = document.querySelector("p.txtbtn");

btn.addEventListener('click', sendSensorDataRoom);

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

document.getElementById("txtbtn").innerHTML = "coucou";

function sendSensorDataRoom(){

    var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.open("POST",baseurl + "GEI-213/" + light + "/" + temperature + "/" + pres,true);
    //console.log(xmlhttp.responseText);

    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState === XMLHttpRequest.DONE && xmlhttp.status === 200){ 
            document.getElementById("txtbtn").innerHTML = 'Data sensors of the room are sent!';            
        } else {
            document.getElementById("txtbtn").innerHTML = 'An error occured!';  
        }
    };
    xmlhttp.send();
}

//btn.addEventListener('click', sendSensorDataRoom);
/*window.onload = function(){
sendSensorDataRoom();
}*/

