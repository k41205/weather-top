package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{

  public static void index()
  {
    Logger.info("Rendering Dashboard (Dashboard.index)");

    List<Station> stations = Station.findAll();
    render ("dashboard.html", stations);
  }

  public static void addStation (String name)
  {
    Logger.info("Adding a Station (Dashboard.addStation)");
    Station station = new Station(name);
    station.save();
    Logger.info(station.name);
    redirect ("/dashboard");
  }
}

