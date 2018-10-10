package com.example.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhbj.base.BasePager;

public class SettingPager extends BasePager {


    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        //fill out the framelayout
        System.out.println("设置初始化啦");
        TextView view = new TextView(mActivity);
        view.setText("设置页面");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
        tvTitle.setText("设置页面");
        btnMenu.setVisibility(View.VISIBLE);
    }


}
