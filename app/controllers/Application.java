package controllers;

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
<<<<<<< HEAD
		//return redirect("/");
		return ok(views.html.index.render(request().getQueryString("username")));		
=======
		return redirect("\\");		
	}
	
	public static Result doLogout(){
		session().remove("username");
		return ok(views.html.logout.render());
>>>>>>> 5db9a8fc046f1685f149de241bba71cf71a93f9d
	}

	public static Result playScreen(){
		return ok(views.html.playScreen.render(session().get("username")));
	}

}
