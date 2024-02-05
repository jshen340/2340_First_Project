package com.example.a2340projectone.ui.todolist;

import com.example.a2340projectone.Doable;
import com.example.a2340projectone.ui.dashboard.Assignment;
import com.example.a2340projectone.ui.exams.DateValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task implements Doable, Comparable<Task> {
    private String name, course, due;
    Date dueDate;

    public Task() {

    }
    private boolean complete;
    public Task(String name, String course) {
        this.name = name;
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

    public void setDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            this.dueDate = dateFormat.parse(date);
        } catch (ParseException e) {

        }
        this.due = date;
    }

    @Override
    public int compareTo(Task other) {
        return this.dueDate.compareTo(other.dueDate);
    }

    public void toggle() {
        complete = !complete;
    }
}

