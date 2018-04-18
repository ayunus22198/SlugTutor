package com.example.srini.slugtutor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import static com.example.srini.slugtutor.Parser.getCoursesArr;

/**
 * Created by Adnan on 4/16/2018.
 */

public class ListOfClasses  extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener {
    ListView lv;
    SearchView sv;
    Activity c;
    private static ArrayList<Course> checkedItems = new ArrayList<Course>();
    private static ArrayList<Course> checkedItemsToDisplay = new ArrayList<Course>();
    private static ArrayList<Course> arr;
    Button submit;
    String submittedClasses = "";
    String submittedDisplay = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_classes);
        submit = (Button) findViewById(R.id.button2);
        c = this;
        lv = (ListView) findViewById(R.id.list_of_classes_1);
        sv = (SearchView) findViewById(R.id.searchQuery);
        if(checkedItems.size() != 0) {
            checkedItems.clear();
        }
        arr = getCoursesArr();
        final ListOfClassesAdapter classesDisplay = new ListOfClassesAdapter(c, arr);
        lv.setAdapter(classesDisplay);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedItems.size() == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(ListOfClasses.this).create();
                    alertDialog.setTitle("No Submitted Classes");
                    alertDialog.setMessage("You haven't submitted any classes. Are you sure you want to proceed?");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getApplicationContext(), EntryScreen.class);
                                    startActivity(i);
                                }
                            });
                    alertDialog.show();
                }
                else
                {

                    AlertDialog alertDialog = new AlertDialog.Builder(ListOfClasses.this).create();
                    alertDialog.setTitle("Submitted Classes");
                    alertDialog.setMessage(stringify());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    setDisplayedClassesProperly();
                                    Intent i = new Intent(getApplicationContext(), EntryScreen.class);
                                    startActivity(i);
                                }
                            });
                    alertDialog.show();
                }

            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                classesDisplay.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION)
        {
            Course courseOfUser = arr.get(pos);
            if(isChecked) {
                if(!checkedItems.contains(courseOfUser)) {
                    checkedItems.add(courseOfUser);
                }
            }
            if(!isChecked)
            {
                if(checkedItems.contains(courseOfUser)) {
                    checkedItems.remove(courseOfUser);
                }
            }
        }
    }

    public String stringify()
    {
        for(int i = 0;i<checkedItems.size();i++)
        {
            submittedClasses += checkedItems.get(i).getCourseNum() + "\n";
        }
        return submittedClasses;
    }

    public static ArrayList<Course> getUserCourses() {
        return checkedItemsToDisplay;
    }

    public void setDisplayedClassesProperly() {
        for (int i = 0; i < checkedItems.size(); i++) {
            if(checkedItemsToDisplay.contains(checkedItems.get(i))) {
                continue;
            }
            else
            {
                checkedItemsToDisplay.add(checkedItems.get(i));
            }
        }
    }
}


