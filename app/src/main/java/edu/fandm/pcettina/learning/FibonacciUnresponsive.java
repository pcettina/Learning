package edu.fandm.pcettina.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FibonacciUnresponsive extends AppCompatActivity {

    public final static String TAG = "FibUnresponsive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_unresponsive);
    }

    private long recurFibHelper(long n){
        if(n == 0){
            return 0;
        } else if( n == 1) {
            return 1;
        } else {
            return recurFibHelper(n-1) + recurFibHelper(n - 2);
        }
    }

    public void recurFib(View v){ //INSIDE XML the onlick is set to this function
        // TO-DO: User preference
        EditText et = (EditText)findViewById(R.id.fib_et_n);
        int n = Integer.parseInt(et.getText().toString());

        if(n < 0){
            Toast.makeText(this, "N cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        showWorking(true);
        long start = System.currentTimeMillis();
        long ans = recurFibHelper(n);
        long end = System.currentTimeMillis();
        showWorking(false);

        Log.d(TAG, "Time spent calculating: " + (end - start) + "ms");

        TextView tv = (TextView)findViewById(R.id.fib_tv_ans);
        tv.setText("The Fibonacci Number is: " + String.valueOf(ans));

    }


    private void showWorking(boolean on) {
        Log.d(TAG, "working...");
        View v = findViewById(R.id.fib_tv_working);
        if (on) {
            v.setVisibility(View.VISIBLE);
        } else {
            v.setVisibility(View.INVISIBLE);
        }
    }
}