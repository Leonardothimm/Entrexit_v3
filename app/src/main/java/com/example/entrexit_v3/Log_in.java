package com.example.entrexit_v3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Log_in extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("New Users");

    ImageButton myImageBtn;
    String username, password;
    String regUsername, regPassword;
    EditText tfName, tfPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //FindByID
        myImageBtn = (ImageButton) findViewById(R.id.imageButtonLogin);
        tfName = (EditText) findViewById(R.id.editText_username);
        tfPassword = (EditText) findViewById(R.id.editText_password);
        register = (Button) findViewById(R.id.btn_register);
        //Adding Event Listener
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent02 = new Intent(Log_in.this, Create_Account.class);
                startActivity(newIntent02);
            }
        });
        myImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gotta be out of the myRef.add Value = On click is different than a new Value;
                username = tfName.getText().toString();
                password = tfPassword.getText().toString();
                //Database reference
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            final Username_Accounts usernameAccounts = childSnapshot.getValue(Username_Accounts.class);
                            regUsername = usernameAccounts.cardName;
                            regPassword = usernameAccounts.passOne;
                            if (password.equals(regPassword) && username.equals(regUsername)) {
                                Intent myIntent = new Intent(Log_in.this, MainActivity.class);
                                startActivity(myIntent);
                            } else {
                                Toast.makeText(Log_in.this, "User not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Failed", error.toException());
                    }
                });
            }
        });
    }
}
