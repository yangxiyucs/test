package com.example.zhbj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.zhbj.fragment.ContentFragment;
import com.example.zhbj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {


    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //remote the title|| call it before contentview
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_left);

        SlidingMenu slidingMenu = getSlidingMenu();
//        slidingMenu.setMode(slidingMenu.LEFT_RIGHT);
//        slidingMenu.setSecondaryMenu(R.layout.menu_right);
        slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(200);


        initFragment();

    }


    //init fragment
    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
        transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), TAG_LEFT_MENU);

        //commit the transaction
        transaction.commit();


//        Fragment fragmentByTag = fm.findFragmentByTag(TAG_LEFT_MENU);
    }

    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment leftMenu = fm.findFragmentByTag(TAG_LEFT_MENU);
        return (LeftMenuFragment) leftMenu;

    }

    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment leftMenu = fm.findFragmentByTag(TAG_CONTENT);
        return (ContentFragment) leftMenu;
    }
}
