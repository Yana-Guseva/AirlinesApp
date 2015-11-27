package com.yana.model;

import java.util.Date;

/**
 * Created by Yana on 27.11.2015.
 */
public class Flight {
    private int filghtId;
    private String cityOfDeparture;
    private String cityOfDestination;
    private Date date;
    private Date time;
    private int teamId;

    public Flight(int filghtId, String cityOfDeparture, String cityOfDestination, Date date, Date time, int teamId) {
        this.filghtId = filghtId;
        this.cityOfDeparture = cityOfDeparture;
        this.cityOfDestination = cityOfDestination;
        this.date = date;
        this.time = time;
        this.teamId = teamId;
    }

    public Flight() {}

    public int getFilghtId() {
        return filghtId;
    }

    public void setFilghtId(int filghtId) {
        this.filghtId = filghtId;
    }

    public String getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(String cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public String getCityOfDestination() {
        return cityOfDestination;
    }

    public void setCityOfDestination(String cityOfDestination) {
        this.cityOfDestination = cityOfDestination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
