package com.example.a2340projectone.ui.notifications;

public class Exam {
    private String name;
    private String date;
    private String course;
    private String time;
    private String location;

    public Exam(String name, String date, String course, String time, String location) {
        this.name = name;
        this.date = date;
        this.course = course;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCourse() {
        return course;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}
