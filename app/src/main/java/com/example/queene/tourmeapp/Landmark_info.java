package com.example.queene.tourmeapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.queene.tourmeapp.controller.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Landmark_info extends Activity {

    private EditText place_name;
    private EditText address;
    private EditText place_desc;
    private EditText wiki_link;
    private EditText opening_days;
    private EditText admission;
    double lat;
    double lang;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    String itemID;
    private static final String item_details_URL = "http://45.55.201.193:8000/itemInfo/";
    private Button directionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_info);

        place_name = (EditText) findViewById(R.id.pName);
        place_desc = (EditText) findViewById(R.id.descIM);
        wiki_link = (EditText) findViewById(R.id.wikiIM);
        address = (EditText) findViewById(R.id.addressIM);
        opening_days = (EditText) findViewById(R.id.opendaysIM);
        admission = (EditText) findViewById(R.id.admissionIM);

        place_name.setEnabled(false);
        place_desc.setEnabled(false);
        wiki_link.setEnabled(false);
        address.setEnabled(false);
        opening_days.setEnabled(false);
        admission.setEnabled(false);




        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemID = extras.getString("id");
        }
        onStartUp();



        directionButton = (Button) findViewById(R.id.DirectionID);

        directionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f",  lat,lang);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }

    public void onStartUp() {




        StringRequest strReq = new StringRequest(Request.Method.POST,
                item_details_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jrepo = new JSONObject(response);
                    place_name.setText(jrepo.getString("place name"));
                    place_desc.setText(jrepo.getString("place desc"));
                    address.setText(jrepo.getString("address"));
                    wiki_link.setText(jrepo.getString("wiki_link"));
                    opening_days.setText(jrepo.getString("opening_days"));
                    admission.setText(jrepo.getString("addmision"));
                    lat = Double.parseDouble(jrepo.getString("Latitude"));
                    lang = Double.parseDouble(jrepo.getString("Longitude"));

                    imageLoader = AppController.getInstance().getImageLoader();
                    NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail11);
                    thumbNail.setImageUrl("http://45.55.201.193:8000/" +jrepo.getString("image"), imageLoader);





                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //    hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("item", itemID);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);


    }

}
