package com.example.srini.slugtutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivityThroughUser extends AppCompatActivity {
    private final Context context = this;
    Spinner classes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity_through_user);
        classes = (Spinner) findViewById(R.id.spinner);
        final FirebaseService firebaseService = new FirebaseService();
        firebaseService.getUserClasses(new CallbackCourses() {
            @Override
            public void callback(List<Course> listings) {
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listings);
                classes.setAdapter(adapter);
            }

        });
        final Course classData = (Course)getIntent().getSerializableExtra("classData");
        System.out.println(classData);
        final EditText text = findViewById(R.id.editText);
        final Button enter = findViewById(R.id.button2);
        Button b = findViewById(R.id.button2);

        Intent i = getIntent();
        final String type = i.getStringExtra("type");
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text.getText().toString().equals("")) {
                    Intent i = new Intent();
                    switch (type) {
                        case "student":
                            i = new Intent(context, TabStudentActivity.class);
                            firebaseService.addStudentListing(text.getText().toString());
                            break;
                        case "group":
                            i = new Intent(context, TabGroupActivity.class);
                            firebaseService.addGroupListing(text.getText().toString());
                            break;
                        case "tutor":
                            i = new Intent(context, TabTutorActivity.class);
                            firebaseService.addTutorListing(text.getText().toString());
                            break;
                    }
                    i.putExtra("classData", classData);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }

            }
        });
    }

}

