package com.example.srini.slugtutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.srini.slugtutor.ListOfClasses.getUserCourses;

/**
 * Created by Adnan on 4/16/2018.
 */

public class EntryScreen extends AppCompatActivity{
    FloatingActionButton fb;
    Activity c;
    ListView lvRegClasses;
    static ArrayList<Course> regClasses = getUserCourses();
    RegisteredClassesAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_screen);

        c = this;

        fb = findViewById(R.id.floatingActionButton);
        lvRegClasses = findViewById(R.id.registered_classes);

        FirebaseService firebaseService = new FirebaseService();
        firebaseService.getUserClasses(new CallbackCourses() {
            @Override
            public void callback(List<Course> courses) {
                adapter = new RegisteredClassesAdapter(c, courses);
                lvRegClasses.setAdapter(adapter);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListOfClasses.class);
                startActivity(i);
            }
        });
    }

}
