package com.example.jaehyoun.nhnpretest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 9. 23..
 */


public class RelatedListAdapter extends BaseAdapter {

    ArrayList<ContentsValues> mArrayList;

    public RelatedListAdapter(ArrayList<ContentsValues> datas) {
        this.mArrayList = datas;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView imgView = (ImageView)convertView.findViewById(R.id.related_thumbnail);
        TextView related_title = (TextView) convertView.findViewById(R.id.related_title);
        TextView related_contents = (TextView) convertView.findViewById(R.id.releated_contents);


        ContentsValues listViewItem = mArrayList.get(position);

        imgView.setImageBitmap(listViewItem.getThumbnail());
        related_title.setText(listViewItem.getTitie());
        related_contents.setText(listViewItem.getContents());

        return convertView;
    }
}
