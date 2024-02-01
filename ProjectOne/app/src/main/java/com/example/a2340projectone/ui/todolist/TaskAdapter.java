package com.example.a2340projectone.ui.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskVH>{
    List<Task> items;

    public TaskAdapter(List<Task> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        holder.taskName.setText(items.get(position).getName());
        holder.taskCourse.setText("Category: " + items.get(position).getCourse());
        holder.taskDue.setText("Due Date: " + items.get(position).getDue());
        holder.checkBox.setChecked(items.get(position).isComplete() ? true : false);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class TaskVH extends RecyclerView.ViewHolder {
    TextView taskName, taskCourse, taskDue;

    CheckBox checkBox;
    private TaskAdapter adapter;
    public TaskVH(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.task_Title);
        taskDue = itemView.findViewById(R.id.dueDate);
        taskCourse = itemView.findViewById(R.id.body);
        checkBox = itemView.findViewById(R.id.todoCheckBox);

        checkBox.setOnClickListener(view -> {
            adapter.items.get(getAdapterPosition()).toggle();
        });
        itemView.findViewById(R.id.deleteItem2).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public TaskVH linkAdapter(TaskAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
