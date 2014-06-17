/**
 * The Connect 4 javascript
 */
/*
 * Play Screen
 */
function wurf_in(s) {
	click(s - 1);
	blurEffect();
};

function click(s) {
	//webSocket.onmessage = receiveEvent;
	webSocket.send(s);
	
};

function receiveEvent(event) {
	$("#output").html(event.data);
	var text = event.data;
	var values = text.split(",");
	var row = "f" + values[2] + values[3];
		
	if (values[1] == "eins") {
		document.getElementById(row).childNodes[0].src = '/assets/images/vggelb.gif';
	}else{
		document.getElementById(row).childNodes[0].src = '/assets/images/vgrot.gif';
	}	
	
};

function hover(element, row){
	document.getElementsByName(element)[row].src = '/assets/images/choice.gif';
	document.getElementsByName(element)[row].style.opacity = "1.0";	
};

function unhover(element, row){
	document.getElementsByName(element)[row].src = '/assets/images/choice_stabil.gif';
	document.getElementsByName(element)[row].style.opacity = "0.5";
};

function blurEffect(){	
	if(text.contains("PLAYER2")){
		$("#play_body_left").css("box-shadow", "0px 0px 5px 5px yellow");
		$("#play_body_right").css("box-shadow", "0px 0px 5px 5px white");
	} else if(text.contains("PLAYER1")) {
		$("#play_body_left").css("box-shadow", "0px 0px 5px 5px white");
		$("#play_body_right").css("box-shadow", "0px 0px 5px 5px red");
	} else {
		$("#play_body_left").css("box-shadow", "0px 0px 0px 0px white");
	}
};


function doDivColor(gamer){
	//Dynamisches einfärben der player divs					
	if(gamer == "Player 1"){
		$("#play_body_left").css("background-color", "yellow");
		$("#play_body_left").css("box-shadow", "0px 0px 5px 5px yellow");
		$("#play_body_right").css("background-color", "red");
	}else{
		alert("in else" +gamer)
		$("#play_body_left").css("background-color", "red");
		$("#play_body_right").css("background-color", "yellow");
		$("#play_body_right").css("box-shadow", "0px 0px 5px 5px yellow");
	} 	
};

function disableMouseCursor(text){
	document.body.style.cursor = not-allowed;
};
function enableMouseCursor(){
	document.body.style.cursor = auto;
};
