package com.example.srini.slugtutor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adnan on 4/17/2018.
 */

public class ListOfClassesAdapter extends ArrayAdapter<Course>  {
    private Activity context;
    private ArrayList<Course> items;


    public ListOfClassesAdapter(Activity context, ArrayList<Course> items) {
        super(context, R.layout.list_of_classes_cell, items);
        this.context = context;
        this.items = items;
    }

    private static class ListOfClassesHolder {
        public TextView className;
        public TextView classTeacher;
        public CheckBox checked;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ListOfClassesHolder holder = new ListOfClassesHolder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_of_classes_cell, null);
            holder.className = (TextView) v.findViewById(R.id.className);
            holder.classTeacher = (TextView) v.findViewById(R.id.teacherName);
            holder.checked = (CheckBox) v.findViewById(R.id.checkBox);
        } else {
            holder = (ListOfClassesHolder) v.getTag();
        }
        if(holder == null) {

            // Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
        }
        Course iteminlist = items.get(position);
        if(iteminlist != null && iteminlist.getCourseNum() != null && iteminlist.getProfessor()!=null &&  !iteminlist.getProfessor().toLowerCase().equals("not offered")) {
            if(holder != null) {
                holder.className.setText("Course: " + iteminlist.getCourseNum());
                holder.classTeacher.setText("Teacher: " + iteminlist.getProfessor());
                holder.checked.setOnCheckedChangeListener((ListOfClasses) context );
            }
        }
        return v;
    }

}