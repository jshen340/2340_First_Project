package com.example.a2340projectone;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {
    private List<Courses> items;
    private Context context;

    public CourseAdapter(Context context, List<Courses> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_coursedisplay, parent, false);
        return new CourseVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        holder.courseName.setText(items.get(position).getCoursename());
        holder.instructor.setText(items.get(position).getInstructor());
        holder.day.setText(items.get(position).getDay());
        holder.timestart.setText(items.get(position).getTimeStart());
        holder.timeend.setText(items.get(position).getTimeEnd());
        holder.section.setText(items.get(position).getSection());
        holder.building.setText(items.get(position).getBuilding());
        holder.room.setText(items.get(position).getRoom());

        // Set click listener for delete button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete button click
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, items.size());
            }
        });

        // Set click listener for edit button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle edit button click
                showEditDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void showEditDialog(int position) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_add_course);

        EditText editCourseName = dialog.findViewById(R.id.coursenameinput);
        EditText editInstructor = dialog.findViewById(R.id.instructorinput);
        EditText editBuilding = dialog.findViewById(R.id.buildinginput);
        EditText editDay = dialog.findViewById(R.id.dayinput);
        EditText editTimeStart = dialog.findViewById(R.id.timestartinput);
        EditText editTimeEnd = dialog.findViewById(R.id.timeendinput);
        EditText editSection = dialog.findViewById(R.id.sectioninput);
        EditText editRoom = dialog.findViewById(R.id.roominput);
        // Add other EditText fields for other course details

        Button addtodashBtn = dialog.findViewById(R.id.addtodash);


        addtodashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedCourseName = editCourseName.getText().toString();
                String updatedInstructor = editInstructor.getText().toString();
                String updatedBuilding = editBuilding.getText().toString();
                String updatedDay = editDay.getText().toString();
                String updatedTimeStart = editTimeStart.getText().toString();
                String updatedTimeEnd = editTimeEnd.getText().toString();
                String updatedSection = editSection.getText().toString();
                String updatedRoom = editRoom.getText().toString();
                // Get other updated details from other EditText fields

                // Update the course at the specified position
                items.set(position, new Courses(
                        updatedCourseName,
                        updatedInstructor,
                        updatedBuilding,
                        updatedDay,
                        updatedTimeStart,
                        updatedTimeEnd,
                        updatedSection,
                        updatedRoom
                ));
                notifyItemChanged(position);

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(1000, 1500);
    }

    public class CourseVH extends RecyclerView.ViewHolder {
        TextView courseName, instructor, day, timestart, timeend, section, building, room;
        ImageButton deleteButton, editButton;

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
        }
    }
}