package com.example.kp3.Models;

public class User {
    public String name;
    public String email;
    public String password;
    public int points;

    public User(){}

    public User(String name, String email, String password, int points, int mistakes){
        this.name = name;
        this.email = email;
        this.password = password;
        this.points = points;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
}
