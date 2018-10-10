package com.example.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhbj.base.BasePager;

public class GovAffairsPager extends BasePager {


    public GovAffairsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //fill out the framelayout
        TextView view = new TextView(mActivity);
        view.setText("政务公开");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
        tvTitle.setText("政务公开");

        btnMenu.setVisibility(View.VISIBLE);


    }


}
