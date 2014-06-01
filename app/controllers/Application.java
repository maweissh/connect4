package controllers;

import javax.servlet.http.HttpSession;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	public static Result init() {
		// hier fehlt die Abfrage ob login=true
		//session().remove("username");
		
		if (session().containsKey("username")) {
			return ok(views.html.login.render());
		} else {
			return ok(views.html.index.render("Hallo "
					+ request().getQueryString("username")));
		}

	}
	
	public static Result saveLogin(){
		session().put("username", request().getQueryString("username"));
		return ok(views.html.index.render("Hallo "
				+ request().getQueryString("username")));
	}


}
