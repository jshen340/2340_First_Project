package com.example.a2340projectone.ui.exams;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamVH> {

    List<Exam> items;
    Context context;

    public ExamAdapter(List<Exam> items, Context context) {
        this.items = items;
        this.context = context;
    }
    @NonNull
    @Override
    public ExamVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ExamVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamVH holder, int position) {
        holder.examName.setText("Exam: " + items.get(position).getName());
        holder.examCourse.setText("Course: " + items.get(position).getCourse());
        holder.examTime.setText("Date/Time: " + items.get(position).getDueDate() + " at " + items.get(position).getTime());
        holder.examLocation.setText("Location: " + items.get(position).getLocation());
        holder.itemView.findViewById(R.id.editExam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.fragment_add_exam);

                EditText editName = dialog.findViewById(R.id.examTitle);
                EditText editCourse = dialog.findViewById(R.id.courseTitle);
                EditText editTime = dialog.findViewById(R.id.timeTitle);
                EditText editLocation = dialog.findViewById(R.id.locationTitle);
                EditText editDate = dialog.findViewById(R.id.dateTitle);
                Button updateBtn = dialog.findViewById(R.id.confirm);
                TextView title = dialog.findViewById(R.id.header);

                title.setText("Edit Exam");
                updateBtn.setText("UPDATE");

                editName.setText((items.get(position).getName()));
                editCourse.setText((items.get(position).getCourse()));
                editTime.setText((items.get(position).getTime()));
                editLocation.setText((items.get(position).getLocation()));
                editDate.setText((items.get(position).getDueDate()));
                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", date = "", course = "", time = "", location = "";
                        if (!editName.getText().toString().equals("")) {
                            name = editName.getText().toString();
                        }
                        if (!editCourse.getText().toString().equals("")) {
                            course = editCourse.getText().toString();
                        }
                        if (!editTime.getText().toString().equals("")) {
                            time = editTime.getText().toString();
                        }
                        if (!editLocation.getText().toString().equals("")) {
                            location = editLocation.getText().toString();
                        }
                        if (!editDate.getText().toString().equals("")) {
                            date = editDate.getText().toString();
                        }
                        items.set(position, new Exam(name, date, course, time, location));
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


    class ExamVH extends RecyclerView.ViewHolder {

        TextView examName;
        TextView examCourse;
        TextView examTime;
        TextView examLocation;
        public ExamVH(@NonNull View itemView) {
            super(itemView);

            examName = itemView.findViewById(R.id.task_Title);
            examCourse = itemView.findViewById(R.id.task);
            examTime = itemView.findViewById(R.id.body);
            examLocation = itemView.findViewById(R.id.dueDate);
            itemView.findViewById(R.id.deleteItem2).setOnClickListener(view -> {
                items.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            });
        }
    }

}
