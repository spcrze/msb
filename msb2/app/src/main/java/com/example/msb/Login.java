package com.example.msb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText policeID,policePassword;
    Button btnLogin;
    DBhelper msbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        policeID = (EditText)findViewById(R.id.inputUserID);
        policePassword = (EditText)findViewById(R.id.inputpassword);
        btnLogin = (Button)findViewById(R.id.loginButton);

        msbDB = new DBhelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String policeService = policeID.getText().toString();
                String policePass = policePassword.getText().toString();

                if(policeService.equals("")||policePass.equals("")){
                    Toast.makeText(Login.this,"Please Enter the Credentials",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result1 = msbDB.checkPoliceIdPassword(policeService,policePass);
                    if(result1 == true){
                        Intent intent = new Intent(Login.this,Dashboard1.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView btn1 = findViewById(R.id.signUP);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,UserType.class));
            }

        });

        TextView btn2 = findViewById(R.id.forgetPassword);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgetPasswordActivity.class));
            }
        });
    }
}