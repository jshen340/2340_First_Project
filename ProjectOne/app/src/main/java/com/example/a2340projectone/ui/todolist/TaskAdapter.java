package com.example.a2340projectone.ui.todolist;

import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.ui.dashboard.AssignmentList;

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

        holder.itemView.findViewById(R.id.editItem2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView((R.layout.fragment_fill_information_screen_task));

                EditText editText = dialog.findViewById(R.id.task_fill);
                EditText editCourse = dialog.findViewById(R.id.category_fill);
                EditText editDate = dialog.findViewById(R.id.duedate_fill);
                Button updateBtn = dialog.findViewById(R.id.confirm);
                TextView title = dialog.findViewById(R.id.textView);

                title.setText("Edit Task");

                updateBtn.setText("UPDATE");

                editText.setText((items.get(holder.getAdapterPosition()).getName()));
                editCourse.setText((items.get(holder.getAdapterPosition()).getCourse()));
                editDate.setText((items.get(holder.getAdapterPosition()).getDue()));

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
                        Task toBeAdded = new Task(name, course);
                        if (toBeAdded.checkDate(date)) {
                            toBeAdded.setDate(date);
                            items.set(holder.getAdapterPosition(), toBeAdded);
                            notifyItemChanged(holder.getAdapterPosition());
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
            if (checkBox.isChecked()) {
                TaskList.completedTasks.add(adapter.items.get(getAdapterPosition()));
                TaskList.tasks.remove(adapter.items.get(getAdapterPosition()));
                adapter.notifyItemRemoved(getAdapterPosition());
            } else {
                TaskList.tasks.add(adapter.items.get(getAdapterPosition()));
                TaskList.completedTasks.remove(adapter.items.get(getAdapterPosition()));
                adapter.notifyItemRemoved(getAdapterPosition());
            }
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
