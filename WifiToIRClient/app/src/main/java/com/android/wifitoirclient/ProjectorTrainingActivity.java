package com.android.wifitoirclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.os.AsyncTask;

import com.android.wifitoirclient.db.IRDataBean;
import com.android.wifitoirclient.db.IRDataDBHelper;
import com.android.wifitoirclient.service.CommunicationManager;

public class ProjectorTrainingActivity extends AppCompatActivity {

    private Button buttonOn,buttonProgPlus,buttonProgMinus,buttonVolUp,buttonVolDown;
    private String Tag="TRAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projector_training);


        buttonOn=(Button)findViewById(R.id.buttonOn);
        buttonOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    Log.d(Tag,"Train ON button click");
                    buttonOn.setEnabled(false);

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            String sData = "";
                            try {

                                CommunicationManager  communicationManager = new CommunicationManager();
                                sData = communicationManager.trainAction();
                                Log.d(Tag,"DATA:" + sData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return sData;
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            //save result data into DB
                            IRDataDBHelper irDBHelper = new IRDataDBHelper(ProjectorTrainingActivity.this);
                            IRDataBean iRDataBean = new IRDataBean();
                            iRDataBean.setName("on");
                            iRDataBean.setType("Projector");
                            iRDataBean.setValue(result);
                            Log.d(Tag,"before updateIRData");

                            irDBHelper.updateIRData(iRDataBean);
                            Log.d(Tag,"After updateIRData");
                            buttonOn.setEnabled(true);
                            Toast.makeText(ProjectorTrainingActivity.this, "On Button Trained", Toast.LENGTH_LONG).show();
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
                    Log.d(Tag,"Train Prog+ button click");
                    buttonProgPlus.setEnabled(false);

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            String sData = "";
                            try {

                                CommunicationManager  communicationManager = new CommunicationManager();
                                sData = communicationManager.trainAction();
                                Log.d(Tag,"DATA:" + sData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return sData;
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            //save result data into DB
                            IRDataDBHelper irDBHelper = new IRDataDBHelper(ProjectorTrainingActivity.this);
                            IRDataBean iRDataBean = new IRDataBean();
                            iRDataBean.setName("program_plus");
                            iRDataBean.setType("Projector");
                            iRDataBean.setValue(result);
                            Log.d(Tag,"before updateIRData");

                            irDBHelper.updateIRData(iRDataBean);
                            Log.d(Tag,"After updateIRData");
                            buttonProgPlus.setEnabled(true);
                            Toast.makeText(ProjectorTrainingActivity.this, "Program Plus Button Trained", Toast.LENGTH_LONG).show();
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
                    Log.d(Tag,"Train Prog- button click");
                    buttonProgMinus.setEnabled(false);

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            String sData = "";
                            try {

                                CommunicationManager  communicationManager = new CommunicationManager();
                                sData = communicationManager.trainAction();
                                Log.d(Tag,"DATA:" + sData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return sData;
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            //save result data into DB
                            IRDataDBHelper irDBHelper = new IRDataDBHelper(ProjectorTrainingActivity.this);
                            IRDataBean iRDataBean = new IRDataBean();
                            iRDataBean.setName("program_minus");
                            iRDataBean.setType("Projector");
                            iRDataBean.setValue(result);
                            Log.d(Tag,"before updateIRData");

                            irDBHelper.updateIRData(iRDataBean);
                            Log.d(Tag,"After updateIRData");
                            buttonProgMinus.setEnabled(true);
                            Toast.makeText(ProjectorTrainingActivity.this, "Program Minus Button Trained", Toast.LENGTH_LONG).show();
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
                    Log.d(Tag,"Train Vol+ button click");
                    buttonVolUp.setEnabled(false);

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            String sData = "";
                            try {

                                CommunicationManager  communicationManager = new CommunicationManager();
                                sData = communicationManager.trainAction();
                                Log.d(Tag,"DATA:" + sData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return sData;
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            //save result data into DB
                            IRDataDBHelper irDBHelper = new IRDataDBHelper(ProjectorTrainingActivity.this);
                            IRDataBean iRDataBean = new IRDataBean();
                            iRDataBean.setName("volume_up");
                            iRDataBean.setType("Projector");
                            iRDataBean.setValue(result);
                            Log.d(Tag,"before updateIRData");

                            irDBHelper.updateIRData(iRDataBean);
                            Log.d(Tag,"After updateIRData");
                            buttonVolUp.setEnabled(true);
                            Toast.makeText(ProjectorTrainingActivity.this, "Volume Plus Button Trained", Toast.LENGTH_LONG).show();
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
                    Log.d(Tag,"Train Vol- button click");
                    buttonVolDown.setEnabled(false);

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            Log.d(Tag,"in doInBackground");
                            String sData = "";
                            try {

                                CommunicationManager  communicationManager = new CommunicationManager();
                                sData = communicationManager.trainAction();
                                Log.d(Tag,"DATA:" + sData);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return sData;
                        }
                        @Override
                        protected void onPostExecute(String result) {

                            //save result data into DB
                            IRDataDBHelper irDBHelper = new IRDataDBHelper(ProjectorTrainingActivity.this);
                            IRDataBean iRDataBean = new IRDataBean();
                            iRDataBean.setName("volume_down");
                            iRDataBean.setType("Projector");
                            iRDataBean.setValue(result);
                            Log.d(Tag,"before updateIRData");

                            irDBHelper.updateIRData(iRDataBean);
                            Log.d(Tag,"After updateIRData");
                            buttonVolDown.setEnabled(true);
                            Toast.makeText(ProjectorTrainingActivity.this, "Volume Minus Button Trained", Toast.LENGTH_LONG).show();
                        }

                    }.execute("hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
