package com.yana.model;

/**
 * Created by Yana on 27.11.2015.
 */
public class WorkerTeam {
    private int teamId;
    private int workerId;

    public  WorkerTeam() {}

    public WorkerTeam(int teamId, int workerId) {
        this.teamId = teamId;
        this.workerId = workerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}
