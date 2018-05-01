package com.example.srini.slugtutor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listings extends Activity implements CallbackListings{
    @Override
    public void callback(List<Listing> listings) {

        FirebaseService fS = new FirebaseService();
        DatabaseReference groupListingsReference = FirebaseDatabase.getInstance().getReference("users")
                .child(fS.getUserID()).child("listings").child("groups");
        DatabaseReference studentListingsReference = FirebaseDatabase.getInstance().getReference("users")
                .child(fS.getUserID()).child("listings").child("students");
        DatabaseReference tutorsListingsReference = FirebaseDatabase.getInstance().getReference("users")
                .child(fS.getUserID()).child("listings").child("tutors");
        final List<Listing> listingsCollection = new ArrayList<>();

        ValueEventListener userGroupListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Listing listingGroup = new Listing();
                    listingGroup.setId(child.getKey());
                    for (DataSnapshot item : child.getChildren()) {
                        switch (item.getKey()) {
                            case "name":
                                listingGroup.setName(item.getValue().toString());
                                break;
                            default:
                                break;
                        }
                    }
                    listingsCollection.add(listingGroup);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ValueEventListener userStudentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Listing listingStudent = new Listing();
                    listingStudent.setId(child.getKey());
                    for (DataSnapshot item : child.getChildren()) {
                        switch (item.getKey()) {
                            case "name":
                                listingStudent.setName(item.getValue().toString());
                                break;
                            default:
                                break;
                        }
                    }
                    listingsCollection.add(listingStudent);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ValueEventListener tutorsStudentListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Listing listingTutor = new Listing();
                    listingTutor.setId(child.getKey());
                    for (DataSnapshot item : child.getChildren()) {
                        switch (item.getKey()) {
                            case "name":
                                listingTutor.setName(item.getValue().toString());
                                break;
                            default:
                                break;
                        }
                    }
                    listingsCollection.add(listingTutor);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        tutorsListingsReference.addValueEventListener(tutorsStudentListener);
        tutorsListingsReference.addValueEventListener(userStudentListener);
        tutorsListingsReference.addValueEventListener(userGroupListener);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listings);

    }

/*
    FirebaseService firebaseService = new FirebaseService();
        firebaseService.getUserGroupListings(new CallbackListings(
            @Override
            public void callback(List<Listing> listings) {

    }
});
*/


}