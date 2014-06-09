/**
 * The Connect 4 javascript
 */

var webSocket;

			$(function(){
				var WS = window["MozWebSocket"] ? MozWebSocket : WebSocket;
				webSocket = new WS("@routes.Application.guess().webSocketURL(request)");
				
				webSocket.onmessage = receiveEvent;
				
			});


			function sendGuess(){
				webSocket.onmessage = receiveEvent;
				var g = $("#guess").val();
				webSocket.send(g);
			};
			
			function receiveEvent(event){
				$("#output").html(event.data);
			};


/*
 * Play Screen 
 */
function wurf_in(s) { click(s-1);}

function click(s){
		
		webSocket.send(s);
//	var requestObj = new XMLHttpRequest();
//	requestObj.onreadystatechange = function(){
//		if(requestObj.readyState == 4 && requestObj.status == 200){
//			document.getElementById("f"+s)
//		}
//	}
//	
//	
//	var table = document.getElementById("table_play");
//	var row = table.rows[s];
//	for (var j = 0; row.cells[j]; j++){
//			if(s < 1){
//				alert("if");
//				document.getElementById("f"+s+j).childNodes[0].src = '/assets/images/vggelb.gif';
//				s++;
//			} 
//		}
};

function receiveEvent(event){
	alert("receive");
	$("#output").html(event.data);
};
