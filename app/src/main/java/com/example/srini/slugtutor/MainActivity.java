package com.example.srini.slugtutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import com.example.srini.slugtutor.Course;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = "MyActivity";
    ArrayList<Course> courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Parser().execute();
    }

    private class Parser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params){
            String url = "https://registrar.ucsc.edu/catalog/programs-courses/course-descriptions/cmps.html";
            try {
                Document document = Jsoup.connect(url).get();
                Elements data=document.select("p");
                courses = new ArrayList<Course>();

                String names = data.toString();


                String[] nameArray = names.split("\n");

                ArrayList<String> temp = new ArrayList<String>();
                for(String s:nameArray)
                {
                    if(s.contains("<p><strong>"))
                    {
                        s = s.replace("<p>","");
                        s = s.replace("<strong>","");
                        s = s.replace("</p>","");
                        s = s.replace("</strong>","");
                        s = s.replace("<em>","");
                        s = s.replace("</em>","");
                        s = s.replace("<br>","");
                        temp.add(s);
                    }
                }
                for(String s:temp)
                {
                    String[] t = s.split("\\.");
                    if(t[2].contains("notoffered"))
                    {
                        courses.add(new Course("CMPS "+t[0],"NOT OFFERED","NOT OFFERED"));
                    }
                    else
                    {
                        courses.add(new Course("CMPS "+t[0],t[t.length-1],t[1]));
                    }

                }


                Log.wtf(LOG,courses.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}
