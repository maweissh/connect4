/**
 * The Connect 4 javascript
 */

/*
 * Play Screen
 */
function wurf_in(s) {
	click(s - 1);
}

function click(s) {
	//webSocket.onmessage = receiveEvent;
	webSocket.send(s);
	
};

function receiveEvent(event) {
	$("#output").html(event.data);
	var row = "f" + event.data.charAt(1) + event.data.charAt(2);
		
	if (event.data.charAt(0)=="e") {
		document.getElementById(row).childNodes[0].src = '/assets/images/vggelb.gif';
	}else{
		document.getElementById(row).childNodes[0].src = '/assets/images/vgrot.gif';
	}	
	
};

