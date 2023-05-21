package controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {

  public static void index() {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Rendering Dashboard");
    List<Station> stations = member.stations;
    Collections.sort(stations, Comparator.comparing(Station::getName, String.CASE_INSENSITIVE_ORDER));
    render("dashboard.html", member, stations);
  }

  public static void addStation(String name, float latitude, float longitude) {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Adding a Station");
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Deleting a Station");
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }
}

