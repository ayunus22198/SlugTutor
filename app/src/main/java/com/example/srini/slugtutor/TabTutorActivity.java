package com.example.srini.slugtutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class TabTutorActivity extends AppCompatActivity {
    private final Context context = this;
    private String isUser;
    private boolean decision;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final Course classData = (Course)getIntent().getSerializableExtra("classData");
        System.out.println(classData);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Set Basic ui
        setSupportActionBar(toolbar);
        if(classData != null)
        getSupportActionBar().setTitle(classData.getCourseNum() + " - Tutors");
        toolbar.setSubtitle("LocSilence");

        //System.out.println(getIntent().getParcelableExtra("classData"));
        isUser = getIntent().getStringExtra("isUser");
        decision = Boolean.valueOf(isUser);
        final ListView listView = findViewById(R.id.listView);
        final View navigator = findViewById(R.id.navigator);
        final Button studentButton = navigator.findViewById(R.id.student_button);
        final Button groupButton = navigator.findViewById(R.id.group_button);
        final Button tutorButton = navigator.findViewById(R.id.tutor_button);
        tutorButton.setBackground(ContextCompat.getDrawable(this, R.drawable.tab_main_background));
        final FloatingActionButton postingButton = findViewById(R.id.floatingActionButton);

        FirebaseService firebaseService = new FirebaseService();
        if(!decision) {
            firebaseService.getTutorListings(new CallbackListings() {
                @Override
                public void callback(List<Listing> listings) {
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listings);
                    listView.setAdapter(adapter);
                }

            });
        }
        else {
            firebaseService.getUserTutorListings(new CallbackListings() {
                @Override
                public void callback(List<Listing> listings) {
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listings);
                    listView.setAdapter(adapter);
                }

            });
        }
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TabTutorActivity.this, TabStudentActivity.class);
                i.putExtra("isUser", isUser);
                i.putExtra("classData", classData);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TabTutorActivity.this, TabGroupActivity.class);
                i.putExtra("isUser", isUser);
                i.putExtra("classData", classData);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        postingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,CreateEventActivity.class);
                i.putExtra("type","tutor");
                i.putExtra("classData", classData);
                startActivity(i);
                finish();
            }
        });


    }
}
