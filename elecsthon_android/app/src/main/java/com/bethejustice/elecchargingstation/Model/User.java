package com.bethejustice.elecchargingstation.Model;

public class User {

    private String name;
    private int type;
    private int limitSpeed;

    public User(String name, int type, int limitSpeed) {

        this.name = name;
        this.type = type;
        this.limitSpeed = limitSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(int limitSpeed) {
        this.limitSpeed = limitSpeed;
    }
}
