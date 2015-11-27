package com.yana.model;

/**
 * Created by Yana on 27.11.2015.
 */
public class Worker {
    private int workerId;
    private int postId;
    private String name;
    private int year;
    private int experience;

    public Worker() {}

    public Worker(int workerId, int postId, String name, int year, int experience) {
        this.workerId = workerId;
        this.postId = postId;
        this.name = name;
        this.year = year;
        this.experience = experience;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
