package com.example.zhbj.base.impl.menu;

import java.util.ArrayList;

import com.example.zhbj.Global.GlobalContent;
import com.example.zhbj.R;
import com.example.zhbj.base.BaseMenuDetailPager;
import com.example.zhbj.domain.NewsMenu;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.text.TextUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import utils.CacheUtils;
import zhbj.base.BaseMenuDetailPager;
import zhbj.domain.NewsMenu.NewsTabData;
import zhbj.domain.NewsTabBean;
import zhbj.domain.NewsTabBean.NewsData;
import zhbj.domain.NewsTabBean.TopNews;
import zhbj.global.GlobalConstant;
import zhbj.utils.CacheUtils;
import zhbj.view.TopNewsViewPager;

/**
 * ҳǩ����ҳ��
 * 
 * @author Administrator
 * 
 */
public class TabDetailPager extends BaseMenuDetailPager {
	private BitmapUtils mBitmapUtils;
	private NewsMenu.NewsTabData mTabData;

	@ViewInject(R.id.)
	private TopNewsViewPager mViewpager;

	@ViewInject(R.id.tv_title)
	private TextView tv_title;

	@ViewInject(R.id.indicator)
	private CirclePageIndicator mIndicator;

	@ViewInject(R.id.lv_list)
	private ListView lvList;
	private ArrayList<TopNews> mTopnews;
	private String mUrl;
	private NewsAdapter mNewsAdapter;
	private ArrayList<NewsData> mNewsList;

	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
		mUrl = GlobalContent.SERVER_URL + mTabData.url;
	}

	// ��д���෽��
	@Override
	public View initView() {
		// view = new TextView(mActivity);
		//
		// // �˴���ָ�룬��Ϊ�ڳ�ʼ��mtabdata֮ǰ��super�Ѿ�����initview
		// // view.setText(mTabData.title);
		//
		// view.setTextColor(Color.RED);
		// view.setTextSize(22);
		// view.setGravity(Gravity.CENTER);
		//
		// return view;

		View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
		ViewUtils.inject(this, view);


		View mHeaderView = View.inflate(mActivity, R.layout.list_item_header,
				null);

		ViewUtils.inject(this, mHeaderView);
		lvList.addHeaderView(mHeaderView);
		return view;
	}

	@Override
	public void initData() {

		// view.setText(mTabData.title);
		String cache = CacheUtils.getCache(mUrl, mActivity);
		// ���������ڣ����û���
		if (!TextUtils.isEmpty(cache)) {
			ProcessData(cache);
		}
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				ProcessData(result);


				CacheUtils.setCache(mUrl, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// ����ʧ��

				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void ProcessData(String result) {
		Gson gson = new Gson();
		NewsTabBean newsTabBean = gson.fromJson(result, NewsTabBean.class);

		mTopnews = newsTabBean.data.topnews;
		if (mTopnews != null) {
			mViewpager.setAdapter(new TopNewsAdapter());
			mIndicator.setViewPager(mViewpager);
			mIndicator.setSnap(true);

			// �¼�Ҫ���ø�inDicator
			mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

				@Override
				public void onPageSelected(int position) {
					// ����ͷ�����ű���
					TopNews topNews = mTopnews.get(position);
					tv_title.setText(topNews.title);
				}

				@Override
				public void onPageScrolled(int position, float positionOffset,
						int positionOffsetPixels) {

				}

				@Override
				public void onPageScrollStateChanged(int state) {

				}
			});
			tv_title.setText(mTopnews.get(0).title);
			// Ĭ���õ�һ��ѡ�У����ҳ���������³�ʼ����indicatror ��Ȼ������
			mIndicator.onPageSelected(0);
		}
		mNewsList = newsTabBean.data.news;
		if (mNewsList != null) {
			mNewsAdapter = new NewsAdapter();
			lvList.setAdapter(mNewsAdapter);
		}

	}

	private class NewsAdapter extends BaseAdapter {

		private BitmapUtils mBitmapUtils;

		public NewsAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}

		@Override
		public int getCount() {

			return mNewsList.size();
		}

		@Override
		public Object getItem(int position) {

			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.list_item_news,
						null);

				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				holder.tvTitle = (TextView) convertView
						.findViewById(R.id.tv_title);
				holder.tvDate = (TextView) convertView
						.findViewById(R.id.tv_date);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			NewsData news = (NewsData) getItem(position);
			holder.tvTitle.setText(news.title);
			holder.tvDate.setText(news.pubdata);

			mBitmapUtils.display(holder.ivIcon, news.listimage);

			return convertView;
		}

	}

	static class ViewHolder {
		public ImageView ivIcon;
		public TextView tvTitle;
		public TextView tvDate;
	}

	/**
	 * ͷ������ ����������
	 * 
	 * @author Administrator
	 * 
	 */
	class TopNewsAdapter extends PagerAdapter {

		public TopNewsAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			mBitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);// ���ü����е�Ĭ��ͼƬ
		}

		@Override
		public int getCount() {

			return mTopnews.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {

			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView view = new ImageView(mActivity);
			// view.setImageResource(R.drawable.topnews_item_default);
			view.setScaleType(ScaleType.FIT_XY);// ����ͼƬ���ŷ�ʽ�������丸�ؼ�

			String imageUrl = mTopnews.get(position).topimage;// ͼƬ��������
			System.out.println(imageUrl);
			// ����ͼƬ������ͼƬ���ø�imageView �����ڴ������������
			// BitmapUtils-XUtils
			mBitmapUtils.display(view, imageUrl);

			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView((View) object);
		}

	}
}
