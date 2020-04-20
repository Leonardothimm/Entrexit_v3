package com.example.entrexit_v3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllBookings extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Orders");

    String firstName, lastName, dob, email, price, roomType;
    TextView firstNameTV, lastNameTV, dobTV, emailTV, priceTV, roomTypeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);

        //Finding by IDS

        firstNameTV = (TextView) findViewById(R.id.textView14);
        lastNameTV = (TextView) findViewById(R.id.textView15);
        emailTV = (TextView) findViewById(R.id.textView21);
        dobTV = (TextView) findViewById(R.id.textView22);
        priceTV = (TextView) findViewById(R.id.textView13);
        roomTypeTV = (TextView) findViewById(R.id.textView20);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    final Orders myOrder = childSnapshot.getValue(Orders.class);
                    firstName = myOrder.firstName;
                    lastName = myOrder.lastName;
                    dob = myOrder.dob;
                    email = myOrder.email;
                    price = myOrder.price;
                    roomType = myOrder.roomType;

                    firstNameTV.setText(firstName);
                    lastNameTV.setText(lastName);
                    emailTV.setText(email);
                    dobTV.setText(dob);
                    priceTV.setText(price);
                    roomTypeTV.setText(roomType);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Failed", error.toException());
            }
        });
    }
}
