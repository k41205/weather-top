package controllers;

import models.Member;
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
    Member member = new Member(firstname, lastname, email, password);
    String errorMsg = "";

    if (firstname.length() > 12) {
      errorMsg += "First Name must have a maximum of 12 characters. <br>";
    } else {
      member.firstname = firstname;
    }

    if (lastname.length() > 12) {
      errorMsg += "Last Name must have a maximum of 12 characters. <br>";
    } else {
      member.lastname = lastname;
    }

    member.email = email;

    if (password.length() < 6 || password.length() > 12) {
      errorMsg += "Password must have between 6 and 12 characters. <br>";
    } else {
      member.password = password;
    }

    if (!errorMsg.isBlank()) {
      flash.put("error", errorMsg);
      redirect("/signup");
    }
    Logger.info("Registering new member " + email);
    member.save();
    redirect("/login");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
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

  public static Member getLoggedInMember() {
    Logger.info("Checking Auth Member");
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
}