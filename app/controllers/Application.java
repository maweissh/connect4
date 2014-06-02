package controllers;

import javax.servlet.http.HttpSession;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	public static Result init() {
		//todo: session.remove für logout button
		//session().remove("username");
		
		if (!session().containsKey("username")) {
			return ok(views.html.login.render());
		} else {
			return ok(views.html.index.render(session().get("username")));
		}
	}
	
	public static Result saveLogin(){
		session().put("username", request().getQueryString("username"));
		return ok(views.html.index.render(request().getQueryString("username")));		
	}


}
