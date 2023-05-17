package controllers;

import java.util.List;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static void index()
  {
    List<Station> stations = Station.findAll();
    Logger.info("Rendering dashboard");
    render ("dashboard.html", stations);
  }
}
