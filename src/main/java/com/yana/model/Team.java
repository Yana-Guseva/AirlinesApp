package com.yana.model;

/**
 * Created by Yana on 27.11.2015.
 */
public class Team {
    private int teamId;
    private int number;

    public Team() {}

    public Team(int teamId, int number) {
        this.teamId = teamId;
        this.number = number;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
