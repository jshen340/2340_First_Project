package com.example.a2340projectone.ui.exams;

import com.example.a2340projectone.ui.todolist.Task;
import com.example.a2340projectone.ui.todolist.SortType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Exam {
    private String name;
    public String dueDate;
    private String course;
    private String time;
    private String location;



    public Exam(String name, String course, String location) {
        this.name = name;
        this.course = course;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCourse() {
        return course;
    }

    public void setDate(String date) {
        this.dueDate = date;
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
    public boolean checkTime(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
            return time;
    }

    public String getLocation() {
        return location;
    }
}

