package com.example.queene.tourmeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.queene.tourmeapp.R;
import com.example.queene.tourmeapp.controller.AppController;
import com.example.queene.tourmeapp.model.landmarks;

import java.util.List;

/**
 * Created by Queene on 11/11/2015.
 */
public class CustomListAdapter extends BaseAdapter {

    private View landmark;

    private LayoutInflater inflater;
    private List<landmarks> landmarklist;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(View activity, List<landmarks> appItems) {
        this.landmark = activity;
        this.landmarklist= appItems;
    }


    @Override
    public int getCount() {
        return landmarklist.size();
    }

    @Override
    public Object getItem(int location) {
        return landmarklist.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater =  (LayoutInflater) landmark.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail11);
        TextView place_name = (TextView) convertView.findViewById(R.id.pName);
        TextView address = (TextView) convertView.findViewById(R.id.addressIM);
        TextView place_desc = (TextView) convertView.findViewById(R.id.descIM);
        TextView wiki_link = (TextView) convertView.findViewById(R.id.wikiIM);
        TextView opening_days = (TextView) convertView.findViewById(R.id.opendaysIM);
        TextView admission = (TextView) convertView.findViewById(R.id.admissionIM);

        // getting movie data for the row
        landmarks m = landmarklist.get(position);

        // Item image
        thumbNail.setImageUrl(m.getImageUrl(), imageLoader);

        // title
        place_name.setText(m.getPlace_name());

        // address
        address.setText("Address: " +m.getAddress());

        // descrip
        place_desc.setText("Price: " +m.getPlace_desc());

        // wiki
        wiki_link.setText("Wiki: " +m.getWiki_link());

        // open days
        opening_days.setText("Open days: " +m.getOpening_days());

        // addmission
        admission.setText("Admission : " +m.getAdmission());

        return convertView;
    }
}
