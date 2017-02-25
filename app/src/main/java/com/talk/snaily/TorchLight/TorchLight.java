package com.talk.snaily.TorchLight;

import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.talk.snaily.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WHITEWALKERS on 9/16/2016.
 */
public class TorchLight extends AppCompatActivity {

    //flag to detect flash is on or off
    private boolean isLighOn = false;
    LinearLayout linearLayout;


    private Camera camera;

    private ImageView buttonFlash , logoBatman , buttonFlashon;

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.torch_light);

        buttonFlash = (ImageView) findViewById(R.id.buttonFlashlight);
        logoBatman = (ImageView) findViewById(R.id.afterFlashlight);
        buttonFlashon = (ImageView) findViewById(R.id.buttonFlashlighton);


        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        logoBatman.setVisibility(View.INVISIBLE);
        buttonFlashon.setVisibility(View.INVISIBLE);

//        Timer timer = new Timer();
//        MyTimer mt = new MyTimer();

        //We schedule the timer task to run after 1000 ms and continue to run every 1000 ms.
//        timer.schedule(mt, 3000, 3000);

        camera = Camera.open();
        final Camera.Parameters p = camera.getParameters();


        buttonFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLighOn) {

                    Snackbar.make(v, "Light is Off", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(p);
                    camera.stopPreview();
                    isLighOn = false;
                    logoBatman.setVisibility(View.INVISIBLE);
                    buttonFlashon.setVisibility(View.INVISIBLE);



                } else {
                    Snackbar.make(v, "Light is On", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();

                    logoBatman.setVisibility(View.VISIBLE);
                    buttonFlashon.setVisibility(View.VISIBLE);

                    isLighOn = true;

                }
            }
        });

    }
//    class MyTimer extends TimerTask {
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
//                    linearLayout.setBackgroundColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
//                }
//            });
//
//        }
//    }
}
