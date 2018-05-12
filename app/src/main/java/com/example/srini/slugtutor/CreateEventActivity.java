package com.example.srini.slugtutor;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class CreateEventActivity extends AppCompatActivity {

    private final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_activity);

        final EditText text = findViewById(R.id.editText);
        final Button enter = findViewById(R.id.button2);
        Button b = findViewById(R.id.button2);

        final String s = text.getText().toString();
        Intent i = getIntent();
        final String type = i.getStringExtra("type");
        Toast.makeText(this,type, Toast.LENGTH_LONG).show();

        final FirebaseService firebaseService = new FirebaseService();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("student"))
                {
                    Intent i = new Intent(context,TabStudentActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    firebaseService.addStudentListing(s);
                    startActivity(i);
                }
                else if(type.equals("group"))
                {
                    Intent i = new Intent(context,TabGroupActivity.class);
                    firebaseService.addGroupListing(s);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else if(type.equals("tutor")) {
                    Intent i = new Intent(context, TabTutorActivity.class);
                    firebaseService.addTutorListing(s);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

            }
        });
    }


}
