package controllers;

import models.Station;
import models.Measure;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    Station station = Station.findById(id);
    Logger.info("Station id = " + id + "(StationCtrl.index)");
    render("station.html", station);
  }

  public static void addMeasure(Long id, int code, float temp, float windSpeed, float windDirection, float pressure) {
    Logger.info("Adding a Measure (StationCtrl.addMeasure)");
    Measure measure = new Measure(code, temp, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.measures.add(measure);
    station.save();
    redirect("/stations/" + id);
  }

  public static void deleteMeasure(Long id, Long measureId) {
    Logger.info("Station ID: " + id + ", Measure ID: " + measureId);
    Station station = Station.findById(id);
    Measure measure = Measure.findById(measureId);
    Logger.info("Removing" + measureId);
    station.measures.remove(measure);
    station.save();
    measure.delete();
    redirect("/stations/" + id);
  }

}

