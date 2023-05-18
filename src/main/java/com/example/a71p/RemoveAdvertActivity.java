package com.example.a71p;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveAdvertActivity extends AppCompatActivity {

    private TextView textViewHeading;
    private TextView textViewDetail;
    private DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_advert);

        // Initialize the DatabaseClass
        databaseClass = new DatabaseClass(this);

        textViewHeading = findViewById(R.id.heading);
        textViewDetail = findViewById(R.id.detail);

        int id = getIntent().getIntExtra("id", -1);

        // Check if a valid ID was received from the previous activity
        if (id != -1) {
            // Retrieve the LostAndFoundModel from the database using the ID
            LostAndFoundModel lostAndFoundModel = databaseClass.getDataById(String.valueOf(id));

            // Set the heading TextView with the "isLostOrFound" and "name" properties
            textViewHeading.setText(lostAndFoundModel != null ? lostAndFoundModel.getIsLostOrFound() + ": " + lostAndFoundModel.getName() : "");

            // Set the detail TextView with the formatted details of the LostAndFoundModel
            textViewDetail.setText(new StringBuilder()
                    .append(lostAndFoundModel != null ? lostAndFoundModel.getDate() : "").append("\n")
                    .append(lostAndFoundModel != null ? lostAndFoundModel.getLocation() : "").append("\n")
                    .append(lostAndFoundModel != null ? lostAndFoundModel.getPhone() : "").append("\n")
                    .append(lostAndFoundModel != null ? lostAndFoundModel.getDescription() : "")
                    .toString());
        }

        // Set a click listener for the remove button
        Button removeButton = findViewById(R.id.remove);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a valid ID was received from the previous activity
                if (id != -1) {
                    // Delete the data from the database using the ID
                    databaseClass.deleteDataById(id);
                    finish();
                }
            }
        });
    }
}