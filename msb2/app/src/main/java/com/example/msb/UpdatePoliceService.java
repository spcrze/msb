package com.example.msb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePoliceService extends AppCompatActivity {

    TextView updatePoliceService;
    EditText policeID,etPoliceStation,etPoliceAddress,etPoliceContact,etPolicePassword,etPoliceRepassword;
    Button btnDone;
    DBhelper msbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_police_service);

        policeID = (EditText)findViewById(R.id.inputUpdatePoliceContact);
        etPoliceStation = (EditText)findViewById(R.id.inputUpdatePoliceStation);
        etPoliceAddress = (EditText)findViewById(R.id.inputUpdatePoliceAddress);
        etPoliceContact = (EditText)findViewById(R.id.inputUpdatePoliceContact);
        etPolicePassword = (EditText)findViewById(R.id.inputUpdatePolicePassword);
        etPoliceRepassword = (EditText)findViewById(R.id.inputUpdatePoliceRepassword);
        msbDB = new DBhelper(this);

        btnDone = (Button)findViewById(R.id.buttonDoneUpdate);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String policeService = policeID.getText().toString();
                String policeStation = etPoliceStation.getText().toString();
                String policeAddress = etPoliceAddress.getText().toString();
                String policeContact = etPoliceContact.getText().toString();
                String policePassword = etPolicePassword.getText().toString();
                String policeRepassword = etPoliceRepassword.getText().toString();

                Boolean checkPoliceUpdate = msbDB.updatePoliceService(policeService,policeStation,policeAddress,policeContact,policePassword);

                if(policeService.equals("") || policeStation.equals("") || policeAddress.equals("") || policeContact.equals("") || policePassword.equals("") || policeRepassword.equals("")){
                    Toast.makeText(UpdatePoliceService.this,"Enter all Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean policeCheckResult = msbDB.checkPoliceService(policeService);
                    if(policeCheckResult == true){
                        if(policePassword.equals(policeRepassword)) {
                            if(checkPoliceUpdate == true){
                                startActivity(new Intent(UpdatePoliceService.this,Dashboard1.class));
                                Toast.makeText(UpdatePoliceService.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(UpdatePoliceService.this,"Failed to Update CHECK CONTACT NUMBER",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(UpdatePoliceService.this,"Password does not match",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(UpdatePoliceService.this,"Enter Registered Contact Number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}