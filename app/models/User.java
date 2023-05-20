package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Entity
public class User extends Model {

  // -----  FIELDS ----- //

  public String firstname;
  public String lastname;
  public String email;
  public String password;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Station> stations = new ArrayList<Station>();

  // -----  CONSTRUCTOR ----- //

  public User(String firstname, String lastname, String email, String password) {
    setFirstname(firstname);
    setLastname(lastname);
    setEmail(email);
    setPassword(password);
  }

// ----- SETTERS ----- //

  public void setFirstname(String firstname) {
    if (firstname != null && !firstname.isEmpty()) {
      this.firstname = firstname;
    } else {
      Logger.warn("Invalid firstname");
    }
  }

  public void setLastname(String lastname) {
    if (lastname != null && !lastname.isEmpty()) {
      this.lastname = lastname;
    } else {
      Logger.warn("Invalid lastname");
    }
  }

  public void setEmail(String email) {
    if (email != null && !email.isEmpty()) {
      this.email = email;
    } else {
      Logger.warn("Invalid email");
    }
  }

  public void setPassword(String password) {
    if (password != null && !password.isEmpty()) {
      this.password = password;
    } else {
      Logger.warn("Invalid password");
    }
  }

  // -----  BESPOKEN METHODS ----- //

  public static User findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}