package com.coursera.gp.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private SeekBar colorSeeker;
    private View leftBox1;
    private View leftBox2;
    private View rightBox1;
    private View rightBox2;
    private View rightBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorSeeker = (SeekBar) findViewById(R.id.colorSeeker);
        leftBox1 = (View) findViewById(R.id.Lbox1);
        leftBox2 = (View) findViewById(R.id.Lbox2);
        rightBox1 = (View) findViewById(R.id.Rbox1);
        rightBox2 = (View) findViewById(R.id.Rbox2);
        rightBox3 = (View) findViewById(R.id.Rbox3);
        //Sets to default colors
        resetColors();

        colorSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("PROGRESS", Integer.toString(progress));
                //Updates colors according to seekBar value
                updateColors(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateColors(int progressValue){
        //Converts seekBar value to a integer value between 0-255
        int factor = (progressValue* 255)/100;
        Log.i("RGB_FACTOR", Integer.toString(factor));
        //Set new color to the boxes
        leftBox1.setBackgroundColor(Color.argb(255, factor, 200, factor));
        leftBox2.setBackgroundColor(Color.argb(255, factor, 153, 0));
        rightBox1.setBackgroundColor(Color.argb(255, 153, 51, factor));
        rightBox3.setBackgroundColor(Color.argb(255, factor, factor, 50));
    }

    private void resetColors(){
        colorSeeker.setProgress(0);
        leftBox1.setBackgroundColor(Color.argb(255, 0, 200, 0));
        leftBox2.setBackgroundColor(Color.argb(255, 0, 153, 0));
        rightBox1.setBackgroundColor(Color.argb(255, 153, 51, 0));
        rightBox2.setBackgroundColor(Color.argb(255, 255, 255, 255));
        rightBox3.setBackgroundColor(Color.argb(255, 0, 0, 50));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.moreinfo) {
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create(); //Read Update
            alertDialog.setMessage(getResources().getString(R.string.infoMessage1) + "\n" + getResources().getString(R.string.infoMessage2));

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(R.string.notNowButton),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Log.i("DIALOG", getResources().getString(R.string.notNowButton));
                            alertDialog.dismiss();
                        }
                    });

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(R.string.visitButton),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Log.i("DIALOG", getResources().getString(R.string.visitButton));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(getResources().getString(R.string.urlMOMA_PIET)));
                            startActivity(i);
                        }
                    });


            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
