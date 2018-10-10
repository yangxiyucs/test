package com.example.zhbj.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import zhbj.base.BaseMenuDetailPager;

/**
 * �˵�����ҳ--����
 * 
 * @author Administrator
 * 
 */
public class TopicMenuDetailPager extends BaseMenuDetailPager {

	public TopicMenuDetailPager(Activity activity) {
		super(activity);

	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("�˵�����ҳ--ר��");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		return view;
	}

}
