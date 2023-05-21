package models;

import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Station extends Model {

  // -----  FIELDS ----- //

  public String name;
  public float latitude;
  public float longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Measure> measures = new ArrayList<Measure>();

  // -----  CONSTRUCTOR ----- //

  public Station(String name, float latitude, float longitude) {
    setName(name);
    setLatitude(latitude);
    setLongitude(longitude);
  }

  // -----  SETTERS ----- //

  public String getName(){
    return name;
  }
  public void setName(String name) {
    if (name != null && !name.isEmpty()) {
      this.name = name;
    } else {
      Logger.warn("Invalid station name");
    }
  }

  public void setLatitude(Float latitude) {
    if (latitude != null && latitude >= -90 && latitude <= 90) {
      this.latitude = latitude;
    } else {
      Logger.warn("Invalid latitude value");
    }
  }

  public void setLongitude(Float longitude) {
    if (longitude != null && longitude >= -180 && longitude <= 180) {
      this.longitude = longitude;
    } else {
      Logger.warn("Invalid longitude value");
    }
  }

}
