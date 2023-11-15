package edu.fandm.pcettina.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Calendar;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

        Button reg = (Button) findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String passwrd = ((EditText) findViewById(R.id.passwrd)).getText().toString();

                Task s = fbAuth.createUserWithEmailAndPassword(email, passwrd);

                s.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = fbAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "New User Created", Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(getApplicationContext(), "Failed to create new user :(", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        Button log = (Button) findViewById(R.id.sign_in);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String passwrd = ((EditText) findViewById(R.id.passwrd)).getText().toString();

                Task s = fbAuth.signInWithEmailAndPassword(email, passwrd);

                s.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = fbAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            TextView tv = (TextView) findViewById(R.id.login_status);
                            tv.setText("Login Successful!\nUID: " + user.getUid());

                        } else{
                            Toast.makeText(getApplicationContext(), "Failed to create new user :(", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }


}