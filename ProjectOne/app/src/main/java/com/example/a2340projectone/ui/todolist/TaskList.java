package com.example.a2340projectone.ui.todolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static ArrayList<Task> completedTasks = new ArrayList<Task>();
    public static boolean completedListOn = false;

    public static void dateSorted() {
        Collections.sort(tasks);
        
    }
    public static void categorySorted() {
        int size = tasks.size();

        for (int step = 1; step < size; step++) {
            Task key = tasks.get(step);
            int j = step - 1;

            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            // For descending order, change key<array[j] to key>array[j].
            while (j >= 0 && key.getCourse().compareToIgnoreCase(tasks.get(j).getCourse()) < 0) {
                tasks.set(j+1, tasks.get(j));
                --j;
            }

            // Place key at after the element just smaller than it.
            tasks.set(j + 1, key);
        }
    }
}

