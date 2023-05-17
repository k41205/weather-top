import models.Measure;
import models.Station;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherUtils {

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
    public static Integer speedToBeaufort(float speed) {
        for (HashMap.Entry<Integer, Integer[]> entry : WIND_SPEEDS.entrySet()) {
            Integer[] range = entry.getValue();
            if (speed >= range[0] && speed <= range[1]) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static final HashMap<String, Float> WIND_DIRECTIONS = new HashMap<>();
    static {
        WIND_DIRECTIONS.put("North North East", 11.25f);
        WIND_DIRECTIONS.put("North East", 33.75f);
        WIND_DIRECTIONS.put("East North East", 56.25f);
        WIND_DIRECTIONS.put("East", 78.75f);
        WIND_DIRECTIONS.put("East South East", 101.25f);
        WIND_DIRECTIONS.put("South East", 123.75f);
        WIND_DIRECTIONS.put("South South East", 146.25f);
        WIND_DIRECTIONS.put("South", 168.75f);
        WIND_DIRECTIONS.put("South South West", 191.25f);
        WIND_DIRECTIONS.put("South West", 213.75f);
        WIND_DIRECTIONS.put("West South West", 236.25f);
        WIND_DIRECTIONS.put("West", 258.75f);
        WIND_DIRECTIONS.put("West North West", 281.25f);
        WIND_DIRECTIONS.put("North West", 303.75f);
        WIND_DIRECTIONS.put("North North West", 326.25f);
        WIND_DIRECTIONS.put("North", 348.75f);
    }

    public static String degreeToName(float degree) {
        String previousKey = "North";
        for (HashMap.Entry<String, Float> entry : WIND_DIRECTIONS.entrySet()) {
            String windDirection = entry.getKey();
            float startRange = entry.getValue();
            if (degree < startRange) {
                return previousKey;
            }
            previousKey = windDirection;
        }
        return "North";
    }

    public static float maxValue(ArrayList<Float> measures) {
        float maxValue = 0;
        for (Float measure : measures) {
            maxValue = Math.max(maxValue, measure);
        }
        return maxValue;
    }

    public static float minValue(ArrayList<Float> measures) {
        float minValue = Float.MAX_VALUE;
        for (Float measure : measures) {
            minValue = Math.min(minValue, measure);
        }
        return minValue;
    }
}
