package com.example.zhbj.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.zhbj.MainActivity;

import zhbj.base.BaseMenuDetailPager;
/**
 * �˵�����ҳ--��ͼ
 * @author Administrator
 *
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager {
	MainActivity activity;
	public PhotoMenuDetailPager(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView view = new TextView(activity);
		view.setText("�˵�����ҳ--��ͼ");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		return view;
	}

}
