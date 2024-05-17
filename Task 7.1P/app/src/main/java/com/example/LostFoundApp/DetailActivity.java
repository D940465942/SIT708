package com.example.LostFoundApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView idTextView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        dbHelper = new DBHelper(this);
        idTextView = findViewById(R.id.idTextView);
        TextView postTypeValue = findViewById(R.id.postTypeValue);
        TextView nameValue = findViewById(R.id.nameValue);
        TextView phoneValue = findViewById(R.id.phoneValue);
        TextView descriptionValue = findViewById(R.id.descriptionValue);
        TextView dateValue = findViewById(R.id.dateValue);
        TextView locationValue = findViewById(R.id.locationValue);
        Button removeButton = findViewById(R.id.removebutton);

        Intent intent = getIntent();
        int itemId = intent.getIntExtra("item_id", -1);

        if (itemId == -1) {
            Toast.makeText(this, "ID is invalid", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        idTextView.setText(String.valueOf(itemId));
        LostAndFoundItem item = dbHelper.getItemById(itemId);

        if (item == null) {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        postTypeValue.setText(item.getPostType());
        nameValue.setText(item.getName());
        phoneValue.setText(item.getPhone());
        descriptionValue.setText(item.getDescription());
        dateValue.setText(item.getDate());
        locationValue.setText(item.getLocation());
        removeButton.setOnClickListener(v -> {
            int idToDelete = Integer.parseInt(idTextView.getText().toString());
            dbHelper.deleteItem(idToDelete);
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
            Intent backToMain = new Intent(this, MainActivity.class);
            startActivity(backToMain);
            finish();
        });
    }
}
