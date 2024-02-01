package com.example.a2340projectone.ui.todolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void dateSorted() {
        SortDates.sortMonths();
        
    }
    public static void categorySorted() {
        int size = tasks.size();

        for (int step = 1; step < size; step++) {
            Task key = tasks.get(step);
            int j = step - 1;

            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            // For descending order, change key<array[j] to key>array[j].
            while (j >= 0 && key.getCourse().compareTo(tasks.get(j).getCourse()) < 0) {
                tasks.set(j+1, tasks.get(j));
                --j;
            }

            // Place key at after the element just smaller than it.
            tasks.set(j + 1, key);
        }
    }
    public static void completeSort() {
        Collections.sort(tasks);
    }
}

class SortDates {
    // Map to store the numeric value of each month depending on
    // its occurrence i.e. Jan = 1, Feb = 2 and so on.
    static Map<String, Integer> monthsMap = new HashMap<>();

    // Function which initializes the monthsMap
    static void sortMonths() {
        monthsMap.put("01", 1);
        monthsMap.put("02", 2);
        monthsMap.put("03", 3);
        monthsMap.put("04", 4);
        monthsMap.put("05", 5);
        monthsMap.put("06", 6);
        monthsMap.put("07", 7);
        monthsMap.put("08", 8);
        monthsMap.put("09", 9);
        monthsMap.put("10", 10);
        monthsMap.put("11", 11);
        monthsMap.put("12", 12);
    }

    static int cmp(String date) {
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[1]);
        int month = monthsMap.get(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);

        return year * 10000 + month * 100 + day;
    }


}
