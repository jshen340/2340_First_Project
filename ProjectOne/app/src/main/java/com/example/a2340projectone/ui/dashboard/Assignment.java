package com.example.a2340projectone.ui.dashboard;

import com.example.a2340projectone.Doable;
import com.example.a2340projectone.ui.todolist.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment implements Comparable<Assignment> {
    private String name, course, due;

    Date dueDate;
    private boolean complete;

    public Assignment(String name, String course, String dueDate) {
        this.name = name;
        this.course = course;
        this.due = due;

    }

    public boolean checkDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
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
    public void setDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            this.dueDate = dateFormat.parse(date);
        } catch (ParseException e) {

        }
        this.due = date;
    }

    @Override
    public int compareTo(Assignment other) {
        return this.dueDate.compareTo(other.dueDate);
    }

    public boolean isComplete() {
        return complete;
    }

    public void toggle() {
        complete = !complete;
    }
}
