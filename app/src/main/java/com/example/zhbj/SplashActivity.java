package com.example.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import utils.PrefUtils;

import static android.view.animation.Animation.AnimationListener;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Splashing page
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        //rotate anim
        RotateAnimation rotate = new RotateAnimation(0, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        //time
        rotate.setDuration(1000);
        rotate.setFillAfter(true);

        //scale anim
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);
        scale.setFillAfter(true);

        //continue
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        scale.setDuration(2000);
        alpha.setFillAfter(true);

        //anim ste
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        //start anim set
        rlRoot.startAnimation(set);

        set.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // judge if its first time enter
            @Override
            public void onAnimationEnd(Animation animation) {
//                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
//                booleanlean isFirst = sp.getBoolean("is_first", true);
                Intent intent;
                boolean isFirst = PrefUtils.getBoolean(SplashActivity.this, "is_first", true);
                if (isFirst) {
                    //guide page

                    intent = new Intent(getApplicationContext(), GuideActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), MainActivity.class);

                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
