package com.example.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhbj.base.BasePager;

public class HomePager extends BasePager {


    public HomePager(Activity activity) {
        super(activity);

    }

    @Override
    public void initData() {
        // Log.d(TAG, "main page loaded");
        //fill out the framelayout
        System.out.println("首页初始化了。。。。");
        TextView view = new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        //modify title
        tvTitle.setText("智慧北京");
        btnMenu.setVisibility(View.GONE);
    }


}
