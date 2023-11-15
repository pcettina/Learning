package edu.fandm.pcettina.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        Button tip_15 = (Button) findViewById(R.id.tip_15_bt);
        Button tip_20 = (Button) findViewById(R.id.tip_20_bt);
        Button tip_10 = (Button) findViewById(R.id.tip_10_bt);
        TipButtonOnClickListener x = new TipButtonOnClickListener();
        tip_15.setOnClickListener(x);
        tip_20.setOnClickListener(x);
        tip_10.setOnClickListener(x);
    }

    }
