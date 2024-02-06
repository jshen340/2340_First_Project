package com.example.a2340projectone.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {
    private List<Courses> items;
    private Context context;

    public CourseAdapter(List<Courses> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_coursedisplay, parent, false);
        return new CourseVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        holder.courseName.setText(items.get(position).getCourseName());
        holder.instructor.setText(items.get(position).getInstructor());
        holder.day.setText(items.get(position).getDay());
        holder.timestart.setText(items.get(position).getTimeStart());
        holder.timeend.setText(items.get(position).getTimeEnd());
        holder.section.setText(items.get(position).getSection());
        holder.building.setText(items.get(position).getBuilding());
        holder.room.setText(items.get(position).getRoom());

        // Set click listener for edit button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle edit button click
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.fragment_add_course);

                EditText editCourseName = dialog.findViewById(R.id.coursenameinput);
                EditText editInstructor = dialog.findViewById(R.id.instructorinput);
                EditText editBuilding = dialog.findViewById(R.id.buildinginput);
//        EditText editDay = dialog.findViewById(R.id.dayinput);
                CheckBox monday = dialog.findViewById(R.id.mondayCheckbox);
                CheckBox tuesday = dialog.findViewById(R.id.tuesdayCheckbox);
                CheckBox wed = dialog.findViewById(R.id.wedCheckbox);
                CheckBox thurs = dialog.findViewById(R.id.thursCheckbox);
                CheckBox fri = dialog.findViewById(R.id.friCheckbox);
                EditText editTimeStart = dialog.findViewById(R.id.timestartinput);
                EditText editTimeEnd = dialog.findViewById(R.id.timeendinput);
                EditText editSection = dialog.findViewById(R.id.sectioninput);
                EditText editRoom = dialog.findViewById(R.id.roominput);
                // Add other EditText fields for other course details

                editCourseName.setText(items.get(position).getCourseName());
                editInstructor.setText(items.get(position).getInstructor());
                editBuilding.setText(items.get(position).getBuilding());

                editTimeStart.setText(items.get(position).getTimeStart());
                editTimeEnd.setText(items.get(position).getTimeEnd());
                editSection.setText(items.get(position).getSection());
                editRoom.setText(items.get(position).getRoom());

                if (items.get(position).getDaysClassHeld()[0])
                        monday.setChecked(true);
                if (items.get(position).getDaysClassHeld()[1])
                        tuesday.setChecked(true);
                if (items.get(position).getDaysClassHeld()[2])
                        wed.setChecked(true);
                if (items.get(position).getDaysClassHeld()[3])
                        thurs.setChecked(true);
                if (items.get(position).getDaysClassHeld()[4])
                        fri.setChecked(true);


                Button addtodashBtn = dialog.findViewById(R.id.addtodash);
                addtodashBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Retrieve data from input fields
                        String courseDash = editCourseName.getText().toString();
                        String instructorDash = editInstructor.getText().toString();
                        String buildingDash = editBuilding.getText().toString();
                        String sectionDash = editSection.getText().toString();
                        String roomDash = editRoom.getText().toString();
                        String[] daysSecond = new String[5];

                        if (monday.isChecked() || tuesday.isChecked()
                                || wed.isChecked() || thurs.isChecked() ||
                                fri.isChecked()) {
                            daysSecond[0] = textIfChecked(monday, "M");
                            daysSecond[1] = textIfChecked(tuesday, "T");
                            daysSecond[2] = textIfChecked(wed, "W");
                            daysSecond[3] = textIfChecked(thurs, "Th");
                            daysSecond[4] = textIfChecked(fri, "F");
                        }
                        String daysString = "";
                        Courses toBeAdded = new Courses(courseDash, instructorDash, daysString, sectionDash, buildingDash, roomDash);
                        for (int i = 0; i < daysSecond.length; i++) {
                            if (!daysSecond[i].equals("")) {
                                toBeAdded.getDaysClassHeld()[i] = true;
                                if (i == 0 || daysString.isEmpty()) {
                                    daysString += daysSecond[i];
                                } else {
                                    daysString += ", " + daysSecond[i];
                                }
                            } else {
                                toBeAdded.getDaysClassHeld()[i] = false;
                            }
                        }
                        toBeAdded.setDay(daysString);
                        if (toBeAdded.checkTime(editTimeStart.getText().toString())) {
                            toBeAdded.setTimeStart(editTimeStart.getText().toString());
                        } else {
                            Toast myToast = Toast.makeText(dialog.getContext(), "Start time should be HH:MM!", Toast.LENGTH_SHORT);
                            myToast.show();
                        }

                        if (toBeAdded.checkTime(editTimeEnd.getText().toString())) {
                            toBeAdded.setTimeEnd(editTimeEnd.getText().toString());
                        } else {
                            Toast myToast = Toast.makeText(dialog.getContext(), "End time should be HH:MM!", Toast.LENGTH_SHORT);
                            myToast.show();
                        }
                        if (toBeAdded.checkTime(editTimeEnd.getText().toString()) && toBeAdded.checkTime(editTimeStart.getText().toString())) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                            alertDialogBuilder.setTitle("Edit Course");
                            alertDialogBuilder.setMessage("Are you sure you want to edit this item?");

                            alertDialogBuilder.setPositiveButton("Edit", (dialog, which) -> {
                                items.set(holder.getAdapterPosition(), toBeAdded);
                                notifyItemChanged(holder.getAdapterPosition());

                            });

                            alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                            dialog.dismiss();
                        }
                        }


                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private String textIfChecked(CheckBox c, String s) {
        if (c.isChecked()) {
            return s;
        }
        return "";
    }

    public class CourseVH extends RecyclerView.ViewHolder {
        TextView courseName, instructor, day, timestart, timeend, section, building, room;
        ImageButton deleteButton, editButton;

        private CourseAdapter adapter;

        public CourseVH(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.courseName);
            instructor = itemView.findViewById(R.id.instructorName);
            day = itemView.findViewById(R.id.dayName);
            timestart = itemView.findViewById(R.id.timestartName);
            timeend = itemView.findViewById(R.id.timeendName);
            section = itemView.findViewById(R.id.sectionName);
            building = itemView.findViewById(R.id.buildingName);
            room = itemView.findViewById(R.id.roomName);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);

            itemView.findViewById(R.id.deleteButton).setOnClickListener(view -> {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                alertDialogBuilder.setTitle("Delete Course");
                alertDialogBuilder.setMessage("Are you sure you want to delete this item?");

                alertDialogBuilder.setPositiveButton("Delete", (dialog, which) -> {
                    Toast.makeText(view.getContext(), "Deleted Item Successfully", Toast.LENGTH_SHORT);
                    adapter.items.remove(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                });

                alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> {
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            });

        }
        public CourseVH linkAdapter(CourseAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }
}