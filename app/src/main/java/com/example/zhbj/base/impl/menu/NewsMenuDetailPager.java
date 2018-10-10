package com.example.zhbj.base.impl.menu;

import java.util.ArrayList;

import com.example.zhbj.R;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import zhbj.MainActivity;
import zhbj.base.BaseMenuDetailPager;
import zhbj.domain.NewsMenu.NewsTabData;
import android.app.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import android.view.ViewGroup;

/**
 * �˵�����ҳ--����
 * 
 * ViewPagerIndicatorʹ�����̣� 1:����� 2.���support-v4��ͻ���������汾һ�£� 3.�����ӳ����п��������ļ�
 * 4.�����ӳ����п�����ش��루ָʾ����viewpager�󶨣���дgetPagerTitle���ر��⣩
 * 
 * 5.���嵥�ļ���������ʽ
 * 
 * 6.�����޸�Ϊ��ɫ 7.�޸���ʽ����������ʽ&������ʽ
 * 
 * @author Administrator
 * 
 */

public class NewsMenuDetailPager extends BaseMenuDetailPager implements
		OnPageChangeListener {

	// ��ע���������������ͬ��findviewbyId
	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mViewPager;
	@ViewInject(R.id.indicator)
	private TabPageIndicator mIndicator;
	private ArrayList<NewsTabData> mTabData;
	private ArrayList<TabDetailPager> mTabDetailPagers;

	public NewsMenuDetailPager(Activity activity,
			ArrayList<NewsTabData> children) {
		super(activity);
		mTabData = children;
	}

	@Override
	public View initView() {

		View view = View.inflate(mActivity, R.layout.paper_news_menu_detail,
				null);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void initData() {
		mTabDetailPagers = new ArrayList<TabDetailPager>();
		// ��ʼ��ҳǩ
		for (int i = 0; i < mTabData.size(); i++) {
			TabDetailPager pager = new TabDetailPager(mActivity,mTabData.get(i));
			mTabDetailPagers.add(pager);
		}

		mViewPager.setAdapter(new NewsMenuDetailAdapter());
		// ��ViewPager��ָʾ������һ��:������viewpager����������֮���ٰ�
		mIndicator.setViewPager(mViewPager);

		// mViewPager.setOnPageChangeListener(this);
		mIndicator.setOnPageChangeListener(this);// �˴��������ø�Indicator
													// ָʾ�������ܸ�ViewPager
	}

	class NewsMenuDetailAdapter extends PagerAdapter {

		@Override
		public CharSequence getPageTitle(int position) {
			// ָ�� ָʾ���ı���
			String title = mTabData.get(position).title;

			return title;
		}

		@Override
		public int getCount() {

			return mTabDetailPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPager pager = mTabDetailPagers.get(position);
			View view = pager.mRootView;
			container.addView(view);
			// �ڴˣ���pager�����ݳ�ʼ�������ҷ��ز����ļ�
			pager.initData();
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

		}

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		System.out.println("��ǰλ��" + position);
		if (position == 0) {
			// ���������
			setSlidingMenuEnable(true);
		} else {
			// ���ò����
			setSlidingMenuEnable(false);

		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	/**
	 * �������߽��ò����
	 * 
	 * @param b
	 */

	public void setSlidingMenuEnable(boolean enable) {
		// ��ȡ�����
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();

		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	@OnClick(R.id.bt_next)
	public void nextPage(View v) {
		// ������һ��ҳ��
		int currentItem = mViewPager.getCurrentItem();
		currentItem++;
		mViewPager.setCurrentItem(currentItem);

	}
}
