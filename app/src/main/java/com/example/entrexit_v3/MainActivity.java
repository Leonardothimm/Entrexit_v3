package com.example.entrexit_v3;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText destination, dateOne, dateTwo;
    Button search;
    String place;
    String date1, date2;
    Destination_Dates_Handler myDest ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDest = new Destination_Dates_Handler();
        //Finding the elements
        destination = (EditText) findViewById(R.id.editTextDestination);
        dateOne = (EditText) findViewById(R.id.editTextDateOne);
        dateTwo = (EditText) findViewById(R.id.editTextDateTwo);
        search = (Button) findViewById(R.id.button_search);
        //Adding Event Listener
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              place = destination.getText().toString();
              date1 = dateOne.getText().toString();
              date2 = dateTwo.getText().toString();
              if(place.equals("Ireland") || place.equals("IRELAND") || place.equals("ireland")) {
                  // myDest.setPlace(place);
                  myDest.setDate1(date1);
                  myDest.setDate2(date2);
                  Intent myIntent = new Intent(MainActivity.this, HotelListPage.class);
                  startActivity(myIntent);
              }
              else{
                  Toast.makeText(MainActivity.this, "Place not found", Toast.LENGTH_LONG).show();
              }
            }
        });
    }
}
