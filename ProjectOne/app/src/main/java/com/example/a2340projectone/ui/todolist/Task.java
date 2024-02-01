package com.example.a2340projectone.ui.todolist;

import com.example.a2340projectone.Doable;
import com.example.a2340projectone.ui.exams.DateValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task implements Doable, Comparable<Task> {
    private String name, course, due;

    public Task() {

    }
    private boolean complete;
    public Task(String name, String due, String course) {
        this.name = name;
        this.due = due;
        this.course = course;
        complete = false;

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

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public int compareTo(Task t) {
        if (this.isComplete() == !t.isComplete()) {
            return -1;
        }
        if (!this.isComplete() == t.isComplete()) {
            return 1;
        }
        return 0;
    }


    public void toggle() {
        complete = !complete;
    }
}

