package com.example.srini.slugtutor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Locale;

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
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListOfClasses.this);
                    alertDialog.setTitle("You have no submitted classes. Are you suer you want to proceed?");
                    alertDialog.setMessage(stringify());
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setDisplayedClassesProperly();
                            Intent i = new Intent(getApplicationContext(), EntryScreen.class);
                            startActivity(i);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
                }
                else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListOfClasses.this);
                    alertDialog.setTitle("Submitted Classes");
                    alertDialog.setMessage(stringify());
                    alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setDisplayedClassesProperly();
                                    Intent i = new Intent(getApplicationContext(), EntryScreen.class);
                                    startActivity(i);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                    });
                    AlertDialog alert = alertDialog.create();
                    alert.show();
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
                if(item.isChecked()) {
                    item.setChecked(false);
                }
                else {
                    item.setChecked(true);
                }
            case R.id.listingsTransition:
                Intent i = new Intent(ListOfClasses.this, TabGroupActivity.class);
                i.putExtra("isUser", "true");
                startActivity(i);
            case R.id.transitionMenu:
                if(item.isChecked()) {
                    item.setChecked(false);
                }
                else {
                    item.setChecked(true);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


