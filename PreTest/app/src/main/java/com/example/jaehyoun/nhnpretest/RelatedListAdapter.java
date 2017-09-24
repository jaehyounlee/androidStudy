package com.example.jaehyoun.nhnpretest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RelatedListAdapter extends BaseAdapter {

    ArrayList<ContentsValues> mArrayList;


    ListItemClickListener clickListener;

    public void setClickListener(ListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


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

        related_title.setText(listViewItem.getTitie());
        related_contents.setText(listViewItem.getContents());

        new ImageLoader(listViewItem.getThumbnail_URl(), imgView).execute();
        convertView.setTag(listViewItem.getTitie());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick((String)v.getTag());
            }
        });

        return convertView;
    }

    interface ListItemClickListener{
        void onItemClick(String keyword);
    }
}
