package com.example.entrexit_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class North_Star_Details extends AppCompatActivity {
    Button myButton1, myButton2, myButton3, myButton4;
    String room1, room2, room3, room4;
    String name = "North Star Hotel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.north_star_details);

        myButton1 = (Button) findViewById(R.id.priceOneBtn);
        myButton2 = (Button) findViewById(R.id.priceTwoBtn);
        myButton3 = (Button) findViewById(R.id.priceThreeBtn);
        myButton4 = (Button) findViewById(R.id.priceFourBtn);

        room1 = "Classic Room";
        room2 = "Premiere Room";
        room3 = "Family Room";
        room4 = "Senior suit";


        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(North_Star_Details.this, PaymentPage.class);
                newIntent.putExtra("Riu_Plaza", name);
                newIntent.putExtra("Riu_Plaza1", room1);
                //newIntent.
                startActivity(newIntent);
            }
        });
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(North_Star_Details.this, PaymentPage.class);
                newIntent.putExtra("Riu_Plaza", name);
                newIntent.putExtra("Riu_Plaza2", room2);
                startActivity(newIntent);

            }
        });
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(North_Star_Details.this, PaymentPage.class);
                newIntent.putExtra("Riu_Plaza", name);
                newIntent.putExtra("Riu_Plaza3", room3);
                startActivity(newIntent);

            }
        });
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(North_Star_Details.this, PaymentPage.class);
                newIntent.putExtra("Riu_Plaza", name);
                newIntent.putExtra("Riu_Plaza4", room4);
                startActivity(newIntent);

            }
        });
    }
}