package com.example.LostFoundApp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;
    private EditText locationEditText;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);
        dbHelper = new DBHelper(this);
        radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioLost = findViewById(R.id.radio_lost);
        RadioButton radioFound = findViewById(R.id.radio_found);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        locationEditText = findViewById(R.id.locationEditText);
        EditText dateEditText = findViewById(R.id.dateEditText);
        Button saveButton = findViewById(R.id.savebutton);

        if (radioGroup == null || radioLost == null || radioFound == null || nameEditText == null ||
                phoneEditText == null || descriptionEditText == null || locationEditText == null || dateEditText == null || saveButton == null) {
            Toast.makeText(this, "Cannot Initialize", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        saveButton.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedButton = findViewById(checkedId);

            if (selectedButton == null) {
                Toast.makeText(this, "Select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            String postType = selectedButton.getText().toString();
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String location = locationEditText.getText().toString();
            String date = dateEditText.getText().toString();

            dbHelper.insertItem(postType, name, phone, description, date, location);

            Toast.makeText(CreateActivity.this, "Item saved", Toast.LENGTH_SHORT).show();
            finish(); // Close the current activity
        });
    }
}
