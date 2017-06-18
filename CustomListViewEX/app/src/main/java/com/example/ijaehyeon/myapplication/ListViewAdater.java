package com.example.ijaehyeon.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 5. 13..
 */

public class ListViewAdater extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdater(){

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemview, parent, false);
        }

        ImageView imgView = (ImageView)convertView.findViewById(R.id.imgView);

        ListViewItem listViewItem = listViewItemList.get(position);

        imgView.setImageBitmap(listViewItem.getImg());

        return convertView;
    }

    public void addItem(Bitmap img){
        ListViewItem item = new ListViewItem();
        item.setImg(img);

        listViewItemList.add(item);
    }
}
