package controllers;

import models.Station;
import models.Measure;
import models.Member;

import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    flash.put("error", "Station not found");
    if (member.stations.contains(station)) {
      Logger.info("Rendering Station ID = " + id);
      render("station.html", station);
    } else {
      ErrorCtrl.index();
    }
  }

  public static void addMeasure(Long id, int code, float temp, float windSpeed, float windDirection, float pressure) {
    Member member = Accounts.getLoggedInMember();
    String errorMsg = "";
    if (temp < -20 || temp > 50) {
      errorMsg += "Temperature must be a value between -20 and 50. <br>";
    }
    if (windSpeed < 0 || windSpeed > 117) {
      errorMsg += "Wind Speed must be a value between 0 and 117. <br>";
    }
    if (windDirection < 0 || windDirection > 360) {
      errorMsg += "Wind Direction must be a value between 0 and 360. <br>";
    }
    if (pressure < 500 || pressure > 1500) {
      errorMsg += "Pressure must be a value between 500 and 1500. <br>";
    }
    if (!errorMsg.isBlank()) {
      flash.put("error", errorMsg);
      redirect("/stations/" + id);
    }
    Logger.info("Adding Measure ID = " + id);
    Measure measure = new Measure(code, temp, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.measures.add(measure);
    station.save();
    redirect("/stations/" + id);
  }

  public static void deleteMeasure(Long id, Long measureId) {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Deleting Measure ID: " + measureId + " from Station ID " + id);
    Station station = Station.findById(id);
    Measure measure = Measure.findById(measureId);
    station.measures.remove(measure);
    station.save();
    measure.delete();
    redirect("/stations/" + id);
  }

}

