package com.example.eldeeb.whishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wishlist extends AppCompatActivity {

    private String login_url = "http://lensaty.net/api/services/app/MemberWishlist/GetMyWishlist";
    private String tokenRecieve;
    List<userwishes> userwishesList;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        Intent b = getIntent();
        tokenRecieve = b.getStringExtra("accessToken");
        userwishesList = new ArrayList<>();
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(this);

        Log.d("MyAuthorization", tokenRecieve);

        StringRequest request = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("wishlist response", response);

                try {
                    JSONObject object = new JSONObject(response);

                    //JSONObject json2 = object.getJSONObject("result");
                    //Log.d("sddfsdfsd",json2.toString());
                    JSONArray array = object.getJSONArray("result");

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject usrwish = array.getJSONObject(i);
                        userwishesList.add(new userwishes(usrwish.getString("name")
                                ,usrwish.getDouble("price"),usrwish.getString("imagePath")));



                    }
                } catch (JSONException e) {
                    Log.d("JSONError",e.getMessage());
                    e.printStackTrace();
                }

                wishAdapter adapter = new wishAdapter(wishlist.this,userwishesList);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Error:::" + error, Toast.LENGTH_LONG).show();
                Log.d("Error", error.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + tokenRecieve);
                return headers;
            }
        };
        queue.add(request);
    }
}
