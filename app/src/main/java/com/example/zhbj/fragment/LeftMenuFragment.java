package com.example.zhbj.fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhbj.MainActivity;
import com.example.zhbj.R;
import com.example.zhbj.domain.NewsMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


public class LeftMenuFragment extends BaseFragment {

    @ViewInject(R.id.lv_list)
    private ListView lvList;
    private PagerAdapter mAdapter;
    private int mCurrentPos;
    private ArrayList<NewsMenu.NewsMenuData> mNewsMenuData;

    //    @BindView()
    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        ViewUtils.inject(this, view);

//        lv_list = view.findViewById(R.id.lv_list);
        return view;
    }

    @Override
    public void initData() {

    }

    public void setMenuData(ArrayList<NewsMenu.NewsMenuData> data) {
        mCurrentPos = 0;
        mNewsMenuData = data;
        lvList.setAdapter(new LeftMenuAdapter());

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mCurrentPos = position;// ���µ�ǰ��ѡ�е�λ��
                mAdapter.notifyDataSetChanged();// ˢ��ListView

                // ��������..
                toggle();

                // ��������֮��Ҫ�޸��������ĵ�framlayout����
                setCurrentDetailPager(position);
            }

        });

    }


    class LeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mNewsMenuData.size();
        }

        @Override
        public NewsMenu.NewsMenuData getItem(int position) {
            return mNewsMenuData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.list_item_left_menu, null);
            TextView tv_menu = view.findViewById(R.id.tv_menu);

            if (position == mCurrentPos) {
                tv_menu.setEnabled(true);
            } else {
                tv_menu.setEnabled(false);
            }
            NewsMenu.NewsMenuData item = getItem(position);
            tv_menu.setText(item.title);
            return view;
        }
    }
    protected void setCurrentDetailPager(int position) {

        // ��ȡ�������ĵĶ���
        MainActivity mainUi = (MainActivity) mActivity;

        // ��ȡcontentFragment
        ContentFragment contentFragment = mainUi.getContentFragment();
        // ��ȡcontentPager
        NewsCenterPager newscenterpager = contentFragment.getNewsCenterPager();

        // ��ȡ�������ĵ�framlayout�Ĳ���

        newscenterpager.setCurrentDetailPager(position);

    }

    /**
     * �� ���߹رղ����
     */
    protected void toggle() {
        MainActivity mainUI = (MainActivity) mactivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// �����ǰ״̬�ǿ������ú�͹أ���֮��Ȼ

    }
}
