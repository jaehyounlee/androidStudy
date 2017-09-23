package com.example.ijaehyeon.speedtouchgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 7. 21..
 */

public class ImageGridAdapter extends BaseAdapter {
    Context context =null;
    LayoutInflater inflater =null;
    ArrayList<Drawable> imgDs = null;

    public ImageGridAdapter(Context context, ArrayList<Drawable> imageData) {
        context = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        imgDs = imageData;
    }
    @Override
    public int getCount() {
        return imgDs.size();
    }

    @Override
    public Object getItem(int position) {
        return imgDs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if(null == convertView){
            convertView = inflater.inflate(R.layout.gridview_item_laout, parent, false);
        }
        imageView = (ImageView)convertView.findViewById(R.id.imgView1);
        imageView.setImageDrawable((Drawable) getItem(position));

        return convertView;
    }
}

