package com.example.a2340projectone.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.courseName.setText(items.get(position).getName());
        holder.taskName.setText(items.get(position).getCourse());
        holder.taskDue.setText(items.get(position).getDue());
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
    private AssignmentAdapter adapter;
    public AssignmentVH(@NonNull View itemView) {
        super(itemView);
        courseName = itemView.findViewById(R.id.course_Title);
        taskDue = itemView.findViewById(R.id.assignment_dueDate_Title);
        taskName = itemView.findViewById(R.id.assignment_title);
        itemView.findViewById(R.id.deleteAssignment).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public AssignmentVH linkAdapter(AssignmentAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
