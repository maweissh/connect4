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
	webSocket.onmessage = receiveEvent;
	webSocket.send(s);

	// var requestObj = new XMLHttpRequest();
	// requestObj.onreadystatechange = function(){
	// if(requestObj.readyState == 4 && requestObj.status == 200){
	// document.getElementById("f"+s)
	// }
	// }
	//	
	//	
	// var table = document.getElementById("table_play");
	// var row = table.rows[s];
	// for (var j = 0; row.cells[j]; j++){
	// if(s < 1){
	// alert("if");
	// document.getElementById("f"+s+j).childNodes[0].src =
	// '/assets/images/vggelb.gif';
	// s++;
	// }
	// }
};

function receiveEvent(event) {
	$("#output").html(event.data);
	var row = "f" + event.data.charAt(1) + "5";
		
	if (event.data.charAt(0)=="e") {
		document.getElementById(row).childNodes[0].src = '/assets/images/vggelb.gif';
	}else{
		document.getElementById(row).childNodes[0].src = '/assets/images/vgrot.gif';
	}	
	
};

