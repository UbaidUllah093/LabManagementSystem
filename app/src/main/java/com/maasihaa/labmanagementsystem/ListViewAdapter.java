package com.maasihaa.labmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Locale;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

        // Declare Variables

        Context mContext;
        LayoutInflater inflater;
        private List<String> animalNamesList = null;
        private ArrayList<String> arraylist;

public ListViewAdapter(Context context, List<String> animalNamesList) {
        mContext = context;
        this.animalNamesList = animalNamesList;
        inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arraylist = new ArrayList<String>();
        this.arraylist.addAll(animalNamesList);
        }

public class ViewHolder {
    TextView name;
}

    @Override
    public int getCount() {
        return animalNamesList.size();
    }

    @Override
    public String getItem(int position) {
        return animalNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(animalNamesList.get(position));
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        animalNamesList.clear();
        if (charText.length() == 0) {
            animalNamesList.addAll(arraylist);
        } else {
            for (int i = 0 ; i < arraylist.size(); i++) {
                if (arraylist.get(i).toLowerCase(Locale.getDefault()).contains(charText)) {
                    animalNamesList.add(arraylist.get(i));
                }
            }
        }
        if (animalNamesList.isEmpty()) {
            animalNamesList.add("No Record Found");
        }
        notifyDataSetChanged();
    }

}