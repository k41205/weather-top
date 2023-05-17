package controllers;

import models.Station;
import models.Measure;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{
    public static void index(Long id)
    {
        Station station = Station.findById(id);
        Logger.info ("Station id = " + id);
        render("station.html", station);
    }
}

