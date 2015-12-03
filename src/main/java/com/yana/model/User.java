package com.yana.model;

/**
 * Created by Yana on 02.12.2015.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String role;

    public User() {
    }

    public User(int id, String name, String role, String login, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
