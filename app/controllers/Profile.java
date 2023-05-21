package controllers;

import models.Member;

import play.Logger;
import play.mvc.Controller;

public class Profile extends Controller {
  public static void index() {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Rendering Profile");
    render("profile.html", member);
  }

  public static void editDetails(String firstname, String lastname, String email, String password) {
    Member member = Accounts.getLoggedInMember();
    String errorMsg = "";

    if (!firstname.isBlank()) {
      if (firstname.length() > 12) {
        errorMsg += "First Name must have a maximum of 12 characters. <br>";
      } else {
        member.firstname = firstname;
      }
    }

    if (!lastname.isBlank()) {
      if (lastname.length() > 12) {
        errorMsg += "Last Name must have a maximum of 12 characters. <br>";
      } else {
        member.lastname = lastname;
      }
    }

    if (!email.isBlank()) {
      member.email = email;
    }

    if (!password.isBlank()) {
      if (password.length() < 6 || password.length() > 12) {
        errorMsg += "Password must have between 6 and 12 characters. <br>";
      } else {
        member.password = password;
      }
    }

    if (!errorMsg.isBlank()) {
      flash.put("error", errorMsg);
      redirect("/profile");
    }

    Logger.info("Editing Profile");
    member.save();
    redirect("/profile");
  }
}
