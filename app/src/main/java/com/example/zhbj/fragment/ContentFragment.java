package com.example.zhbj.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.zhbj.MainActivity;
import com.example.zhbj.R;
import com.example.zhbj.base.BasePager;
import com.example.zhbj.base.impl.GovAffairsPager;
import com.example.zhbj.base.impl.HomePager;
import com.example.zhbj.base.impl.NewsPager;
import com.example.zhbj.base.impl.SettingPager;
import com.example.zhbj.base.impl.SmartServicePager;
import com.example.zhbj.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;


//main page content fragment
public class ContentFragment extends BaseFragment {

    NoScrollViewPager mViewPager;
    //add give tab pages
    ArrayList<BasePager> mPagers;
    RadioGroup rgGroup;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);

        rgGroup = view.findViewById(R.id.rg_group);
        mViewPager = view.findViewById(R.id.vp_content);
        return view;

    }

    @Override
    public void initData() {
        mPagers = new ArrayList<>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_main:
                        mViewPager.setCurrentItem(0, false);
                        //
                        break;
                    case R.id.rb_news:
                        //
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_gov:
                        //
                        mViewPager.setCurrentItem(3, false);
                        break;

                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(4, false);
                        break;

                    default:
                        break;


                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                BasePager pager = mPagers.get(i);
                pager.initData();

                if (i == 0 || i == mPagers.size() - 1) {
                    //forbidden the first and end page
                    setSlingMenuEnable(false);
                } else {
                    //unlock the full_screen
                    setSlingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //manually forbidden this function
        setSlingMenuEnable(false);
        //manually initData for first time
        mPagers.get(0).initData();
    }

    /*
    open or close slide menu
     */
    public void setSlingMenuEnable(boolean Enable) {
        //get slidingmenu obj
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        if (Enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

    }


    class ContentAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);

            //get the layout of current pager
            View view = pager.mRootView;
            //init data
            //dont initData here, because the waster of resource
//            pager.initData();

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


}