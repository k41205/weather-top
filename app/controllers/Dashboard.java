package controllers;

import java.util.List;
import java.util.HashMap;

import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller
{
  public static final HashMap<Integer, String> WEATHER_CODES = new HashMap<>();
  static {
    WEATHER_CODES.put(100, "Clear");
    WEATHER_CODES.put(200, "Partial clouds");
    WEATHER_CODES.put(300, "Cloudy");
    WEATHER_CODES.put(400, "Light Showers");
    WEATHER_CODES.put(500, "Heavy Showers");
    WEATHER_CODES.put(600, "Rain");
    WEATHER_CODES.put(700, "Snow");
    WEATHER_CODES.put(800, "Thunder");
  }

  public static final HashMap<Integer, Integer[]> WIND_SPEEDS = new HashMap<>();
  static {
    WIND_SPEEDS.put(0, new Integer[]{0, 1});
    WIND_SPEEDS.put(1, new Integer[]{1, 5});
    WIND_SPEEDS.put(2, new Integer[]{6, 11});
    WIND_SPEEDS.put(3, new Integer[]{12, 19});
    WIND_SPEEDS.put(4, new Integer[]{20, 28});
    WIND_SPEEDS.put(5, new Integer[]{29, 38});
    WIND_SPEEDS.put(6, new Integer[]{39, 49});
    WIND_SPEEDS.put(7, new Integer[]{50, 61});
    WIND_SPEEDS.put(8, new Integer[]{62, 74});
    WIND_SPEEDS.put(9, new Integer[]{75, 88});
    WIND_SPEEDS.put(10, new Integer[]{89, 102});
    WIND_SPEEDS.put(11, new Integer[]{103, 117});
  }

  public static Integer speedToBeaufort(int speed) {
    for (HashMap.Entry<Integer, Integer[]> entry : WIND_SPEEDS.entrySet()) {
      Integer[] range = entry.getValue();
      if (speed >= range[0] && speed <= range[1]) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static void index()
  {
    Logger.info("Rendering Admin");

    List<Station> stations = Station.findAll();
    renderArgs.put("WEATHER_CODES", WEATHER_CODES);
    render ("dashboard.html", stations);
  }
}

