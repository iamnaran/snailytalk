package com.talk.snaily;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Administrator on 5/09/2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        imageView = (ImageView) findViewById(R.id.splash_image);

        imageView.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.rotate) );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        },1000);


    }
}
