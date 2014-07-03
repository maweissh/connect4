/**
 * The Connect 4 javascript
 */
var player = $("#player").value();

/*
 * Play Screen
 */
function wurf_in(s) {
	if($("a").css("cursor") == "not-allowed"){
			//do nothing
	} else {
		click(s - 1);
	}

};

function click(s) {
	webSocket.send(s);	
};

function receiveEvent(event) {
	var json = JSON.parse(event.data);
	var callbackPlayer = json.player;
	var row = "f" +json.column +json.row;
	var victory = json.victory;
	
	if(json.alone =="true"){
		disableMouseCursor();
	}else{
		enableMouseCursor();
	}
	
	if (json.playerLeft == "true") {
		if (confirm("A player has left the game. Wanna stay in?")) {
			
		}else{
			history.back();
		}
		
	}
	
	if(victory==1 || victory == 2){
		if(confirm("Player "+callbackPlayer +" won." +"\n" +"Wanna play again?")){
			location.reload();
		}else{
			history.back();
		}
	}
		
	// at the beginning of the game, set the cursor for player 2 to "not-allowed"	
	// read the number of the player
	if(player == "Player 2"){
		$("a").css("cursor", "not-allowed");
		$("#table_play").css("cursor", "not-allowed");
	}
	
	// change the picture where the player had clicked
	if (callbackPlayer == "eins") {
		document.getElementById(row).childNodes[0].src = '/assets/images/vggelb.gif';
	}else{
		document.getElementById(row).childNodes[0].src = '/assets/images/vgrot.gif';
	}	
	blurEffect(callbackPlayer);
};

function hover(element, row){
	document.getElementsByName(element)[row].src = '/assets/images/choice.gif';
	document.getElementsByName(element)[row].style.opacity = "1.0";	
};

function unhover(element, row){
	document.getElementsByName(element)[row].src = '/assets/images/choice_stabil.gif';
	document.getElementsByName(element)[row].style.opacity = "0.5";
};

function blurEffect(text){
	var color = document.getElementById("play_body_left").style.backgroundColor;

	switch (text){
	case "eins" :
		if (color == "yellow"){
			disableMouseCursor();
			$("#play_body_left").css("box-shadow", "0px 0px 5px 5px white");
			$("#play_body_right").css("box-shadow", "0px 0px 5px 5px black");
		} else {
			enableMouseCursor();
			$("#play_body_left").css("box-shadow", "0px 0px 5px 5px black");
			$("#play_body_right").css("box-shadow", "0px 0px 5px 5px white");
		}
		break;
	case "zwei" :
		if (color == "yellow"){
			enableMouseCursor();
			$("#play_body_left").css("box-shadow", "0px 0px 5px 5px black");
			$("#play_body_right").css("box-shadow", "0px 0px 5px 5px white");
		} else {
			disableMouseCursor();
			$("#play_body_left").css("box-shadow", "0px 0px 5px 5px white");
			$("#play_body_right").css("box-shadow", "0px 0px 5px 5px black");
		}
		break;
	}
};


function doDivColor(gamer){
	//Dynamisches einfärben der player divs					
	if(gamer == "Player 1"){
		$("#play_body_left").css("background-color", "yellow");
		$("#play_body_left").css("box-shadow", "0px 0px 5px 5px black");
		$("#play_body_right").css("background-color", "red");
	}else{
		$("#play_body_left").css("background-color", "red");
		$("#play_body_right").css("background-color", "yellow");
		$("#play_body_right").css("box-shadow", "0px 0px 5px 5px black");
	} 	
};

function disableMouseCursor(){
	$('#table_play').css('cursor', 'not-allowed');
	$('a').css('cursor', 'not-allowed');
};
function enableMouseCursor(){
	$('#table_play').css('cursor', 'default');
	$('a').css('cursor', 'default');
};


