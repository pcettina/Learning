package edu.fandm.pcettina.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

public class Vibration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);

        Intent i = getIntent();
        if(i != null){
            int color = i.getIntExtra("color", 0);
            View cl = findViewById(R.id.vibration_cl);


            cl.setBackgroundColor(color);
        }

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            VibrationEffect ve = VibrationEffect.createOneShot(750, VibrationEffect.DEFAULT_AMPLITUDE);
            v.vibrate(ve);
        } else{
            v.vibrate(500);
        }



    }
}