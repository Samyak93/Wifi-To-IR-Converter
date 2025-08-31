package com.android.wifitoirclient;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    String TAG="In Smart Pill Box startActivty List";
    TextView login;
    TextView signUp;
    Button IpCancleButton;
    Button IpSubmitButton;
    TextView llg;
    public static String serverIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");

        login = (TextView) findViewById(R.id.startsignin);
        signUp= (TextView) findViewById(R.id.startsignup);

        login.setTypeface(custom_font);
        signUp.setTypeface(custom_font);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(!TextUtils.isEmpty(serverIp))
                    {
                        Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Log.d(TAG,"Switch to login Activity.");
                    }
                    else {
                        Toast.makeText(StartActivity.this, "Please Enter the server connection Address.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {e.printStackTrace();}
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(!TextUtils.isEmpty(serverIp))
                    {
                        Intent intent=new Intent(StartActivity.this,AboutUsActivity.class);
                        startActivity(intent);
                        Log.d(TAG,"Switch to Register Activity.");
                    }
                    else {
                        Toast.makeText(StartActivity.this, "Please Enter the server connection Address.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {e.printStackTrace();}
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Dialog dialog = new Dialog(StartActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        switch (item.getItemId()) {
            case R.id.ip_settings:
            {
                dialog.setContentView(R.layout.ip_config_layout);
                dialog.setCancelable(true);

                IpCancleButton = (Button) dialog.findViewById(R.id.ip_cancel_button);
                IpCancleButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                IpSubmitButton = (Button) dialog.findViewById(R.id.ip_ok_button);

                IpSubmitButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {

                            EditText editTextipServer = (EditText) dialog.findViewById(R.id.ip_config_layout_serverIpEditText);
                            serverIp = editTextipServer.getText().toString();

                            Toast.makeText(StartActivity.this, "Server Ip:" + serverIp, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            Log.d(TAG, "IP address:" + serverIp);
                            //}
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
            break;


        }

        return super.onOptionsItemSelected(item);
    }// end onOptionsItemSelected
}
