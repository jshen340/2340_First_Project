package com.example.a2340projectone.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

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
    private AssignmentAdapter adapter;
    public AssignmentVH(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.assignment_title);
        courseName = itemView.findViewById(R.id.course_Title);
        taskDue = itemView.findViewById(R.id.assignment_dueDate_Title);
        checkbox = itemView.findViewById(R.id.assignmentCompleted);
        itemView.findViewById(R.id.deleteAssignment).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
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
