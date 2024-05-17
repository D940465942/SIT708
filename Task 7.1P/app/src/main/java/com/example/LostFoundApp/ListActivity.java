package com.example.LostFoundApp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DBHelper dbHelper = new DBHelper(this);
        List<DBHelper.ItemDescription> itemList = dbHelper.getAllItems();
        LostAndFoundAdapter adapter = new LostAndFoundAdapter(itemList);
        adapter.setOnItemClickListener(itemId -> {
            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            intent.putExtra("item_id", itemId);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
