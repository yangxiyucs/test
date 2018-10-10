package com.example.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import utils.PrefUtils;

/**
 * 新手引导
 */
public class GuideActivity extends Activity {
    private ViewPager vp_container;
    int[] ImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    ArrayList<ImageView> ImageViews;
    LinearLayout cc_container;
    ImageView redpoint;
    int pleft;
    Button vp_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call it before  set contentview
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        PrefUtils.setBoolean(getApplicationContext(), "is_first", false);


        vp_container = (ViewPager) findViewById(R.id.vp_guide);
        cc_container = (LinearLayout) findViewById(R.id.vp_container);
        redpoint = (ImageView) findViewById(R.id.red_point);

        vp_button = (Button) findViewById(R.id.vp_button);
        initData();

        vp_container.setAdapter(new GuideAdapter());
        vp_container.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println(position + "  " + positionOffset);
                int pdistnace = (int) (pleft * positionOffset) + position * pleft;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) redpoint.getLayoutParams();
                layoutParams.leftMargin = pdistnace;
                redpoint.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == ImageViews.size() - 1) {
                    vp_button.setVisibility(View.VISIBLE);
                } else {
                    vp_button.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //call back after layout finish
                redpoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                pleft = cc_container.getChildAt(1).getLeft() - cc_container.getChildAt(0).getLeft();

            }
        });
        vp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update sp
                PrefUtils.setBoolean(getApplicationContext(), "is_first", false);

                //jump to main page
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void initData() {
        ImageViews = new ArrayList<ImageView>();

        //init images
        for (int i = 0; i < ImageIds.length; i++) {

            ImageView view = new ImageView(this);
            view.setBackgroundResource(ImageIds[i]);
            ImageViews.add(view);

            //initialize cicles
            ImageView circles = new ImageView(this);
            circles.setImageResource(R.drawable.shape_circles);

            //init frame params(father is linear )
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            if (i > 0) {
                params.leftMargin = 10;
            }
            circles.setLayoutParams(params);
            cc_container.addView(circles);


        }
    }


    class GuideAdapter extends PagerAdapter {

        //no of items
        @Override
        public int getCount() {
            return ImageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //instance a obj
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = ImageViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}