/**
 * Script for the main_view
 */

function answerAjax(answer){
	if(answer=="yes"){
		$('#ajax_answer').html('A second player is logged in.');		
	}else{
		$('#ajax_answer').html('No other player is actually logged in.');    								        		   								        		
	}
}