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
        final Context context = parent.getContext();
        RelatedListItemHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            viewHolder = new RelatedListItemHolder();
            viewHolder.thumbnail = (ImageView)convertView.findViewById(R.id.related_thumbnail);
            viewHolder.title = (TextView) convertView.findViewById(R.id.related_title);;
            viewHolder.content = (TextView) convertView.findViewById(R.id.releated_contents);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RelatedListItemHolder) convertView.getTag();
        }

        ContentsValues listViewItem = mArrayList.get(position);
        if(listViewItem !=null) {
            viewHolder.title.setText(listViewItem.getTitie());
            viewHolder.content.setText(listViewItem.getContents());
            new ImageLoader(listViewItem.getThumbnail_URl(), viewHolder.thumbnail).execute();
        }
        return convertView;
    }
    class RelatedListItemHolder {
        public ImageView thumbnail;
        public TextView title;
        public TextView content;
    }
}
