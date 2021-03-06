package com.android.jungledjumble.Setting;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.android.jungledjumble.Auth.StartActivity;
import com.android.jungledjumble.Main.ReturnActivity;
import com.android.jungledjumble.R;
import com.android.jungledjumble.Utils.Utils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressActivity extends AppCompatActivity {

    LineChart chart1;
    LineChart chart2;
    LineChart chart3;
    BarChart chart4;
    ImageView forresearchers_button;
    ImageView cancel_button;
    ImageView forresearchers2_button;
    TextView time_title;

    TextView researchers_title;
    TextView for_title;

    int total_val1=0;
    int total_val2=0;
    int total_val3=0;
    int hour;
    int minute;
    int second;
    String username;
    double accRate;
    int int_accRate;
    int width ;
    int height ;
    String _OSVERSION = System.getProperty("os.version");
    String _RELEASE = android.os.Build.VERSION.RELEASE;
    String _DEVICE = android.os.Build.DEVICE;
    String _MODEL = android.os.Build.MODEL;
    String _PRODUCT = android.os.Build.PRODUCT;
    String _BRAND = android.os.Build.BRAND;
    String _DISPLAY = android.os.Build.DISPLAY;
    String _CPU_ABI = android.os.Build.CPU_ABI;
    String _CPU_ABI2 = android.os.Build.CPU_ABI2;
    String _UNKNOWN = android.os.Build.UNKNOWN;
    String _HARDWARE = android.os.Build.HARDWARE;
    String _ID = android.os.Build.ID;
    String _MANUFACTURER = android.os.Build.MANUFACTURER;
    String _SERIAL = android.os.Build.SERIAL;
    String _USER = android.os.Build.USER;
    String _HOST = android.os.Build.HOST;
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=0;
    MediaPlayer background_sound;
    Boolean sound_on = true;
    Boolean music_on = true;

    @Override
    public void onResume(){
        super.onResume();
        Utils utils = new Utils(this);
        utils.hideSystemUI ();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        final MediaPlayer click_sound = MediaPlayer.create(this, R.raw.blip_annabel);
        background_sound = MediaPlayer.create(this, R.raw.mixed_demo);

        try{sound_on = getIntent().getExtras().getBoolean("sound_on",true);}
        catch (Exception e){}

        try{music_on = getIntent().getExtras().getBoolean("music_on",true);}
        catch (Exception e){}

        if (music_on){background_sound.start();}

        Utils utils = new Utils(this);
        utils.hideSystemUI ();

        try {
            Intent intent = getIntent();
            accRate = intent.getDoubleExtra("accRate", 10.0);

            int_accRate = (int)(accRate/10);
            //Toast.makeText (this, " " + int_accRate, Toast.LENGTH_SHORT).show ();



            String[] timef=currentTime.split(":");

             hour=Integer.parseInt(timef[0]);
             minute=Integer.parseInt(timef[1]);
             second=Integer.parseInt(timef[2]);




        }catch(Exception e){


        }


  /*      if (!background_sound.isPlaying()) {
            background_sound.start();
        }*/
        time_title=findViewById(R.id.time_title);

        final TabHost mTabHost = (TabHost)findViewById(R.id.tabHost);
        mTabHost.setup();
        //Lets add the first Tab
        TabHost.TabSpec mSpec = mTabHost.newTabSpec("Daily");
        mSpec.setContent(R.id.Daily);
        mSpec.setIndicator("Daily");
        mTabHost.addTab(mSpec);
        //Lets add the second Tab
        mSpec = mTabHost.newTabSpec("Monthly");
        mSpec.setContent(R.id.Monthly);
        mSpec.setIndicator("Monthly");
        mTabHost.addTab(mSpec);
        //Lets add the third Tab
        mSpec = mTabHost.newTabSpec("Yearly");
        mSpec.setContent(R.id.Yearly);
        mSpec.setIndicator("Yearly");
        mTabHost.addTab(mSpec);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int i = mTabHost.getCurrentTab();
                if (i == 0) {
                    if (sound_on){click_sound.start();}
                    time_title.setText("TIME (Hours)");
                    // your method 1
                    chart1.invalidate(); // refresh
                    chart1.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds
                }
                else if (i ==1) {
                    if (sound_on){click_sound.start();}
                    time_title.setText("TIME (Days)");
                    //your method 2
                    chart2.invalidate(); // refresh
                    chart2.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds
                }
                else if (i ==2) {
                    if (sound_on){click_sound.start();}
                    time_title.setText("TIME (Months)");
                    //your method 3
                    chart3.invalidate(); // refresh
                    chart3.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds
                }
            }
        });

        final Intent intent = getIntent ();
        username = intent.getStringExtra ("username");

        forresearchers_button = findViewById(R.id.forresearchers_button);
        cancel_button = findViewById(R.id.cancel_button);

        chart1 = findViewById(R.id.chart1);
        chart2 = findViewById(R.id.chart2);
        chart3 = findViewById(R.id.chart3);
        chart4 = findViewById(R.id.chart4);




        chart1.getDescription().setEnabled(false);
        chart2.getDescription().setEnabled(false);
        chart3.getDescription().setEnabled(false);
        chart4.getDescription().setEnabled(false);

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        List<BarEntry> yVals4 = new ArrayList<>();

        for (int i = 0; i <= 24; i++) {

            if (i == hour){

                yVals1.add(new Entry(i,int_accRate));
                //break;
            }else if (i<hour){

                if (i == 0){yVals1.add(new Entry(i,5));}
                else if (i == 1){ yVals1.add(new Entry(i,5));}
                else if (i == 2){yVals1.add(new Entry(i,8));}
                else if (i == 3){yVals1.add(new Entry(i,3));}
                else if (i == 4){yVals1.add(new Entry(i,4));}
                else if (i == 5){yVals1.add(new Entry(i,9));}
                else if (i == 6){yVals1.add(new Entry(i,10));}
                else if (i == 7){yVals1.add(new Entry(i,2));}
                else if (i == 8){yVals1.add(new Entry(i,2));}
                else if (i == 9){yVals1.add(new Entry(i,4));}
                else if (i == 10){yVals1.add(new Entry(i,6));}
                else if (i == 11){yVals1.add(new Entry(i,3));}
                else if (i == 12){yVals1.add(new Entry(i,3));}
                else if (i == 13){yVals1.add(new Entry(i,2));}
                else if (i == 14){yVals1.add(new Entry(i,5));}
                else if (i == 15){yVals1.add(new Entry(i,7));}
                else if (i == 16){yVals1.add(new Entry(i,7));}
                else if (i == 17){yVals1.add(new Entry(i,8));}
                else if (i == 18){yVals1.add(new Entry(i,4));}
                else if (i == 19){yVals1.add(new Entry(i,3));}
                else if (i == 20){yVals1.add(new Entry(i,6));}
                else if (i == 21){yVals1.add(new Entry(i,10));}
                else if (i == 22){yVals1.add(new Entry(i,7));}
                else if (i == 23){yVals1.add(new Entry(i,5));}
                else if (i == 24){yVals1.add(new Entry(i,2));}
                //int val1 = (int) (Math.random()*10);
                //total_val1 = total_val1 + val1;
                //yVals1.add(new Entry(i,val1));
                }
                else if (i>hour){

                yVals1.add(new Entry(i,0));

            }


        }

        for (int i = 1; i <= 30; i++) {
            int val1 = (int) (Math.random()*10);
            int val2 = (int) (Math.random()*100) ;
            int val3 = (int) (Math.random()*100);
            total_val1 = total_val1 + val1;
            total_val2 = total_val2 + val2;
            total_val3 = total_val3 + val3;
            yVals2.add(new Entry(i,val1));
        }

        for (int i = 1; i <= 12; i++) {
            int val1 = (int) (Math.random()*10);
            int val2 = (int) (Math.random()*100) ;
            int val3 = (int) (Math.random()*100);
            total_val1 = total_val1 + val1;
            total_val2 = total_val2 + val2;
            total_val3 = total_val3 + val3;
            yVals3.add(new Entry(i,val1));
        }


        for (int i = 1; i <= 2; i++) {
            int val1 = (int) (Math.random()*10);
            int val2 = (int) (Math.random()*100) ;
            int val3 = (int) (Math.random()*100);
            total_val1 = total_val1 + val1;
            total_val2 = total_val2 + val2;
            total_val3 = total_val3 + val3;
            yVals4.add(new BarEntry(i,val1));
        }



        LineDataSet dataSet1 = new LineDataSet(yVals1, "Score"); // add entries to dataset
        LineDataSet dataSet2 = new LineDataSet(yVals2, "Score"); // add entries to dataset
        LineDataSet dataSet3 = new LineDataSet(yVals3, "Score"); // add entries to dataset
        BarDataSet dataSet4 = new BarDataSet(yVals4, "Average"); // add entries to dataset



        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_green);

        dataSet1.setDrawFilled(true);
        dataSet1.setFillDrawable(drawable);

        dataSet2.setDrawFilled(true);
        dataSet2.setFillDrawable(drawable);

        dataSet3.setDrawFilled(true);
        dataSet3.setFillDrawable(drawable);

        dataSet1.setDrawCircleHole(false);
        dataSet1.setDrawCircles(false);

        dataSet2.setDrawCircleHole(false);
        dataSet2.setDrawCircles(false);

        dataSet3.setDrawCircleHole(false);
        dataSet3.setDrawCircles(false);

        dataSet4.setColors(Color.rgb(142,169,64),Color.rgb(70,87,42));

        dataSet1.setColor(Color.rgb(142,169,64));
        //dataSet1.setCircleColor(Color.rgb(142,169,64));
        LineData lineData1 = new LineData(dataSet1);



        dataSet2.setColor(Color.rgb(142,169,64));
        //dataSet2.setCircleColor(Color.rgb(142,169,64));
        LineData lineData2 = new LineData(dataSet2);

        dataSet3.setColor(Color.rgb(142,169,64));
        //dataSet3.setCircleColor(Color.rgb(142,169,64));
        LineData lineData3 = new LineData(dataSet3);

        BarData BarData = new BarData(dataSet4);
        BarData.setBarWidth(0.5f); // set custom bar width

        LegendEntry legendEntryA = new LegendEntry();
        legendEntryA.label = "Your's";
        legendEntryA.formColor = Color.rgb(142,169,64);
        LegendEntry legendEntryB = new LegendEntry();
        legendEntryB.label = "Others";
        legendEntryB.formColor = Color.rgb(70,87,42);


        lineData1.setValueFormatter(formatter);
        lineData2.setValueFormatter(formatter);
        lineData3.setValueFormatter(formatter);
        BarData.setValueFormatter(formatter);

        chart1.getXAxis().setGranularity(1f);
        chart2.getXAxis().setGranularity(1f);
        chart3.getXAxis().setGranularity(1f);
        chart4.getXAxis().setGranularity(1f);

        chart1.getAxis(YAxis.AxisDependency.RIGHT).setGranularity(1f);
        chart2.getAxis(YAxis.AxisDependency.RIGHT).setGranularity(1f);
        chart3.getAxis(YAxis.AxisDependency.RIGHT).setGranularity(1f);
        chart4.getAxis(YAxis.AxisDependency.RIGHT).setGranularity(1f);

        chart4.getLegend().setCustom(Arrays.asList(legendEntryA, legendEntryB));
        chart1.setData(lineData1);
        chart1.invalidate(); // refresh
        chart1.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds

        chart2.setData(lineData2);
        //chart2.setScaleMinima(10f, 1f);
        //chart2.moveViewToAnimated(40, 0, YAxis.AxisDependency.LEFT, 2000);
        chart2.invalidate(); // refresh
        chart2.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds

        chart3.setData(lineData3);
        //chart3.setScaleMinima(20f, 1f);
        //chart3.moveViewToAnimated(300, 0, YAxis.AxisDependency.LEFT, 2000);
        chart3.invalidate(); // refresh
        chart3.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds

        chart4.setData(BarData);
        chart4.invalidate(); // refresh
        chart4.setFitBars(true); // make the x-axis fit exactly all bars
        chart4.animateXY(200, 300); // animate horizontal and vertical 3000 milliseconds


        forresearchers_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (sound_on){click_sound.start();}
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                width = displayMetrics.widthPixels;
                height = displayMetrics.heightPixels;
                currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(ProgressActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ProgressActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                        ActivityCompat.requestPermissions(ProgressActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(ProgressActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted

                    //generate data
                    StringBuilder data = new StringBuilder();
                    data.append("Number,deviceID,deviceModel,deviceRes,userID,age,gender,handedness,userPerf,session,sessionDate,sessionPerf,trialNum,trialStartTime,trialAnswer,trialCond,trialResp,trialRespTime,trialEndTime");
                    for (int i = 1; i <= 40; i++) {
                        data.append("\n" + i + "," + _ID + "," + _MANUFACTURER + " " + _MODEL + "," + width + " x " + height + "," + "79004" + "," + "23" + "," + "-1" + "," + "1" + "," + "0.92" + "," + "1" + "," + currentDate + "," + "0.95" + "," + "1" + "," + "1" + "," + "1" + "," + "0" + "," + "1" + "," + "1.5" + "," + "2");
                    }
                    try {

                        //saving the file into device
                        FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                        out.write((data.toString()).getBytes());
                        out.close();

                        //exporting
                        Context context = getApplicationContext();
                        File filelocation = new File(getFilesDir(), "data.csv");
                        Uri path = FileProvider.getUriForFile(context, "com.android.jungledjumble.fileprovider", filelocation);
                        Intent fileIntent = new Intent(Intent.ACTION_SEND);
                        fileIntent.setType("text/csv");
                        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                        startActivity(Intent.createChooser(fileIntent, "Send mail"));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });




        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (sound_on){click_sound.start();}
                background_sound.pause();
                Intent intent = new Intent(ProgressActivity.this, StartActivity.class);
                intent.putExtra ("sound_on",sound_on);
                intent.putExtra ("music_on",music_on);
                background_sound.pause();
                startActivity(intent);
            }
        });





    }


    ValueFormatter formatter = new ValueFormatter() {
        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    //generate data
                    StringBuilder data = new StringBuilder();
                    data.append("Number,deviceID,deviceModel,deviceRes,userID,age,gender,handedness,userPerf,session,sessionDate,sessionPerf,trialNum,trialStartTime,trialAnswer,trialCond,trialResp,trialRespTime,trialEndTime");
                    for (int i = 1; i <= 40; i++) {
                        data.append("\n" + i + "," + _ID + "," + _MANUFACTURER + " " + _MODEL + "," + width + " x " + height + "," + "79004" + "," + "23" + "," + "-1" + "," + "1" + "," + "0.92" + "," + "1" + "," + currentDate + "," + "0.95" + "," + "1" + "," + "1" + "," + "1" + "," + "0" + "," + "1" + "," + "1.5" + "," + "2");
                    }
                    try {
                        //saving the file into device
                        FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                        out.write((data.toString()).getBytes());
                        out.close();
                        //exporting
                        Context context = getApplicationContext();
                        File filelocation = new File(getFilesDir(), "data.csv");
                        Uri path = FileProvider.getUriForFile(context, "com.android.jungledjumble.fileprovider", filelocation);
                        Intent fileIntent = new Intent(Intent.ACTION_SEND);
                        fileIntent.setType("text/csv");
                        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                        startActivity(Intent.createChooser(fileIntent, "Send mail"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }



}