package controllers;

import play.Logger;
import play.mvc.Controller;

public class ErrorCtrl extends Controller {
  public static void index() {
    Logger.info("Error 404");
    render("errors/404.html");
  }
}