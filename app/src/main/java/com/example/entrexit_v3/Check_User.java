package com.example.entrexit_v3;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Check_User {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Users");
    String username, password;

    public Check_User() {
    }

    public void Check_User() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    final Username_Accounts usernameAccounts = childSnapshot.getValue(Username_Accounts.class);
                    String testing = usernameAccounts.cardName;
                    System.out.println(testing);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Failed", error.toException());
            }
        });

    }
    public Check_User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
