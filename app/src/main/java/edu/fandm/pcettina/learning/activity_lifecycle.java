package edu.fandm.pcettina.learning;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class activity_lifecycle extends AppCompatActivity {
    public static final String TAG = "LifeCycleApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        Log.d(TAG, "onCreate was called!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart was called!");
    }

    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume was called!");
    }

    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause was called!");
    }

    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop was called!");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy was called!");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart was called!");
    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("MyString", "Welcome back to Android!");
        Log.d(TAG, "onSaveInstanceState was called!");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String myString = savedInstanceState.getString("MyString");
        Log.d(TAG, "onRestoreInstanceState was called!, myString: " + myString);
    }
}