package com.example.msb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {

    EditText policeID,policePassword,policeRepassword,policeStation,policeAddress,policeContact;
    Button btnRegister;
    DBhelper msbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        policeID = (EditText)findViewById(R.id.inputPolicephone);
        policeStation = (EditText)findViewById(R.id.inputPoliceStation);
        policeAddress = (EditText)findViewById(R.id.inputPoliceAddress);
        policeContact = (EditText)findViewById(R.id.inputPolicephone);
        policePassword = (EditText)findViewById(R.id.inputPolicePassword);
        policeRepassword = (EditText)findViewById(R.id.inputPoliceRepassword);
        btnRegister = (Button)findViewById(R.id.buttonPoliceRegister);

        TextView btn1 = findViewById(R.id.alreadyHavePoliceAccount);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this,Login.class));
            }
        });

        msbDB = new DBhelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policeService = policeID.getText().toString();
                String policeStationName = policeStation.getText().toString();
                String policeServiceAddress = policeAddress.getText().toString();
                String policePhoneNumber = policeContact.getText().toString();
                String policePass = policePassword.getText().toString();
                String policeRepass = policeRepassword.getText().toString();

                if(policeStationName.equals("") || policeServiceAddress.equals("")||policePhoneNumber.equals("")||policePass.equals("") || policeRepass.equals("")){
                    Toast.makeText(RegisterActivity2.this,"Fill Required Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(policePass.equals(policeRepass)){

                        Boolean policeCheckResult = msbDB.checkPoliceService(policeService);
                        if(policeCheckResult == false){
                            Boolean regPoliceService =  msbDB.insertPoliceService(policeService,policeStationName,policeServiceAddress,policePhoneNumber,policePass);
                            if(regPoliceService == true){
                                Toast.makeText(RegisterActivity2.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),RegisteredSuccessfully.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity2.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity2.this,"Police Service already Exists \n Please Log in",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(RegisterActivity2.this,"Password does not match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}