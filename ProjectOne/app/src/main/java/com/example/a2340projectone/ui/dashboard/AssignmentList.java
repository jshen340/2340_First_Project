package com.example.a2340projectone.ui.dashboard;

import com.example.a2340projectone.ui.todolist.Task;

import java.util.ArrayList;
import java.util.Collections;

public class AssignmentList {
    public static ArrayList<Assignment> assignments = new ArrayList<>();
    public static boolean completeListOn = false;
    public static ArrayList<Assignment> completedAssignments = new ArrayList<>();
    public static void courseSorted() {
        int size = assignments.size();

        for (int step = 1; step < size; step++) {
            Assignment key = assignments.get(step);
            int j = step - 1;

            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            // For descending order, change key<array[j] to key>array[j].
            while (j >= 0 && key.getCourse().compareToIgnoreCase(assignments.get(j).getCourse()) < 0) {
                assignments.set(j + 1, assignments.get(j));
                --j;
            }

            // Place key at after the element just smaller than it.
            assignments.set(j + 1, key);
        }
    }

    public static void dateSorted() {
        Collections.sort(assignments);
    }
}
