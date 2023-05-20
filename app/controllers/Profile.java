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
    String errorMsg = "";

    if (!firstname.isBlank()) {
      if (firstname.length() > 12) {
        errorMsg += "First Name must have a maximum of 12 characters. <br>";
      } else {
        user.firstname = firstname;
      }
    }

    if (!lastname.isBlank()) {
      if (lastname.length() > 12) {
        errorMsg += "Last Name must have a maximum of 12 characters. <br>";
      } else {
        user.lastname = lastname;
      }
    }

    if (!email.isBlank()) {
      user.email = email;
    }

    if (!password.isBlank()) {
      if (password.length() < 6 || password.length() > 12) {
        errorMsg += "Password must have between 6 and 12 characters. <br>";
      } else {
        user.password = password;
      }
    }

    if (!errorMsg.isBlank()) {
      flash.put("error", errorMsg);
      redirect("/profile");
    }

    Logger.info("Editing Profile");
    user.save();
    redirect("/profile");
  }
}
