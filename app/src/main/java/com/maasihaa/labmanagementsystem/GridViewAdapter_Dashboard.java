package com.maasihaa.labmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridViewAdapter_Dashboard extends BaseAdapter {

  static Activity mActivity;
  private static LayoutInflater inflater = null;
  int[] Imageid;
  String[] imagedetail;

  public GridViewAdapter_Dashboard(Activity activity, int[] imageid, String[] imagedetail) {
    mActivity = activity;
    this.Imageid = imageid;
    this.imagedetail = imagedetail;
    inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return Imageid.length;
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
      v = inflater.inflate(R.layout.gv_item, null);
      ImageView iv_homescreen = v.findViewById(R.id.iv_gv_homescreen);
      TextView tv_homescreen = v.findViewById(R.id.tv_gv_homescreen);
      iv_homescreen.setImageResource(Imageid[i]);
      tv_homescreen.setText(imagedetail[i]);
    } else {
      v = view;
      ImageView iv_homescreen = v.findViewById(R.id.iv_gv_homescreen);
      TextView tv_homescreen = v.findViewById(R.id.tv_gv_homescreen);
      iv_homescreen.setImageResource(Imageid[i]);
      tv_homescreen.setText(imagedetail[i]);
    }

    return v;
  }
}
