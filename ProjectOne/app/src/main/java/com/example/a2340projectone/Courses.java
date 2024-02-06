package com.example.a2340projectone;

public class Courses {
    private String coursename;
    private String instructor;
    private String day;
    private String building;
    private String room;

    private String timestart;
    private String timeend;

    private String section;



    public Courses(String coursename, String instructor, String day, String timestart,String timeend, String section, String building, String room) {
        this.coursename = coursename;
        this.instructor = instructor;
        this.day = day;
        this.timestart = timestart;
        this.timeend = timeend;
        this.section = section;
        this.building = building;
        this.room = room;
    }

    // Getter methods
    public String getCoursename() {
        return coursename;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDay() {
        return day;
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
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setDay(String day) {
        this.day = day;
    }

//   // public void setTime(String time) {
//        this.time = time;
//    }

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