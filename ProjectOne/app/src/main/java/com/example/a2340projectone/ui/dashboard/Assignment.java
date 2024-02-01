package com.example.a2340projectone.ui.dashboard;

import com.example.a2340projectone.Doable;
import com.example.a2340projectone.ui.todolist.Task;

public class Assignment extends Task {
    private String name, course, due;
    private boolean complete;

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

    @Override
    public boolean isComplete() {
        return complete;
    }

    public void toggle() {
        complete = !complete;
    }
}
