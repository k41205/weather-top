package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Measure extends Model
{
    public int code;
    public float temp;
    public int windSpeed;
    public float windDirection;
    public int pressure;

    public Measure(int code, float temp, int windSpeed, float windDirection, int pressure)
    {
        this.code = code;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }
}