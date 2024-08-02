package com.prajithravisankar.simplereminderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CardView schoolCard;
    CardView officeCard;
    CardView personalCard;
    CardView healthCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        schoolCard = findViewById(R.id.card_view_school);
        officeCard = findViewById(R.id.card_view_office);
        personalCard = findViewById(R.id.card_view_personal);
        healthCard = findViewById(R.id.card_view_health);

        schoolCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSchoolActivity = new Intent(MainActivity.this, SchoolActivity.class);
                startActivity(goToSchoolActivity);
            }
        });

        officeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOfficeActivity = new Intent(MainActivity.this, OfficeActivity.class);
                startActivity(goToOfficeActivity);
            }
        });

        personalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to personalView
                Intent goToPersonalActivity = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(goToPersonalActivity);
            }
        });

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to healthView
                Intent goToHealthActivity = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(goToHealthActivity);
            }
        });

    }
}