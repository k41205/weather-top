package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Measure extends Model
{
    public int code;
    public float temp;
    public int windSpeed;
    public int pressure;

    public Measure(int code, float temp, int windSpeed, int pressure)
    {
        this.code = code;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
    }
}