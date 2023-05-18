package com.example.a71p;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.a71p.DatabaseClass;
import com.example.a71p.LostAndFoundModel;


public class CreateNewAdverActivity extends AppCompatActivity {

    private RadioButton radioButtonLost;
    private RadioButton radioButtonFound;
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;
    private EditText locationEditText;
    private ImageView datePicker;
    private DatabaseClass database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_adver);

        // Initialize the database
        database = new DatabaseClass(this);

        // Initialize views
        radioButtonLost = findViewById(R.id.lost);
        radioButtonFound = findViewById(R.id.found);
        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phone);
        descriptionEditText = findViewById(R.id.description);
        dateEditText = findViewById(R.id.date);
        locationEditText = findViewById(R.id.location);
        datePicker = findViewById(R.id.datePicker);

        // Set click listener for the date picker
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNewAdverActivity.this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dateEditText.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                });
                datePickerDialog.show();
            }
        });

        // Set click listener for the save button
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Insert data into the database
                database.insertData(new LostAndFoundModel(
                        radioButtonLost.isChecked() ? "Lost" : "Found",
                        nameEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        dateEditText.getText().toString(),
                        locationEditText.getText().toString(),
                        0
                ));



                // Finish the current activity
                finish();
            }
        });
    }
}
