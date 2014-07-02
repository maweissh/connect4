/**
 * Script for the main_view
 */
function answerAjax(answer){
	if(answer=="yes"){
		$('#lets_play').prop('disabled', false);
		$('#ajax_answer').html('A second player is logged in.');		
	}else{
		$('#lets_play').prop('disabled', true);
		$('#ajax_answer').html('No other player is actually logged in.');    								        		   								        		
	}
}
