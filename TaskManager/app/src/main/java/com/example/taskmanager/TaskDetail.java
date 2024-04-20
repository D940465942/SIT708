package com.example.taskmanager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetail extends AppCompatActivity {
    private TextView textview_title, textview_description, textview_due_date;
    private Button edit_task, delete_task;
    private DBHelper dbHelper;
    private SQLiteDatabase sQLiteDatabase;
    private long task_id;

    @SuppressLint({"Range", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.task_detail);
        dbHelper = new DBHelper(this);
        sQLiteDatabase = dbHelper.getWritableDatabase();
        textview_title = findViewById(R.id.textview_title);
        textview_description = findViewById(R.id.textview_description);
        textview_due_date = findViewById(R.id.textview_due_date);

        task_id = getIntent().getLongExtra("task_id", -1);
        if (task_id != -1) {
            Cursor cursor = sQLiteDatabase.query("tasks", new String[]{"id, title, description, due_date"}, "id=?", new String[]{String.valueOf(task_id)}, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                textview_title.setText("Title: " + cursor.getString(cursor.getColumnIndex("title")));
                textview_description.setText("Description: " + cursor.getString(cursor.getColumnIndex("description")));
                textview_due_date.setText("Due Date: " + cursor.getString(cursor.getColumnIndex("due_date")));
                cursor.close();
            }
        }

        edit_task = findViewById(R.id.button_edit_task);

        edit_task.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditTask.class);
            intent.putExtra("task_id", task_id);
            intent.putExtra("title", textview_title.getText().toString());
            intent.putExtra("description", textview_description.getText().toString());
            intent.putExtra("due_date", textview_due_date.getText().toString());
            startActivity(intent);
            finish();
        });

        delete_task = findViewById(R.id.button_delete_task);
        delete_task.setOnClickListener(v -> {
            sQLiteDatabase.delete("tasks", "id=?", new String[]{String.valueOf(task_id)});
            Toast.makeText(this, "Task has been successfully deleted!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}