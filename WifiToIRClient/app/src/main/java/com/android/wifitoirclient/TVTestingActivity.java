package com.android.wifitoirclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.wifitoirclient.db.IRDataBean;
import com.android.wifitoirclient.db.IRDataDBHelper;
import com.android.wifitoirclient.service.CommunicationManager;

public class TVTestingActivity extends AppCompatActivity {


    private Button buttonOn,buttonProgPlus,buttonProgMinus,buttonVolUp,buttonVolDown;
    private String Tag="TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvtraining);

        buttonOn=(Button)findViewById(R.id.buttonOn);
        buttonOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Train ON button click");

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            try {
                                IRDataDBHelper irDBHelper = new IRDataDBHelper(TVTestingActivity.this);
                                Log.d(Tag,"Before getIRData");
                                IRDataBean iRDataBean = irDBHelper.getIRData("on", "tv");
                                Log.d(Tag,"After getIRData");
                                Log.d(Tag,"DATA:" + iRDataBean.getValue());
                                CommunicationManager  communicationManager = new CommunicationManager();
                                communicationManager.testAction(iRDataBean.getValue());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "";
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            Toast.makeText(TVTestingActivity.this, "On Button Trained", Toast.LENGTH_LONG).show();
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        buttonProgPlus=(Button)findViewById(R.id.ButtonProgPlus);
        buttonProgPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Test Prog + button click");

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            try {
                                IRDataDBHelper irDBHelper = new IRDataDBHelper(TVTestingActivity.this);
                                Log.d(Tag,"Before getIRData");
                                IRDataBean iRDataBean = irDBHelper.getIRData("program_plus", "tv");
                                Log.d(Tag,"After getIRData");
                                Log.d(Tag,"DATA:" + iRDataBean.getValue());
                                CommunicationManager  communicationManager = new CommunicationManager();
                                communicationManager.testAction(iRDataBean.getValue());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "";
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        buttonProgMinus=(Button)findViewById(R.id.ButtonProgMinus);
        buttonProgMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Test Prog - button click");

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            try {
                                IRDataDBHelper irDBHelper = new IRDataDBHelper(TVTestingActivity.this);
                                Log.d(Tag,"Before getIRData");
                                IRDataBean iRDataBean = irDBHelper.getIRData("program_minus", "tv");
                                Log.d(Tag,"After getIRData");
                                Log.d(Tag,"DATA:" + iRDataBean.getValue());
                                CommunicationManager  communicationManager = new CommunicationManager();
                                communicationManager.testAction(iRDataBean.getValue());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "";
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        buttonVolUp=(Button)findViewById(R.id.buttonVolUp);
        buttonVolUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Test Vol up button click");

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            try {
                                IRDataDBHelper irDBHelper = new IRDataDBHelper(TVTestingActivity.this);
                                Log.d(Tag,"Before getIRData");
                                IRDataBean iRDataBean = irDBHelper.getIRData("volume_up", "tv");
                                Log.d(Tag,"After getIRData");
                                Log.d(Tag,"DATA:" + iRDataBean.getValue());
                                CommunicationManager  communicationManager = new CommunicationManager();
                                communicationManager.testAction(iRDataBean.getValue());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "";
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        buttonVolDown=(Button)findViewById(R.id.buttonVolDown);
        buttonVolDown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Test Vol down button click");

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            try {
                                IRDataDBHelper irDBHelper = new IRDataDBHelper(TVTestingActivity.this);
                                Log.d(Tag,"Before getIRData");
                                IRDataBean iRDataBean = irDBHelper.getIRData("volume_down", "tv");
                                Log.d(Tag,"After getIRData");
                                Log.d(Tag,"DATA:" + iRDataBean.getValue());
                                CommunicationManager communicationManager = new CommunicationManager();
                                communicationManager.testAction(iRDataBean.getValue());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "";
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }
}
