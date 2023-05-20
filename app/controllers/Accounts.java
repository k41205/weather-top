package controllers;

import models.User;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
  public static void signup() {
    Logger.info("Rendering Signup");
    render("signup.html");
  }

  public static void login() {
    Logger.info("Rendering Login");
    render("login.html");
  }

  public static void register(String firstname, String lastname, String email, String password) {
    Logger.info("Registering new user " + email);
    User user = new User(firstname, lastname, email, password);
    user.save();
    redirect("/login");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    User user = User.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Userid", user.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    Logger.info("Logging out");
    session.clear();
    redirect("/");
  }

  public static User getLoggedInUser() {
    Logger.info("Checking Auth User");
    User user = null;
    if (session.contains("logged_in_Userid")) {
      String userId = session.get("logged_in_Userid");
      user = User.findById(Long.parseLong(userId));
    } else {
      login();
    }
    return user;
  }
}