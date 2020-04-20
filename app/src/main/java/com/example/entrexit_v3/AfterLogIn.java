package com.example.entrexit_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class AfterLogIn extends AppCompatActivity {
    Button checkIn, checkOut, MyBookingsBtn;
  //  ToggleButton tglReadWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);
     //   tglReadWrite = (ToggleButton) findViewById(R.id.toggleButtonId);
        checkIn = (Button) findViewById(R.id.checkIntBtn);
        checkOut = (Button) findViewById(R.id.checkOutBtn);
        MyBookingsBtn = (Button) findViewById(R.id.MyBookingsBtn);
       //OnClickListener
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkInIntent = new Intent(AfterLogIn.this, Check_in.class);
                startActivity(checkInIntent);
            }
        });
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkOutIntent = new Intent(AfterLogIn.this, Check_in.class);
                startActivity(checkOutIntent);
            }
        });
        MyBookingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myBookingIntent = new Intent(AfterLogIn.this, AllBookings.class);
                startActivity(myBookingIntent);
            }
        });
    }
}
