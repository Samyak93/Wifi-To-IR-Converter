package com.android.wifitoirclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView TVbuttonControl;
    private TextView TVbuttonTraining;

    private TextView ACbuttonControl;
    private TextView ACbuttonTraining;
    private TextView ProjectorbuttonControl;
    private TextView ProjectorbuttonTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        TVbuttonControl=(TextView) findViewById(R.id.TVcontrolButton);
        TVbuttonTraining=(TextView) findViewById(R.id.TVtrainingButton);

        ACbuttonControl=(TextView) findViewById(R.id.ACcontrolButton);
        ACbuttonTraining=(TextView)findViewById(R.id.ACtrainingButton);

        ProjectorbuttonControl=(TextView)findViewById(R.id.ProjectorcontrolButton);
        ProjectorbuttonTraining=(TextView)findViewById(R.id.ProjectortrainingButton);


        TVbuttonControl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,TVTestingActivity.class);
                startActivity(i);
            }
        });


        TVbuttonTraining.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,TVTrainingActivity.class);
                startActivity(i);
            }
        });



        ACbuttonControl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,ACTestingActivity.class);
                startActivity(i);
            }
        });


        ACbuttonTraining.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,ACTrainingActivity.class);
                startActivity(i);
            }
        });
        ProjectorbuttonControl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,ProjectorTestingActivity.class);
                startActivity(i);
            }
        });


        ProjectorbuttonTraining.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuActivity.this,ProjectorTrainingActivity.class);
                startActivity(i);
            }
        });
    }
}
