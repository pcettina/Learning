package edu.fandm.pcettina.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciResponsive extends AppCompatActivity {
    public final static String TAG = "FibResponsive";

    private long start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_unresponsive);
    }
    FibCallback cb = new FibCallback(){

        public void onComplete(long result) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    long end = System.currentTimeMillis();
                    showWorking(false);
                    TextView tv = (TextView) findViewById(R.id.fib_tv_ans);
                    tv.setText("The fibonacci number is: " + String.valueOf(result));

                    Log.d(TAG, "tIME SPENT CALCULATING: " + (end - FibonacciResponsive.this.start) + "ms");
                }
            });
        }
    };

    interface FibCallback {
        void onComplete(long result);
    }

    public class FibonacciExecutor {
         public void compute(final int n, final FibCallback callback){
             ExecutorService es = Executors.newFixedThreadPool(1);
             es.execute(new Runnable() {
                 @Override
                 public void run() {
                     long res = recurFibHelper(n);
                     callback.onComplete(res);
                 }
             });
         }
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
        this.start = System.currentTimeMillis();
        FibonacciExecutor fe = new FibonacciExecutor();
        fe.compute(n, this.cb);

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