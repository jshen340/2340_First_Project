package com.example.a2340projectone.ui.todolist;

public class Task {
    private String name, course, due;

    public Task(String name, String due, String course) {
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

    public void setDue(String due) {
        this.due = due;
    }

    public String getDue() {
        return due;
    }
}
