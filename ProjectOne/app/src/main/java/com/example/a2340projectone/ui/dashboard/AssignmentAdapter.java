package com.example.a2340projectone.ui.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentVH>{
    List<Assignment> items;
    Context context;

    public AssignmentAdapter(List<Assignment> items, Context context) {

        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentVH(view);
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
                Dialog dialog = new Dialog(context);
                dialog.setContentView((R.layout.fragment_add_assignment));

                EditText editText = dialog.findViewById(R.id.assignment_name_fill);
                EditText editCourse = dialog.findViewById(R.id.assignment_course_fill);
                EditText editDate = dialog.findViewById(R.id.assignment_duedate_fill);
                Button updateBtn = dialog.findViewById(R.id.add_assignment_button);
                TextView title = dialog.findViewById(R.id.textView2);

                title.setText("Edit Assignment");

                updateBtn.setText("UPDATE");

                editText.setText((items.get(position).getName()));
                editCourse.setText((items.get(position).getCourse()));
                editDate.setText((items.get(position).getDue()));

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "", course = "", date = "";

                        if (!editText.getText().toString().equals("")) {
                            name = editText.getText().toString();
                        }
                        if (!editCourse.getText().toString().equals("")) {
                            course = editCourse.getText().toString();
                        }
                        if (!editDate.getText().toString().equals("")) {
                            date = editDate.getText().toString();
                        }

                        items.set(position, new Assignment(name, course, date));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000, 1500);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class AssignmentVH extends RecyclerView.ViewHolder {
        TextView courseName;
        TextView taskName;
        TextView taskDue;
        CheckBox checkbox;
        private AssignmentAdapter adapter;
        public AssignmentVH(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.assignment_title);
            courseName = itemView.findViewById(R.id.course_Title);
            taskDue = itemView.findViewById(R.id.assignment_dueDate_Title);
            checkbox = itemView.findViewById(R.id.assignmentCompleted);
            itemView.findViewById(R.id.deleteAssignment).setOnClickListener(view -> {
                items.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });
            checkbox.setOnClickListener(view -> {
                items.get(getAdapterPosition()).toggle();
                if (checkbox.isChecked()) {
                    AssignmentList.completedAssignments.add(items.get(getAdapterPosition()));
                    AssignmentList.assignments.remove(items.get(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                } else {
                    AssignmentList.assignments.add(items.get(getAdapterPosition()));
                    AssignmentList.completedAssignments.remove(items.get(getAdapterPosition()));
                    notifyItemRemoved(getAdapterPosition());
                }

            });

        }
    }

}

