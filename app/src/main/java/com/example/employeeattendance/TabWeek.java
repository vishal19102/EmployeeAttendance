package com.example.employeeattendance;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surya on 12/24/2016.
 */
public class TabWeek extends Fragment implements View.OnClickListener {

    private static final String TAG = "HELLO";
    ModelData modelData = new ModelData();

    String url = "http://www.vishalsri.esy.es/fetch.php";
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_week, container, false);
        btn = (Button) v.findViewById(R.id.sho);
        show();
        return v;

    }

    void show() {
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.sho) {
           /* JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, response.toString());
                    Toast.makeText(getContext(),response.toString(),
                            Toast.LENGTH_LONG).show();



                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    Toast.makeText(getContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    // hide the progress dialo
                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq);
*/

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Toast.makeText(getContext(),
                    //       response.toString(), Toast.LENGTH_LONG).show();
                    try {
                        parsejson(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }
    }

    void parsejson(String response) throws JSONException {
        JSONObject jObject = new JSONObject(response); // json
        JSONArray jsonArray = jObject.getJSONArray("DATA"); // get data object
        List<ModelData> data = new ArrayList<>();
        /*Toast.makeText(getContext(),
                jsonArray.toString(), Toast.LENGTH_LONG).show();*/// get the name
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_data = jsonArray.getJSONObject(i);
            Toast.makeText(getContext(),
                    json_data.toString(), Toast.LENGTH_LONG).show();
            ModelData modelData = new ModelData();
            modelData.time_in = json_data.getString("time_in");
            modelData.time_out = json_data.getString("time_out");
           /* modelData.Location = json_data.getString("Location");
            modelData.status = json_data.getString("status");*/
            modelData.date = json_data.getString("date");
            data.add(modelData);

            Toast.makeText(getContext(),
                   data.get(i).toString(), Toast.LENGTH_LONG).show();// get the name from data.
        }


    }
}
