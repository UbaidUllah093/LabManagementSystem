package com.maasihaa.labmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sohaib_Rafique on 8/30/2018.
 */

public class Listview_adapter extends BaseAdapter {

    static LayoutInflater layoutInflater = null;
    Activity mActivity;
    ArrayList<HashMap<String, String>> mfinalbill;
    HashMap<String, String> mobj1;
    HashMap<String, String> temp;
    int total = 0;

    public Listview_adapter(Activity activity, ArrayList<HashMap<String, String>> finalbill, HashMap<String, String> obj1) {
        mActivity = activity;
        mfinalbill = finalbill;
        mobj1 = obj1;

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mfinalbill.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = null;

        if (view == null) {
            v = new View(mActivity);
            v = layoutInflater.inflate(R.layout.listview_item, null);
            TextView tv_sr = v.findViewById(R.id.tv_sr);
            TextView tv_name = v.findViewById(R.id.tv_bill_productname);
            TextView tv_size = v.findViewById(R.id.tv_bill_productsize);
            TextView tv_quantity = v.findViewById(R.id.tv_billquantity);
            TextView tv_price = v.findViewById(R.id.tv_billprice);
            TextView tv_amount = v.findViewById(R.id.tv_billamount);

            temp = mfinalbill.get(i);

            tv_sr.setText(String.valueOf(i+1));
            tv_name.setText(temp.get("product_name").toString());
            tv_size.setText(temp.get("product_size").toString());
            tv_quantity.setText(temp.get("product_quantity").toString());
            tv_price.setText(temp.get("product_price").toString());
            tv_amount.setText(String.valueOf(Integer.parseInt(temp.get("product_quantity"))*Integer.parseInt(temp.get("product_price"))));
            //tv_home.setText(imagedetail[i]);
            total = total + (Integer.parseInt(temp.get("product_quantity"))*Integer.parseInt(temp.get("product_price")));
        } else {
            v = view;
            TextView tv_sr = v.findViewById(R.id.tv_sr);
            TextView tv_name = v.findViewById(R.id.tv_bill_productname);
            TextView tv_size = v.findViewById(R.id.tv_bill_productsize);
            TextView tv_quantity = v.findViewById(R.id.tv_billquantity);
            TextView tv_price = v.findViewById(R.id.tv_billprice);
            TextView tv_amount = v.findViewById(R.id.tv_billamount);

            temp = mfinalbill.get(i);

            tv_sr.setText(String.valueOf(i+1));
            tv_name.setText(temp.get("product_name").toString());
            tv_size.setText(temp.get("product_size").toString());
            tv_quantity.setText(temp.get("product_quantity").toString());
            tv_price.setText(temp.get("product_price").toString());
            tv_amount.setText(String.valueOf(Integer.parseInt(temp.get("product_quantity"))*Integer.parseInt(temp.get("product_price"))));
        }

        return v;
    }

    public int getTotal(){
        return total;
    }

}
