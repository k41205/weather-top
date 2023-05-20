package controllers;

import java.util.List;

import models.User;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {

  public static void index() {
    User user = Accounts.getLoggedInUser();
    Logger.info("Rendering Dashboard");
    List<Station> stations = user.stations;
    render("dashboard.html", user, stations);
  }

  public static void addStation(String name, float latitude, float longitude) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Adding a Station");
    Station station = new Station(name, latitude, longitude);
    user.stations.add(station);
    user.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Deleting a Station");
    Station station = Station.findById(id);
    user.stations.remove(station);
    user.save();
    station.delete();
    redirect("/dashboard");
  }
}

