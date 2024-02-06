package com.example.a2340projectone.ui.dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.ui.home.CourseList;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentVH>{
    List<Assignment> items;

    public AssignmentAdapter(List<Assignment> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AssignmentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentVH holder, int position) {
        holder.courseName.setText("Course: " + items.get(position).getCourse());
        holder.taskName.setText(items.get(position).getName());
        holder.taskDue.setText("Due Date: " + items.get(position).getDue());
        if (items.get(position).isComplete()) {
            holder.checkbox.setChecked(true);
        }


        holder.itemView.findViewById(R.id.editAssignment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView((R.layout.fragment_add_assignment));

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(dialog.getContext(), android.R.layout.simple_spinner_item, CourseList.coursesAvailable);
                Spinner spinner = dialog.findViewById(R.id.assignmentCourseFill);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                EditText editText = dialog.findViewById(R.id.assignment_name_fill);
                EditText editDate = dialog.findViewById(R.id.assignment_duedate_fill);
                Button updateBtn = dialog.findViewById(R.id.add_assignment_button);
                TextView title = dialog.findViewById(R.id.textView_Assigment);



                title.setText("Edit Assignment");

                updateBtn.setText("UPDATE");

                editText.setText((items.get(holder.getAdapterPosition()).getName()));
                editDate.setText((items.get(holder.getAdapterPosition()).getDue()));

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", course = "", date = "";
                        course = (String) spinner.getSelectedItem();

                        if (!editText.getText().toString().equals("")) {
                            name = editText.getText().toString();
                        }

                        if (!editDate.getText().toString().equals("")) {
                            date = editDate.getText().toString();
                        }
                        Assignment toBeAdded = new Assignment(name, course);
                        if (items.get(holder.getAdapterPosition()).isComplete()) {
                            toBeAdded.toggle();
                        }
                        if (toBeAdded.checkDate(date)) {
                            toBeAdded.setDate(date);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
                            alertDialogBuilder.setTitle("Edit Assignment");
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
                        } else {
                            Toast myToast = Toast.makeText(dialog.getContext(), "Invalid Date Entered! Date should be entered MM-DD-YYYY", Toast.LENGTH_SHORT);
                            myToast.show();
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class AssignmentVH extends RecyclerView.ViewHolder {
    TextView courseName;
    TextView taskName;
    TextView taskDue;
    CheckBox checkbox;
    Button editAssignment;
    private AssignmentAdapter adapter;
    public AssignmentVH(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.assignment_title);
        courseName = itemView.findViewById(R.id.course_Title);
        taskDue = itemView.findViewById(R.id.assignment_dueDate_Title);
        checkbox = itemView.findViewById(R.id.assignmentCompleted);
        editAssignment = itemView.findViewById(R.id.editAssignment);
        itemView.findViewById(R.id.deleteAssignment).setOnClickListener(view -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setTitle("Delete Assignment");
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
        checkbox.setOnClickListener(view -> {
            adapter.items.get(getAdapterPosition()).toggle();
            if (checkbox.isChecked()) {
                AssignmentList.completedAssignments.add(adapter.items.get(getAdapterPosition()));
                AssignmentList.assignments.remove(adapter.items.get(getAdapterPosition()));
                adapter.notifyItemRemoved(getAdapterPosition());
            } else {
                AssignmentList.assignments.add(adapter.items.get(getAdapterPosition()));
                AssignmentList.completedAssignments.remove(adapter.items.get(getAdapterPosition()));
                adapter.notifyItemRemoved(getAdapterPosition());
            }

        });

    }

    public AssignmentVH linkAdapter(AssignmentAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
