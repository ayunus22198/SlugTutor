package com.example.srini.slugtutor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.classesTransition:
                Toast.makeText(EntryScreen.this, "Classes Page", Toast.LENGTH_LONG).show();
                return true;
            case R.id.listingsTransition:
                Intent i = new Intent(EntryScreen.this, TabGroupActivity.class);
                i.putExtra("isUser", "true");
                startActivity(i);
                return true;
            case R.id.transitionMenu:
                Intent leave = new Intent(EntryScreen.this, SignInActivity.class);
                startActivity(leave);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
