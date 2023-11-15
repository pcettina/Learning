package edu.fandm.pcettina.learning;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class CatFact extends AppCompatActivity {

    private static final String TAG = "pcettina";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_fact);

        Button image_button = (Button) findViewById(R.id.rand_image);
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandImgExecutor rie = new RandImgExecutor();
                rie.fetch(ric);

            }
        });

        Log.d(TAG, "done downloading image");

        Button button = (Button) findViewById(R.id.but_catfact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatFactExecutor cfe = new CatFactExecutor();
                cfe.fetch(cfc);

            }
        });

        
    }

    CatFactCallback cfc = new CatFactCallback(){
        public void onComplete(String fact){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(fact != null) {
                        TextView tv = (TextView) findViewById(R.id.catfact_tv);
                        tv.setText(fact);
                    } else{
                        Toast.makeText(getApplicationContext(), "Failed to download cat fact :(", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };

    interface CatFactCallback{
        void onComplete(String fact);
    }


    public class CatFactExecutor{
        public void fetch(CatFactCallback cfc){
            ExecutorService es = Executors.newFixedThreadPool(1);
            //need to figure out where to put things
            es.execute(new Runnable() {
                @Override
                public void run() {
                    String fact = null;
                    try {
                        URL url = new URL("https://catfact.ninja/fact");
                        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.connect();


                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        StringBuffer data = new StringBuffer();
                        String curLine;
                        while((curLine = in.readLine()) != null){
                            Log.println(Log.WARN, "JSON", "line: " + curLine);
                            data.append(curLine);
                        }


                        JSONObject jsonCatFact = new JSONObject(data.toString());
                        fact = (String) jsonCatFact.get("fact");
                        int length = (Integer)jsonCatFact.get("length");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    cfc.onComplete(fact);
                }
            });

        }
    }

    RandImgCallback ric = new RandImgCallback(){
        public void onComplete(Bitmap img){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(img != null) {
                        ImageView tv = (ImageView) findViewById(R.id.img_iv);
                        tv.setImageBitmap(img);
                    } else{
                        Toast.makeText(getApplicationContext(), "Failed to download IMG :(", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };
    interface RandImgCallback{
        void onComplete(Bitmap img);
    }


    public class RandImgExecutor{
        public void fetch(RandImgCallback ric){
            ExecutorService es = Executors.newFixedThreadPool(1);
            //need to figure out where to put things
            es.execute(new Runnable() {
                @Override
                public void run() {
                    Bitmap img = null;
                    try{

                        URL url = new URL("https://images5.alphacoders.com/408/408941.jpg");
                        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.connect();
                        InputStream in = new BufferedInputStream(url.openStream());
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];
                        int n = 0;

                        while(-1!=(n=in.read(buf))){
                            out.write(buf,0,n);
                        }

                        out.close();
                        in.close();

                        byte[] response = out.toByteArray();
                        img = BitmapFactory.decodeByteArray(response, 0, response.length);

                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    ric.onComplete(img);
                }
            });
        }
    }
}