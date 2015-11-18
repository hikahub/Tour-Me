package com.example.queene.tourmeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.queene.tourmeapp.adapter.CustomListAdapter;
import com.example.queene.tourmeapp.adapter.GPSTracker;
import com.example.queene.tourmeapp.adapter.Locations;
import com.example.queene.tourmeapp.controller.AppController;
import com.example.queene.tourmeapp.model.landmarks;
import com.example.queene.tourmeapp.util.CustomRequestJsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Landmarks extends Fragment {

    View rootview;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "";
    private List<landmarks> itemList = new ArrayList<landmarks>();
    private ProgressDialog pDialog;
    private CustomListAdapter adapter;
    ListView listView;
    Locations ls;
    GPSTracker gps;
    //  Button moreInfo;

    @Nullable

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_landmarks, container, false);
        listView = (ListView) rootview.findViewById(R.id.listOfItem);
        listView.setClickable(false);
        adapter = new CustomListAdapter(rootview, itemList);

        listView.setAdapter(adapter);


        ls =  new Locations(rootview);
        gps = new GPSTracker(rootview.getContext());



        // CurrentLoaction cn = new CurrentLoaction(rootview);
        Map<String, String> params = new HashMap<String, String>();
        // String lat = String.valueOf(lati);
        // String lng = String.valueOf(longi)

        params.put("Latitude", String.valueOf(gps.getLatitude()));
        params.put("Longitude",  String.valueOf(gps.getLongitude()));

        //  params.put("Latitude", String.valueOf(ls.getLat()));
        // params.put("Longitude",  String.valueOf(ls.getLng()));





        CustomRequestJsonArray itemRequest = new CustomRequestJsonArray(Request.Method.POST,url,params,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                landmarks newItem = new landmarks();
                                newItem.setPlace_name(obj.getJSONObject("fields").getString("name"));
                                newItem.setAddress(obj.getJSONObject("fields").getString("address"));
                                newItem.setPlace_desc(obj.getJSONObject("fields").getString("place_desc"));
                                newItem.setWiki_link(obj.getJSONObject("fields").getString("wiki_link"));
                                newItem.setOpening_days(obj.getJSONObject("fields").getString("opening_days"));
                                newItem.setAdmission(obj.getJSONObject("fields").getString("admission"));
                                newItem.setImageUrl("http://45.55.201.193:8000/" +obj.getJSONObject("fields").getString("image"));

                                // adding item to item array

                                itemList.add(newItem);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }


                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Opps" + error.getMessage());

                //    hideDialog();
            }

        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(itemRequest);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                landmarks test = (landmarks) parent.getItemAtPosition(position);
                String placeLoc = test.getAddress();
                Intent intent = new Intent(getActivity(),Landmark_info.class);
                intent.putExtra("id", placeLoc);
                Log.d(TAG,placeLoc);
                startActivity(intent);


            }
        });

        return rootview;
    }


    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
