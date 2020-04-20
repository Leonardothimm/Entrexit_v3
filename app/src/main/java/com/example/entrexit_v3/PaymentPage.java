package com.example.entrexit_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentPage extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Users");
    DatabaseReference myRef2 = database.getReference("Orders");

    Button myBtn;
    TextView hotelTitle, roomTitle, price1, price2;
    TextView firstName, lastName, email, dob;
    //String for the values between intents
    Destination_Dates_Handler myDest = new Destination_Dates_Handler();
    String place;
    String room1, room2, room3, room4;
    //String to retrieve data from DATABASE
    //String firstName, lastName, email, dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
        //Find IDS
        hotelTitle = (TextView) findViewById(R.id.HotelTextView);
        roomTitle = (TextView) findViewById(R.id.textView45);

       firstName = (TextView) findViewById(R.id.textView31);
       lastName = (TextView) findViewById(R.id.textView33);
       dob = (TextView) findViewById(R.id.textView37);
       email = (TextView) findViewById(R.id.textView41);
       //Prices
       price1 = (TextView) findViewById(R.id.textView12);
       price2 = (TextView) findViewById(R.id.textView56);
       price1.setText("120");
       price2.setText("240");
       myBtn = (Button) findViewById(R.id.FinishPayBtn);

        //RECEIVING THE VALUES FROM THE PREVIOUS INTENT
        Intent myIntent = getIntent();
        place = myIntent.getStringExtra("Riu_Plaza");
        room1 = myIntent.getStringExtra("Riu_Plaza1");
        room2 = myIntent.getStringExtra("Riu_Plaza2");
        room3 = myIntent.getStringExtra("Riu_Plaza3");
        room4 = myIntent.getStringExtra("Riu_Plaza4");
        hotelTitle.setText(place);
        roomTitle.setText(room1);

        //RETRIEVING THE USER INFORMATION FROM THE DATABASE
        myRef.addValueEventListener(new ValueEventListener() {
            Orders newOrder = new Orders();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    final Username_Accounts usernameAccounts = childSnapshot.getValue(Username_Accounts.class);
                   String firstNameReg = usernameAccounts.firstName;
                   String lastNameReg = usernameAccounts.lastName;
                   String dobReg = usernameAccounts.dob;
                   String  emailReg = usernameAccounts.email;
                   firstName.setText(firstNameReg);
                   lastName.setText(lastNameReg);
                   dob.setText(dobReg);
                   email.setText(emailReg);

                   //Creating a ORDER object to store "transactions" on DB
                   newOrder.setFirstName(firstNameReg);
                   newOrder.setLastName(lastNameReg);
                   newOrder.setDob(dobReg);
                   newOrder.setEmail(emailReg);
                   newOrder.setRoomType(room1);
                   newOrder.setPrice("€240");
                   myRef2.push().setValue(newOrder);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Failed", error.toException());
            }
        });

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PaymentPage.this, AfterLogIn.class);
                startActivity(myIntent);
                Toast.makeText(PaymentPage.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                //Orders newOrder = new Orders();
               // newOrder.setFirstName();
            }
        });
    }
}
