package com.example.taskmanager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTask extends AppCompatActivity {
    private EditText edittext_title, edittext_description, edittext_duedate;
    private TextView title;
    private Button button_save_task;
    private DBHelper dbHelper;
    private SQLiteDatabase sQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_edit_task);
        edittext_title = findViewById(R.id.edittext_title);
        edittext_description = findViewById(R.id.edittext_description);
        edittext_duedate = findViewById(R.id.edittext_duedate);
        button_save_task = findViewById(R.id.btnAddTaskToDb);
        title = findViewById(R.id.test);

        long task_id = getIntent().getLongExtra("task_id", -1);
        if(task_id != -1) {
            edittext_title.setText(getIntent().getStringExtra("title"));
            edittext_description.setText(getIntent().getStringExtra("description"));
            edittext_duedate.setText(getIntent().getStringExtra("due_date"));
        }

        dbHelper = new DBHelper(this);
        sQLiteDatabase = dbHelper.getWritableDatabase();
        button_save_task.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("title", edittext_title.getText().toString());
            values.put("description", edittext_description.getText().toString());
            values.put("due_date", edittext_duedate.getText().toString());
            sQLiteDatabase.insert("tasks", null, values);
            Toast.makeText(this, "Task has been saved successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}