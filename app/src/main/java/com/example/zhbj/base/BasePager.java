package com.example.zhbj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zhbj.R;

public class BasePager {
    public Activity mActivity;
    public TextView tvTitle;
    public ImageButton btnMenu;
    //empty  frameayout dynamicly add layouts
    public FrameLayout flContent;
    //current layout
    public View mRootView;

    public BasePager(Activity activity) {
        this.mActivity = activity;

        mRootView = initView();

    }

    public View initView() {

        View view = View.inflate(mActivity, R.layout.base_pager, null);
        tvTitle = view.findViewById(R.id.tv_title);
        btnMenu = view.findViewById(R.id.btn_menu);
        flContent = view.findViewById(R.id.fl_content);
        return view;
    }

    public void initData() {

    }
}
