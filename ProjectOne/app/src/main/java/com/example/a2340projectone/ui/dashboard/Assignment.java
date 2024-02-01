package com.example.a2340projectone.ui.dashboard;

public class Assignment {
    private String name, course, due;

    public Assignment(String name, String due, String course) {
        this.name = name;
        this.due = due;
        this.course = course;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCourse() {
        return course;
    }
    public String getDue() {
        return due;
    }
}
