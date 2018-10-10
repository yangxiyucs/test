package com.example.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhbj.base.BasePager;

public class SmartServicePager extends BasePager {


    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //fill out the framelayout
        System.out.println("智慧服务初始化了");
        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
        tvTitle.setText("生活");
        btnMenu.setVisibility(View.VISIBLE);

    }


}
