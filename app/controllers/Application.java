package controllers;

import java.util.HashMap;

import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;

public class Application extends Controller {

	private static HashMap<Integer, String> playerList = new HashMap<Integer, String>();

	public static Result init() {

		if (!session().containsKey("username")) {
			return ok(views.html.login.render());
		} else {
			return ok(views.html.index.render(session().get("username")));
		}
	}

	public static Result saveLogin() {
		session().put("username", request().getQueryString("username"));
		if (session().size() <= 2) {
			playerList.put(1, request().getQueryString("username"));
		}
		return redirect("/");
	}

	public static Result doLogout() {
		session().remove("username");
		return ok(views.html.logout.render());

	}

	public static Result playScreen() {
		return ok(views.html.playScreen.render(session().get("username")));
	}

	public static WebSocket<String> guess() {
		WebSocket<String> ws = null;
		final int r = 100;
		// final boolean test = playerList.containsValue("Stefo");
		ws = new WebSocket<String>() {
			public void onReady(WebSocket.In<String> in,
					final WebSocket.Out<String> out) {
				in.onMessage(new Callback<String>() {
					public void invoke(String g) {						
						
						out.write(g);
					}
				});
				in.onClose(new Callback0(){
					public void invoke() {
						System.out.println("Disconnected!");
					}
				});
			}
		};
		return ws;
	}
}