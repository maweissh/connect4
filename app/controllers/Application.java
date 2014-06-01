package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
	public static Result init(){
		//hier fehlt die Abfrage ob login=true
		return ok(views.html.login.render() );
	}
    
    public static Result index() {
        return ok(views.html.index.render("Hallo ihr Opfas"));
    }
    
}
