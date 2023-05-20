package controllers;

import models.Station;
import models.Measure;
import models.User;

import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    User user = Accounts.getLoggedInUser();
    Station station = Station.findById(id);
    Logger.info("Rendering Station ID = " + id);
    render("station.html", station);
  }

  public static void addMeasure(Long id, int code, float temp, float windSpeed, float windDirection, float pressure) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Adding Measure ID = " + id);
    Measure measure = new Measure(code, temp, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.measures.add(measure);
    station.save();
    redirect("/stations/" + id);
  }

  public static void deleteMeasure(Long id, Long measureId) {
    User user = Accounts.getLoggedInUser();
    Logger.info("Deleting Measure ID: " + measureId + " from Station ID " + id);
    Station station = Station.findById(id);
    Measure measure = Measure.findById(measureId);
    station.measures.remove(measure);
    station.save();
    measure.delete();
    redirect("/stations/" + id);
  }

}

