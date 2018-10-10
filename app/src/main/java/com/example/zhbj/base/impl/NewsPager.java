package com.example.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhbj.Global.GlobalContent;
import com.example.zhbj.MainActivity;
import com.example.zhbj.base.BaseMenuDetailPager;
import com.example.zhbj.base.BasePager;
import com.example.zhbj.domain.NewsMenu;
import com.example.zhbj.fragment.LeftMenuFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import utils.CacheUtils;

public class NewsPager extends BasePager {


    public NewsPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initData() {
        System.out.println();
        //fill out the framelayout
        System.out.println("新闻中心初始化了");
        TextView view = new TextView(mActivity);
        view.setText("新闻中心");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
        tvTitle.setText("新闻中心");
        btnMenu.setVisibility(View.VISIBLE);

        String cache = CacheUtils.getCache(GlobalContent.CATEGORY_URL, mActivity);
        if (!TextUtils.isEmpty(cache)) {
            System.out.println("发现缓存");
            getDataFromServer();
        } else {
            //request from server to get data
            getDataFromServer();
        }


    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, GlobalContent.CATEGORY_URL, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                //get result from virtual tomcat server
                String result = responseInfo.result;
                System.out.println("服务器返回结果 " + result);

                //transform to json
                processData(result);

                //write the cache
                CacheUtils.setCache(GlobalContent.CATEGORY_URL, result, mActivity);

            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void processData(String json) {
        //google json
        Gson gson = new Gson();
        NewsMenu data = gson.fromJson(json, NewsMenu.class);
        MainActivity mActivity = (MainActivity) this.mActivity;
        LeftMenuFragment leftFragment = mActivity.getLeftMenuFragment();
        leftFragment.setMenuData(data.data);


        mPagers = new ArrayList<BaseMenuDetailPager>();

        mPagers.add(new NewsMenuDetailPager(mActivity, mNewsData.data
                .get(0).children));
        mPagers.add(new TopicMenuDetailPager(mActivity));

        mPagers.add(new PhotoMenuDetailPager(mActivity));
        mPagers.add(new InteractMenuDetailPager(mActivity));

        // �����Ų˵�����ҳ����ΪĬ��ҳ��(��һ�μ��ص�ҳ��ʱ��ҳ�������һ��)
        setCurrentDetailPager(0);

    }


    public void setCurrentDetailPager(int position) {
        // ���¸�fragmentLayout�������
        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
        View view = pager.mRootView;


        flContent.removeAllViews();

        flContent.addView(view);


        pager.initData();


        String title = mNewsData.data.get(position).title;
        tvTitle.setText(title);
    }

}
