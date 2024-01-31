package com.example.a2340projectone.todo_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.a2340projectone.Adapter.ToDoAdapter;
import com.example.a2340projectone.AddNewTask;
import com.example.a2340projectone.MainActivity;
import com.example.a2340projectone.R;
import com.example.a2340projectone.RecyclerItemTouchHelper;
import com.example.a2340projectone.Utils.DatabaseHandler;

import com.example.a2340projectone.databinding.ActivityMainBinding;
import com.example.a2340projectone.databinding.ActivityTodoBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import java.util.Collections;


public class TodoActivity extends AppCompatActivity {
    private Button close;
    private ActivityTodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        close = findViewById(R.id.close);

        DatabaseHandler db = new DatabaseHandler(this);
        db.openDatabase();

        RecyclerView tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ToDoAdapter tasksAdapter = new ToDoAdapter(db, TodoActivity.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);

        List<ToDoModel> taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TodoActivity.this, MainActivity.class));
            }
        });
//        @Override
//        public boolean onCreateOptionsMenu (Menu menu){
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.list_menu, false);
//            return true;
//        }
//
//        @Override
//        public boolean onOptionsItemSelected (MenuItem item){
//            if
//        }
    }
}