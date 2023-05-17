package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Measure extends Model
{
    public int code;
    public float temp;
    public float windSpeed;
    public float windDirection;
    public float pressure;

    public Measure(int code, float temp, float windSpeed, float windDirection, float pressure)
    {
        this.code = code;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }
}