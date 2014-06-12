package controllers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ArrayNode;

import play.Logger;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class Application extends Controller {

	private static Map<Integer, WebSocket.Out<String>> playerList = new HashMap<Integer, WebSocket.Out<String>>();
	private static Map<String, String> users = new HashMap<String, String>();
	
	
	public static Result init() {
		
		// before the PlayScreen
		if (!users.containsValue(request().getQueryString("username"))) {
			return ok(views.html.login.render());
		} else {
			return ok(views.html.index.render(session().get("username")));
		}
	}

	public static Result saveLogin() {
		if(users.size() >= 2){
			return badRequest("Sorry alles voll");
		}
		if(!users.containsValue(request().getQueryString("username"))){
			if (users.isEmpty()){
				session().put("username", request().getQueryString("username"));
				users.put(session().get("username"), "player1");
				System.out.println(users.size());
				System.out.println("-----");
				System.out.println(users.get(session().get("username")));
				
				return ok(views.html.index.render(users.get(session().get("username"))));
			} else {
				session().put("username", request().getQueryString("username"));
				users.put(session().get("username"), "player2");
				return ok(views.html.index.render(users.get(session().get("username"))));
			}	
		} 
		
		//session().put("username", request().getQueryString("username"));
		return badRequest("Sorry Name is not available.");
	}

	public static Result doLogout() {
		System.out.println("DoLogout " + users.get(session().get("username")));
		users.remove(session().get("username"));
		session().clear();
		return ok(views.html.logout.render());
	}

	public static Result playScreen() {
		if(!session().containsKey("username")){
			return ok(views.html.login.render());
		} else {
			return ok(views.html.playScreen.render(session().get("username"), users.get(session().get("username"))));
		}
					
	}

	public static WebSocket<String> guess() {
		return new WebSocket<String>() {
			public void onReady(WebSocket.In<String> in,
					final WebSocket.Out<String> out) {
				in.onMessage(new Callback<String>() {
					public void invoke(String g) {						
						if(playerList.isEmpty()){
							playerList.put(1, out);
							System.out.println(users.get("player1"));
						} else {
							playerList.put(2, out);
							System.out.println(users.get("player2"));
						}
						for(WebSocket.Out<String> channel : playerList.values()){
							channel.write(g);
						}
					}
				});
				in.onClose(new Callback0(){
					public void invoke() {
						out.close();
						System.out.println("Disconnected!");
					}
				});
			}
		};
	}
}