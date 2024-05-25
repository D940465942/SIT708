package com.example.LostFoundApp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = findViewById(R.id.createButton);
        Button showButton = findViewById(R.id.showButton);
        Button showOnMapButton = findViewById(R.id.showOnMapButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCreateAdvert = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intentCreateAdvert);
            }
        });

        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowOnMap = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intentShowOnMap);
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentShowItems = new Intent(MainActivity.this, ListAllActivity.class);
                startActivity(intentShowItems);
            }
        });

    }
}
