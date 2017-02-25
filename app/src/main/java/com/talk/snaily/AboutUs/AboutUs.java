package com.talk.snaily.AboutUs;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.talk.snaily.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WHITEWALKERS on 9/17/2016.
 */
public class AboutUs extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_us);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
//        Timer timer = new Timer();
//        MyTimer mt = new MyTimer();
    }
        // We schedule the timer task to run after 1000 ms and continue to run every 1000 ms.
//        timer.schedule(mt, 3000, 3000);
    }

//    public class DrawableGradient extends GradientDrawable {
//        DrawableGradient(int[] colors, int cornerRadius) {
//            super(GradientDrawable.Orientation.TOP_BOTTOM, colors);
//
//            try {
//                this.setShape(GradientDrawable.RECTANGLE);
//                this.setGradientType(GradientDrawable.LINEAR_GRADIENT);
//                this.setCornerRadius(cornerRadius);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public DrawableGradient SetTransparency(int transparencyPercent) {
//            this.setAlpha(255 - ((255 * transparencyPercent) / 100));
//
//            return this;
//        }
//    }
//        class MyTimer extends TimerTask {
//
//        public void run() {
//
//            //This runs in a background thread.
//            //We cannot call the UI from this thread, so we must call the main UI thread and pass a runnable
//            runOnUiThread(new Runnable() {
//
//                public void run() {
//                    Random rand = new Random();
//                    //The random generator creates values between [0,256) for use as RGB values used below to create a random color
//                    //We call the RelativeLayout object and we change the color.  The first parameter in argb() is the alpha.
//                    linearLayout.setBackgroundDrawable(new DrawableGradient(new int[]{0xff666666, 0xff111111, 0xffffffff}, 0).SetTransparency(10));
//                }
//            });
//
//        }
//    }
//}
