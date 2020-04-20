package com.example.entrexit_v3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HotelListPage extends AppCompatActivity {
    ImageButton myImageBtn, myImageBtn2, myImageBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     //   place = myDest.getPlace();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list_page);
       // final Intent newIntent = new Intent(HotelListPage.this, Hotel.class);
        myImageBtn = (ImageButton)findViewById(R.id.imageButtonHotelOne);
        myImageBtn2 = (ImageButton)findViewById(R.id.imageButtonHotelTwo);
        myImageBtn3 = (ImageButton)findViewById(R.id.imageButtonHotelThree);
        //On Click Listener
        myImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(HotelListPage.this, Riu_Plaza_Details.class);
                startActivity(newIntent);
            }
        });
        myImageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(HotelListPage.this, North_Star_Details.class);
                startActivity(newIntent);
            }
        });
        myImageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(HotelListPage.this, Temple_Bar_Details.class);
                startActivity(newIntent);
            }
        });
    }
}