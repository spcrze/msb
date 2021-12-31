package com.example.msb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard1 extends AppCompatActivity {

    TextView deletePoliceService,updatePoliceService,backToLogin,viewPoliceService;
    EditText policeID;
    DBhelper msbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        policeID = findViewById(R.id.inputPoliceServiceId);

        deletePoliceService = findViewById(R.id.tvDeletePoliceService);
        updatePoliceService = findViewById(R.id.tvUpdatePoliceService);
        viewPoliceService = findViewById(R.id.tvViewPoliceService);
        backToLogin = findViewById(R.id.textViewDashboardBackToLogin);

        msbDB = new DBhelper(this);

        deletePoliceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policeService = policeID.getText().toString();
                Boolean deletePoliceAccount = msbDB.deletePoliceService(policeService);
                if(policeService.equals("")){
                    Toast.makeText(Dashboard1.this,"Enter the CONTACT NUMBER to perform further operation",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(deletePoliceAccount == true){
                        startActivity(new Intent(Dashboard1.this,Login.class));
                        Toast.makeText(Dashboard1.this,"Deleted Police Service Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Dashboard1.this,"Failed to Delete Account CHECK CONTACT NUMBER",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        updatePoliceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String policeService = policeID.getText().toString();
                if(policeService.equals("")){
                    Toast.makeText(Dashboard1.this,"Enter the CONTACT NUMBER to perform further operation",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean policeCheckResult = msbDB.checkPoliceService(policeService);
                    if(policeCheckResult == true){
                        startActivity(new Intent(Dashboard1.this,UpdatePoliceService.class));
                    }
                    else {
                        Toast.makeText(Dashboard1.this,"Enter the Registered Contact Number",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        viewPoliceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policeService = policeID.getText().toString();
                if(policeService.equals("")){
                    Toast.makeText(Dashboard1.this,"Enter the CONTACT NUMBER to perform further operation",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean policeCheckResult = msbDB.checkPoliceService(policeService);
                    if(policeCheckResult == true){
                        Cursor result = msbDB.viewPoliceService();
                        if(result.getCount()==0){
                            Toast.makeText(Dashboard1.this,"No Police Service Data Available",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(result.moveToNext()) {
                            buffer.append("Police Service ID : "+ result.getString(0)+"\n");
                            buffer.append("Police Station : " + result.getString(1)+"\n");
                            buffer.append("Police Service Address : "+ result.getString(2)+"\n");
                            buffer.append("Police Service Contact : " + result.getString(3)+"\n");
                            buffer.append("Password :" + result.getString(4)+"\n\n");
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard1.this);
                        builder.setCancelable(true);
                        builder.setTitle("Police Service Data");
                        builder.setMessage(buffer.toString());
                        builder.show();
                    }
                    else{
                        Toast.makeText(Dashboard1.this,"Enter the Registered Contact Number",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard1.this,Login.class));
            }
        });

    }
}