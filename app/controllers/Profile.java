package controllers;

import models.User;

import play.Logger;
import play.mvc.Controller;

public class Profile extends Controller {
  public static void index() {
    User user = Accounts.getLoggedInUser();
    Logger.info("Rendering Profile");
    render("profile.html", user);
  }

  public static void editDetails(String firstname, String lastname, String email, String password) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Editing Profile");
    if (!firstname.isBlank()) user.firstname = firstname;
    if (!lastname.isBlank()) user.lastname = lastname;
    if (!email.isBlank()) user.email = email;
    if (!password.isBlank()) user.password = password;
    user.save();
    redirect("/profile");
  }
}
