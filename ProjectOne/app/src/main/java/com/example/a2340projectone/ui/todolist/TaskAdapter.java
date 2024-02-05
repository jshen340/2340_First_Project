package com.example.a2340projectone.ui.todolist;

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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH>{
    List<Task> items;
    Context context;

    public TaskAdapter(List<Task> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        holder.taskName.setText(items.get(position).getName());
        holder.taskCourse.setText("Category: " + items.get(position).getCourse());
        holder.taskDue.setText("Due Date: " + items.get(position).getDue());
        holder.checkBox.setChecked(items.get(position).isComplete() ? true : false);

        holder.itemView.findViewById(R.id.editItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView((R.layout.fragment_fill_information_screen_task));

                EditText editCourse = dialog.findViewById(R.id.category_fill);
                EditText editDate = dialog.findViewById(R.id.duedate_fill);
                EditText editText = dialog.findViewById(R.id.task_fill);
                Button updateBtn = dialog.findViewById(R.id.confirm_task);
                TextView title = dialog.findViewById(R.id.textView);
                title.setText("Edit Task");

                updateBtn.setText("UPDATE");

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                        items.set(position, new Task(name, course, date));
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

    class TaskVH extends RecyclerView.ViewHolder {
        TextView taskName, taskCourse, taskDue;
        CheckBox checkBox;
        public TaskVH(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_Title);
            taskDue = itemView.findViewById(R.id.dueDate);
            taskCourse = itemView.findViewById(R.id.body);
            checkBox = itemView.findViewById(R.id.todoCheckBox);

            checkBox.setOnClickListener(view -> {
                items.get(getAdapterPosition()).toggle();
            });
            itemView.findViewById(R.id.deleteItem2).setOnClickListener(view -> {
                items.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });
        }

    }
}


