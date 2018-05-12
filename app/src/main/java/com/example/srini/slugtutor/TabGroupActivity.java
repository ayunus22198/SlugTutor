package com.example.srini.slugtutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

public class TabGroupActivity extends AppCompatActivity {
    private final Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set Basic ui
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Group");
        toolbar.setSubtitle("LocSilence");

        System.out.println(getIntent().getParcelableExtra("classData"));

        final ListView listView = findViewById(R.id.listView);
        final View navigator = findViewById(R.id.navigator);
        final Button studentButton = navigator.findViewById(R.id.student_button);
        final Button tutorButton = navigator.findViewById(R.id.tutor_button);

        FirebaseService firebaseService = new FirebaseService();

        firebaseService.getGroupListings(new CallbackListings() {
            @Override
            public void callback(List<Listing> listings) {
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listings);
                listView.setAdapter(adapter);
            }

        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TabGroupActivity.this, TabStudentActivity.class));
                finish();
            }
        });

        tutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TabGroupActivity.this, TabTutorActivity.class));
                finish();
            }
        });

    }
}
