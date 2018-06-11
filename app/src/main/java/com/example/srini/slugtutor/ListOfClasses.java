package com.example.srini.slugtutor;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.Toast;

import java.lang.reflect.Field;
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
    private final Context context = this;
    Button submit;
    String submittedClasses = "";
    String submittedDisplay = "";
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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
        String name = getIntent().getStringExtra("type").toLowerCase();
        new Parser().execute();


        if(name.equals("cmps"))
        {
            arr = Parser.cmps;
        }
        else if(name.equals("acen"))
        {
            arr = Parser.acen;
        }
        else if(name.equals("anth"))
        {
            arr = Parser.anth;
        }
        else if(name.equals("aplx"))
        {
            arr = Parser.aplx;
        }
        else if(name.equals("ams"))
        {
            arr = Parser.ams;
        }
        else if(name.equals("art"))
        {
            arr = Parser.art;
        }
        else if(name.equals("artg"))
        {
            arr = Parser.artg;
        }
        else if(name.equals("astr"))
        {
            arr = Parser.astr;
        }
        else if(name.equals("bme"))
        {
            arr = Parser.bme;
        }
        else if(name.equals("bioc"))
        {
            arr = Parser.bioc;
        }
        else if(name.equals("chem"))
        {
            arr = Parser.chem;
        }
        else if(name.equals("chin"))
        {
            arr = Parser.chin;
        }
        else if(name.equals("clst"))
        {
            arr = Parser.clst;
        }
        else if(name.equals("clni"))
        {
            arr = Parser.clni;
        }
        else if(name.equals("clte"))
        {
            arr = Parser.clte;
        }
        else if(name.equals("cmmu"))
        {
            arr = Parser.cmmu;
        }
        else if(name.equals("cmpm"))
        {
            arr = Parser.cmpm;
        }
        else if(name.equals("cmpe"))
        {
            arr = Parser.cmpe;
        }
        else if(name.equals("cowl"))
        {
            arr = Parser.cowl;
        }
        else if(name.equals("cres"))
        {
            arr = Parser.cres;
        }
        else if(name.equals("crwn"))
        {
            arr = Parser.crwn;
        }
        else if(name.equals("danm"))
        {
            arr = Parser.danm;
        }
        else if(name.equals("eart"))
        {
            arr = Parser.eart;
        }
        else if(name.equals("eeb"))
        {
            arr = Parser.eeb;
        }
        else if(name.equals("econ"))
        {
            arr = Parser.econ;
        }
        else if(name.equals("educ"))
        {
            arr = Parser.educ;
        }
        else if(name.equals("ee"))
        {
            arr = Parser.ee;
        }
        else if(name.equals("envs"))
        {
            arr = Parser.envs;
        }
        else if(name.equals("fmst"))
        {
            arr = Parser.fmst;
        }
        else if(name.equals("film"))
        {
            arr = Parser.film;
        }
        else if(name.equals("fren"))
        {
            arr = Parser.fren;
        }
        else if(name.equals("game"))
        {
            arr = Parser.game;
        }
        else if(name.equals("germ"))
        {
            arr = Parser.germ;
        }
        else if(name.equals("gree"))
        {
            arr = Parser.gree;
        }
        else if(name.equals("hebr"))
        {
            arr = Parser.hebr;
        }
        else if(name.equals("his"))
        {
            arr = Parser.his;
        }
        else if(name.equals("havc"))
        {
            arr = Parser.havc;
        }
        else if(name.equals("hisc"))
        {
            arr = Parser.hisc;
        }
        else if(name.equals("ital"))
        {
            arr = Parser.ital;
        }
        else if(name.equals("japn"))
        {
            arr = Parser.japn;
        }
        else if(name.equals("jwst"))
        {
            arr = Parser.jwst;
        }
        else if(name.equals("laad"))
        {
            arr = Parser.laad;
        }
        else if(name.equals("krsg"))
        {
            arr = Parser.krsg;
        }
        else if(name.equals("lals"))
        {
            arr = Parser.lals;
        }
        else if(name.equals("latn"))
        {
            arr = Parser.latn;
        }
        else if(name.equals("ling"))
        {
            arr = Parser.ling;
        }
        else if(name.equals("lgst"))
        {
            arr = Parser.lgst;
        }
        else if(name.equals("math"))
        {
            arr = Parser.math;
        }
        else if(name.equals("merr"))
        {
            arr = Parser.merr;
        }
        else if(name.equals("mcdb"))
        {
            arr = Parser.mcdb;
        }
        else if(name.equals("metx"))
        {
            arr = Parser.metx;
        }
        else if(name.equals("musc"))
        {
            arr = Parser.musc;
        }
        else if(name.equals("oaks"))
        {
            arr = Parser.oaks;
        }
        else if(name.equals("ocea"))
        {
            arr = Parser.ocea;
        }
        else if(name.equals("phil"))
        {
            arr = Parser.phil;
        }
        else if(name.equals("phye"))
        {
            arr = Parser.phye;
        }
        else if(name.equals("poli"))
        {
            arr = Parser.poli;
        }
        else if(name.equals("prtr"))
        {
            arr = Parser.prtr;
        }
        else if(name.equals("port"))
        {
            arr = Parser.port;
        }
        else if(name.equals("psyc"))
        {
            arr = Parser.psyc;
        }
        else if(name.equals("punj"))
        {
            arr = Parser.punj;
        }
        else if(name.equals("russ"))
        {
            arr = Parser.russ;
        }
        else if(name.equals("crsn"))
        {
            arr = Parser.crsn;
        }
        else if(name.equals("scic"))
        {
            arr = Parser.scic;
        }
        else if(name.equals("socd"))
        {
            arr = Parser.socd;
        }
        else if(name.equals("socy"))
        {
            arr = Parser.socy;
        }
        else if(name.equals("span"))
        {
            arr = Parser.span;
        }
        else if(name.equals("sphs"))
        {
            arr = Parser.sphs;
        }
        else if(name.equals("stev"))
        {
            arr = Parser.stev;
        }
        else if(name.equals("tim"))
        {
            arr = Parser.tim;
        }
        else if(name.equals("thea"))
        {
            arr = Parser.thea;
        }
        else if(name.equals("ucdc"))
        {
            arr = Parser.ucdc;
        }
        else if(name.equals("writ"))
        {
            arr = Parser.writ;
        }
        else if(name.equals("yidd"))
        {
            arr = Parser.yidd;
        }
        else
        {
            arr = Parser.courses;
        }

        final ListOfClassesAdapter classesDisplay = new ListOfClassesAdapter(c, arr);
        lv.setAdapter(classesDisplay);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedItems.size() == 0) {
                    Toast.makeText(c, "You must select at least one class.", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListOfClasses.this);
//                    alertDialog.setTitle("You have no submitted classes. Are you sure you want to proceed?");
//                    alertDialog.setMessage(stringify());
//                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            setDisplayedClassesProperly();
//                            Intent i = new Intent(getApplicationContext(), EntryScreen.class);
//                            startActivity(i);
//                        }
//                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                        }
//                    });
//                    AlertDialog alert = alertDialog.create();
//                    alert.show();
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
                                    FirebaseService firebaseService = new FirebaseService();
                                    for (int i = 0; i < checkedItemsToDisplay.size(); i++) {
                                        final int count = i;
                                        Course course = checkedItemsToDisplay.get(i);
                                        firebaseService.addCourse(course, new Callback() {
                                            @Override
                                            public void callback() {
                                                if (count == checkedItemsToDisplay.size() - 1) {
                                                    Intent i = new Intent(getApplicationContext(), EntryScreen.class);
                                                    startActivity(i);
                                                }
                                            }
                                        });
                                    }
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
                Intent classes = new Intent(ListOfClasses.this, EntryScreen.class);
                startActivity(classes);
                return true;
            case R.id.listingsTransition:
                Intent i = new Intent(ListOfClasses.this, TabGroupActivity.class);
                i.putExtra("isUser", "true");
                startActivity(i);
                return true;
            case R.id.transitionMenu:
                Intent leave = new Intent(ListOfClasses.this, SignInActivity.class);
                startActivity(leave);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


