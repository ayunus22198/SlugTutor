package com.example.srini.slugtutor;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class FirebaseService {

    // TODO: when a user signs out, set this ID to null
    private static String cachedUserId = null;

    public FirebaseService() {}

    public String getUserID() {
        if (FirebaseService.cachedUserId == null) {
            FirebaseService.cachedUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        return FirebaseService.cachedUserId;
    }

    public void getUserClasses(final CallbackCourses callback) {
        DatabaseReference coursesReference = FirebaseDatabase.getInstance().getReference("users")
                .child(getUserID()).child("courses");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Course> courses = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Course course = new Course();
                    course.setId(child.getKey());
                    for (DataSnapshot item : child.getChildren()) {
                        switch(item.getKey()) {
                            case "name":
                                course.setName(item.getValue().toString());
                                break;
                            case "description":
                                course.setDescription(item.getValue().toString());
                                break;
                            case "courseNum":
                                course.setCourseNum(item.getValue().toString());
                                break;
                            case "professor":
                                course.setProfessor(item.getValue().toString());
                                break;
                        }
                    }
                    courses.add(course);
                }

                callback.callback(courses);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        coursesReference.addListenerForSingleValueEvent(postListener);
    }

    public void getUserGroupListings(final CallbackListings callback) {
        DatabaseReference coursesReference = FirebaseDatabase.getInstance().getReference("users")
                .child(getUserID()).child("listings").child("groups");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                processListings(dataSnapshot, callback);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        coursesReference.addListenerForSingleValueEvent(postListener);
    }

    public void getUserTutorListings(final CallbackListings callback) {
        DatabaseReference coursesReference = FirebaseDatabase.getInstance().getReference("users")
                .child(getUserID()).child("listings").child("tutors");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                processListings(dataSnapshot, callback);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        coursesReference.addListenerForSingleValueEvent(postListener);
    }

    public void getUserStudentListings(final CallbackListings callback) {
        DatabaseReference coursesReference = FirebaseDatabase.getInstance().getReference("users")
                .child(getUserID()).child("listings").child("students");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                processListings(dataSnapshot, callback);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        coursesReference.addListenerForSingleValueEvent(postListener);
    }

    private void processListings(DataSnapshot dataSnapshot, CallbackListings callback) {
        List<Listing> listings = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Listing listing = new Listing();
            listing.setId(child.getKey());
            for (DataSnapshot item : child.getChildren()) {
                switch(item.getKey()) {
                    case "name":
                        listing.setName(item.getValue().toString());
                        break;
                }
            }
            listings.add(listing);
        }

        callback.callback(listings);
    }

    public void addCourse(Course course) {
        FirebaseDatabase.getInstance().getReference("users")
                .child(getUserID()).child("courses").child(course.getId())
                .setValue(new Course(
                        course.getName(),
                        course.getCourseNum(),
                        course.getProfessor(),
                        course.getDescription()));
    }

    public void addStudentListing(String name) {
        addListing("students", name);
    }

    public void addTutorListing(String name) {
        addListing("tutors", name);
    }

    public void addGroupListing(String name) {
        addListing("groups", name);
    }

    private void addListing(String type, String name) {
        FirebaseDatabase.getInstance().getReference("users").child(getUserID())
                .child("listings").child(type).child(UUID.randomUUID().toString()).child("name").setValue(name);
    }




}
