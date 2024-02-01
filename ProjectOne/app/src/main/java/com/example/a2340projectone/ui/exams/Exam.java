package com.example.a2340projectone.ui.exams;

import com.example.a2340projectone.ui.todolist.Task;
import com.example.a2340projectone.ui.todolist.SortType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Exam extends Task {
    private String name;
    public String date;
    private String course;
    private String time;
    private String location;



    public Exam(String name, String dateToChange, String course, String time, String location) {
        DateValidator validator = new DateValidatorUsingDateFormat();
        if (validator.isValid(dateToChange)) {
            date = dateToChange;
        } else {
            date = "Wrong date buddy";
        }
        this.name = name;
        this.course = course;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDue() {
        return date;
    }

    public String getCourse() {
        return course;
    }

    public String getTime() {
        if (Integer.parseInt(time.substring(0,2)) < 12 && Integer.parseInt(time.substring(0,2)) > 7) {
            time += " AM";
        } else if (Integer.parseInt(time.substring(0,2)) >= 1 && Integer.parseInt(time.substring(0,2)) < 7) {
            time += " PM";
        }
        else if (Integer.parseInt(time.substring(0,2)) == 12) {
            time += " PM";
        }
            return time;
    }

    public String getLocation() {
        return location;
    }
}

 class DateValidatorUsingDateFormat implements DateValidator {
    private String dateFormat = "MM/dd/yyyy";

    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
