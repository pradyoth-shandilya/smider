package com.example.hynd.smider;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {
    SeekBar seekBar1;
    Button submit;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);

        status = findViewById(R.id.status);
        seekBar1 = findViewById(R.id.seekBar1);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            int progress;

            @Override
            public void onClick(View v) {

                progress = seekBar1.getProgress();
                status.setText(Integer.toString(progress));
                String url = "http://ec2-54-149-169-53.us-west-2.compute.amazonaws.com/smider_open.php?percent="+ Integer.toString(progress);

// prepare the Request

                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error.Response", "blurhj");
                            }
                        }
                );

// add it to the RequestQueue
                queue.add(getRequest);

            }
        });
    }
}

