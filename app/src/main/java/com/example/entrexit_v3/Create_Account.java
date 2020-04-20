package com.example.entrexit_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class Create_Account extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Users");

    Username_Accounts myObject;
    Button myButton;
    EditText firstName, lastName, email, dob, passOne, passTwo, cardName, cardNum, expiry, cvv;
    String firstNameS, lastNameS, emailS, dobS, passOneS, passTwoS, cardNameS, cardNumS, expiryS, cvvS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);
        myObject = new Username_Accounts();
       // readBtn = (Button) findViewById(R.id.readBtn);
        myButton = (Button) findViewById(R.id.btn_save);
        //TF:
        firstName = (EditText) findViewById(R.id.firstNameTf);
        lastName = (EditText) findViewById(R.id.lastNameTf);
        email = (EditText) findViewById(R.id.emailTf);
        dob = (EditText) findViewById(R.id.dobTf);
        passOne = (EditText) findViewById(R.id.passwordOneTf);
        passTwo = (EditText) findViewById(R.id.passwordTwoTf);
        cardName = (EditText) findViewById(R.id.cardNameTf);
        cardNum= (EditText) findViewById(R.id.cardNumberTf);
        expiry = (EditText) findViewById(R.id.expiryTf);
        cvv = (EditText) findViewById(R.id.cvvTf);
/*
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            final Username_Accounts usernameAccounts = childSnapshot.getValue(Username_Accounts.class);
                            // System.out.println(usernameAccounts.cardName);
                          //  System.out.println(usernameAccounts.cardNum);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("Failed" , error.toException());
                    }
                });
            }
        });*/

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstNameS = firstName.getText().toString();
                myObject.setFirstName(firstNameS);

                lastNameS = lastName.getText().toString();
                myObject.setLastName(lastNameS);

                emailS = email.getText().toString();
                myObject.setEmail(emailS);

                dobS = dob.getText().toString();
                myObject.setDob(dobS);

                passOneS = passOne.getText().toString();
                myObject.setPassOne(passOneS);

                passTwoS = passTwo.getText().toString();
                myObject.setPassTwo(passTwoS);

                cardNameS = cardName.getText().toString();
                myObject.setCardName(cardNameS);
                //myObject.setFirstName(cardNameS);

                cardNumS = cardNum.getText().toString();
                myObject.setCardNum(cardNumS);

                expiryS = expiry.getText().toString();
                myObject.setExpiry(expiryS);

                cvvS= cvv.getText().toString();
                myObject.setCvv(cvvS);

                myRef.push().setValue(myObject);
                Toast.makeText(Create_Account.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();

                Intent newIntent = new Intent(Create_Account.this, Log_in.class);
                startActivity(newIntent);
            }
        });

    }
}
/*
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            users.put(childSnapshot.getKey(), childSnapshot.getValue());
                            //users.put(childSnapshot.getKey(), childSnapshot.getKey());
                            for (String keys : users.keySet())
                            {
                                System.out.println(keys + ":"+ users.get(keys));
                            }
                        }
                    }
 */