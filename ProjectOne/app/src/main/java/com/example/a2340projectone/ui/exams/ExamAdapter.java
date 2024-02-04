package com.example.a2340projectone.ui.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamVH> {

    List<Exam> items;

    public ExamAdapter(List<Exam> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public ExamVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ExamVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamVH holder, int position) {
        holder.examName.setText(items.get(position).getName());
        holder.examCourse.setText(items.get(position).getCourse());
        holder.examTime.setText(items.get(position).getDueDate() + " at " + items.get(position).getTime());
        holder.examLocation.setText("@ " + items.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class ExamVH extends RecyclerView.ViewHolder {

    TextView examName;
    TextView examCourse;
    TextView examTime;
    TextView examLocation;
    private ExamAdapter adapter;
    public ExamVH(@NonNull View itemView) {
        super(itemView);

        examName = itemView.findViewById(R.id.task_Title);
        examCourse = itemView.findViewById(R.id.task);
        examTime = itemView.findViewById(R.id.body);
        examLocation = itemView.findViewById(R.id.dueDate);
        itemView.findViewById(R.id.deleteItem2).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public ExamVH linkAdapter(ExamAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
