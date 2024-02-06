package com.example.a2340projectone.ui.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Courses {
    private String courseName;
    private String instructor;
    private String day;
    private String building;
    private String room;

    private String timestart;
    private String timeend;

    private String section;

    private boolean[] daysClassHeld = new boolean[5];



    public Courses(String coursename, String instructor, String day, String section, String building, String room) {
        this.courseName = coursename;
        this.instructor = instructor;
        this.day = day;
        this.timestart = timestart;
        this.timeend = timeend;
        this.section = section;
        this.building = building;
        this.room = room;
    }

    // Getter methods
    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDay() {
        return day;
    }

    public boolean[] getDaysClassHeld() {
        return daysClassHeld;
    }

    public String getTimeStart() {
        return timestart;
    }
    public String getTimeEnd() {
        return timeend;
    }
    public String getSection() {
        return section;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    // Setter methods
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDay(String day) {
        this.day = day;
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

    public void setTimestart(String time) {
        this.timestart = time;
    }

    public void setTimeend(String time) {
        this.timeend = time;
    }


    public void setTimeStart(String timestart) {
        this.timestart = timestart;
    }
    public void setTimeEnd(String timeend) {
        this.timeend = timeend;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoom(String room) {
        this.room = room;
    }


}