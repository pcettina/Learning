package edu.fandm.pcettina.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Main"; //can put your name or filename to help find in logcat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tip_go_button = (Button) findViewById(R.id.tip_launch_bt);
        tip_go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TipCalculator.class);
                startActivity(i);
            }
        });

        SeekBar sb = (SeekBar) findViewById(R.id.random_seekbar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "poistion: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button life_cycle_button = (Button) findViewById(R.id.life_cycle_bt);
        tip_go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), activity_lifecycle.class);
                startActivity(i);
            }
        });

        Button vibration_button = (Button) findViewById(R.id.vibrationBT);
        vibration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchOtherAct();
            }
        });


    }

    public void launchOtherAct() {
        Intent i = new Intent(this, Vibration.class);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
        i.putExtra("color", color); //will need this for a calculator putStringextra/arrayextra
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,m);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.quit_menu_item) {
            finish();
        }
        else if(item.getItemId() == R.id.tip_calc_item) {
            Intent i = new Intent(getApplicationContext(), TipCalculator.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.Vibration_menu_item) {
            launchOtherAct();
        }
        else if(item.getItemId() == R.id.fibonaccireponse_menu_item){
            Intent i = new Intent(this, FibonacciUnresponsive.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.fibonaccibetter_menu_item){
            Intent i = new Intent(this, FibonacciResponsive.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.location_menu_item){
            Intent i = new Intent(this, Location_activity.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.CatFact_menu_item){
            Intent i = new Intent(this, CatFact.class);
            startActivity(i);
        }
        return true;
    }


}