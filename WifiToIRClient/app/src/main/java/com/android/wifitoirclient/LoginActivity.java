package com.android.wifitoirclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String TAG="In WifiToIR Login Activity";
    EditText usernameText;
    EditText passwordText;
    TextView wolf;
    TextView signin;
    TextView signup;
    String username,password;
    private ProgressDialog loginWaitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");

        usernameText=(EditText) findViewById(R.id.username);
        passwordText=(EditText) findViewById(R.id.pass);
        wolf = (TextView) findViewById(R.id.wolf);
        signin = (TextView) findViewById(R.id.signin);
        signup = (TextView)findViewById(R.id.signup);
        signup.setTypeface(custom_font);
        usernameText.setTypeface(custom_font);
        passwordText.setTypeface(custom_font);
        wolf.setTypeface(custom_font);
        signin.setTypeface(custom_font);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=usernameText.getText().toString();
                password=passwordText.getText().toString();

                if("".equals(StartActivity.serverIp))
                {
                    Toast.makeText(LoginActivity.this, "Please Configure the Server Address.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(username))
                {
                    usernameText.setError("Invalid Username");
                }
                else if(TextUtils.isEmpty(password))
                {
                    passwordText.setError("Invalid Password");
                }
                else if(username.equals("admin") && password.equals("admin"))
                {
                    Intent intent=new Intent(LoginActivity.this,MenuActivity.class);
                    startActivity(intent);

                }

            }
        });

    }

}
