package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import models.*;

public class Application extends Controller {

	private static Map<String, WebSocket.Out<String>> playerList = new HashMap<String, WebSocket.Out<String>>();
	private static TreeMap<String, String> users = new TreeMap<String, String>();
		
	public static Result init() {
		// before the PlayScreen
		if (!session().containsKey("username")) {
			return ok(views.html.login.render());
		} else {
			return ok(views.html.index.render(session().get("username")));
		}
	}

	public static Result saveLogin() {
		
		if(users.size() >= 2){
			return badRequest(views.html.error.render("Sorry, but there is already a Game running..."));
		}
		if(!users.containsKey(request().getQueryString("username"))){
			if (users.isEmpty()){
				session().put("username", request().getQueryString("username"));
				users.put(session().get("username"), "Player 1");
				
				return ok(views.html.index.render(session().get("username")));
			} else {
				session().put("username", request().getQueryString("username"));
				users.put(session().get("username"), "Player 2");
				return ok(views.html.index.render(session().get("username")));
			}	
		} 
		return badRequest(views.html.error.render("The Nickname '" + request().getQueryString("username") + "' is not available. Please choose a different one."));
	}

	public static Result doLogout() {
		users.remove(session().get("username"));
		session().remove("username");
		return ok(views.html.logout.render());
	}

	public static Result playScreen() {
		if(!session().containsKey("username")){
			return ok(views.html.login.render());
		} else {
			String otherplayer;
			String player2;
			if (users.firstEntry().getKey().equals(session().get("username"))) {
				otherplayer = users.lastEntry().getKey();
				player2 = users.lastEntry().getValue();
			}else{
				otherplayer = users.firstEntry().getKey();
				player2 = users.firstEntry().getValue();
			}
			return ok(views.html.playScreen.render(session().get("username"), users.get(session().get("username")), otherplayer, player2));
		}					
	}

	public static WebSocket<String> playNow(final String username) {
		return new WebSocket<String>() {
			public void onReady(final WebSocket.In<String> in,
					final WebSocket.Out<String> out) {
				in.onMessage(new Callback<String>() {
					public void invoke(String column) {
						String player;
						if (username.equals("Player 1")) {
							player = "eins";
						}else{
							player = "zwei";
						}
						
						// init a webSocket Connection at the start of the game
						if(column.equals("init")){
							String alone = "false";
							if (playerList.isEmpty()) {
								playerList.put(in.toString(), out);
								alone= "true";
							}else if(!playerList.containsKey(in.toString())){
								playerList.put(in.toString(), out);
							}
							// return the position of the gamer to control the blur effect of the div at the start
							for(WebSocket.Out<String> channel : playerList.values()){
								ObjectNode event = play.libs.Json.newObject();
								event.put("player", username.toUpperCase().toString());
								event.put("alone", alone);
								channel.write(event.toString());								
							}
						} else {
							String row = Connect4Logic.addChip(column, player);
							int victory = Connect4Logic.checkVictory(column, row, player);							
							for(WebSocket.Out<String> channel : playerList.values()){
								// return the gamer which had clicked on the playfield and where
								ObjectNode event = play.libs.Json.newObject();
								event.put("player", player);
								event.put("column", column);
								event.put("row", row);
								event.put("victory", victory);
		
								channel.write(event.toString());
							}
						}
					}	
				});
				in.onClose(new Callback0(){
					public void invoke() {
						Connect4Logic.clearArray();
						ObjectNode event = play.libs.Json.newObject();
						event.put("playerLeft", "true");	
						
						for(WebSocket.Out<String> channel : playerList.values()){												
							channel.write(event.toString());
						}						
						playerList.clear();						
						out.close();
					}
				});
			}
		};
	}
	
	public static Result otherPlayer(){
		if (users.size() <2) {
			return ok("no");
		}else{
			return ok("yes");
		}
	}
}