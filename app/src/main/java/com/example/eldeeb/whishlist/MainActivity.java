package com.example.eldeeb.whishlist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //vars
    String login_url = "http://lensaty.net/api/account/Authenticate";
    String token;
    String user_name, passwd;

    //init
    EditText email, password;
    Button login;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);
        dialog = new ProgressDialog(this);
//init dialog
        dialog.setMessage("Loading...");
        dialog.setTitle("Signing In");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_action();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // setText automatically
        if (BuildConfig.DEBUG) {
            email.setText("m.amgad15@yahoo.com");
            password.setText("123456");
        }
    }

    // performing User Login
    private void login_action() {
        user_name = email.getText().toString();
        passwd = password.getText().toString();
dialog.show();
dialog.setCancelable(false);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                JSONObject json = null;    // create JSON obj from string
                try {
                    json = new JSONObject(response);
                    JSONObject json2 = json.getJSONObject("result");
                    token = json2.getString("accessToken");
                    Log.d("MyyyyyAccess", token);
                    dialog.dismiss();
                    Intent i = new Intent(MainActivity.this, wishlist.class);
                    i.putExtra("accessToken", token);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Error" + error, Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("UsernameOrEmailAddress", user_name);
                params.put("Password", passwd);
                return params;
            }
        };
        queue.add(request);
    }
}
