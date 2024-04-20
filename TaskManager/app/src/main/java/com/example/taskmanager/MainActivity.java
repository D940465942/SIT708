package com.example.taskmanager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {
    private List<Task> taskList;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private SQLiteDatabase sQLiteDatabase;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        sQLiteDatabase = dbHelper.getWritableDatabase();
        swipeRefreshLayout = findViewById(R.id.main_page);

        findViewById(R.id.btn_add_task).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditTask.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recycleriew_task);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList = new ArrayList<>();
        fetch_tasks();
        adapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                fetch_tasks();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fetch_tasks();
    }

    @Override
    public void onTaskClick(Task task) {
        Intent intent = new Intent(this, TaskDetail.class);
        intent.putExtra("task_id", task.getId());
        startActivity(intent);
    }

    @SuppressLint("Range")
    private void fetch_tasks() {
        taskList.clear();
        Cursor cursor = sQLiteDatabase.query("tasks", null, null, null, null, null,"due_date DESC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                taskList.add(new Task(cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getString(cursor.getColumnIndex("due_date"))));
            } while (cursor.moveToNext());
            cursor.close();
        }
        recyclerView.setAdapter(adapter);
    }
}
