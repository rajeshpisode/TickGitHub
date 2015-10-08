package com.piyush.tick.adapter;

/**
 * Created by hp on 10/6/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.piyush.tick.R;

public class IconChooserAdapter extends BaseAdapter {
    private Context mContext;
    private static LayoutInflater inflater = null;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher
    };

    // Constructor
    public IconChooserAdapter(Context c) {
        mContext = c;
        inflater = (LayoutInflater) c.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.chooser_item, null);


        holder.img = (ImageView) rowView.findViewById(R.id.img_choose_icon);


        holder.img.setImageResource(mThumbIds[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "You Clicked " + position, Toast.LENGTH_LONG).show();
            }
        });

        if(position == mThumbIds.length){
            holder.img.setVisibility(View.GONE);
            rowView.setBackgroundResource(R.drawable.load_more_button);
        }else{

        }

        return rowView;
    }

    public class Holder {

        ImageView img;
    }

}

