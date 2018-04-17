package com.example.srini.slugtutor;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, Void> {
    public ArrayList<Course> courses;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... params) {
        String url = "https://registrar.ucsc.edu/catalog/programs-courses/course-descriptions/cmps.html";
        try {
            Document document = Jsoup.connect(url).get();
            Elements data = document.select("p");
            courses = new ArrayList<Course>();

            String names = data.toString();


            String[] nameArray = names.split("\n");

            ArrayList<String> temp = new ArrayList<String>();
            for (String s : nameArray) {
                if (s.contains("<p><strong>")) {
                    s = s.replace("<p>", "");
                    s = s.replace("<strong>", "");
                    s = s.replace("</p>", "");
                    s = s.replace("</strong>", "");
                    s = s.replace("<em>", "");
                    s = s.replace("</em>", "");
                    s = s.replace("<br>", "");
                    temp.add(s);
                }
            }
            for (String s : temp) {
                String[] t = s.split("\\.");
                if (t[2].contains("notoffered")) {
                    courses.add(new Course("CMPS " + t[0], "NOT OFFERED", "NOT OFFERED"));
                } else {
                    courses.add(new Course("CMPS " + t[0], t[t.length - 1], t[1]));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

    }
}
