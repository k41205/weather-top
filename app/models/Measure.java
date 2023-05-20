package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.Entity;

import java.util.Date;

@Entity
public class Measure extends Model {

  // -----  FIELDS ----- //

  public Date time;
  public int code;
  public float temp;
  public float windSpeed;
  public float windDirection;
  public float pressure;

  // -----  CONSTRUCTOR ----- //

  public Measure(int code, float temp, float windSpeed, float windDirection, float pressure) {
    this.time = new Date();
    setCode(code);
    setTemp(temp);
    setWindSpeed(windSpeed);
    setWindDirection(windDirection);
    setPressure(pressure);
  }
  // ----- SETTERS ----- //

  public void setCode(Integer code) {
    if (code != null && isValidCode(code)) {
      this.code = code;
    } else {
      Logger.warn("Invalid code value");
    }
  }

  public void setTemp(Float temp) {
    if (temp != null) {
      this.temp = temp;
    } else {
      Logger.warn("Invalid temperature value");
    }
  }

  public void setWindSpeed(Float windSpeed) {
    if (windSpeed != null) {
      this.windSpeed = windSpeed;
    } else {
      Logger.warn("Invalid wind speed value");
    }
  }

  public void setWindDirection(Float windDirection) {
    if (windDirection != null && windDirection >= 0 && windDirection <= 360) {
      this.windDirection = windDirection;
    } else {
      Logger.warn("Invalid wind direction value");
    }
  }

  public void setPressure(Float pressure) {
    if (pressure != null) {
      this.pressure = pressure;
    } else {
      Logger.warn("Invalid pressure value");
    }
  }

  // ----- BESPOKEN METHODS ----- //
  private boolean isValidCode(int code) {
    int[] validCodes = {100, 200, 300, 400, 500, 600, 700, 800};
    for (int validCode : validCodes) {
      if (code == validCode) {
        return true;
      }
    }
    return false;
  }

}