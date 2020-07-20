package com.example.soccertournamethelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

public class LauncherActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private int progress;
    private ImageView imageView_trophy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //hide titleBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide actionbar
        Objects.requireNonNull(getSupportActionBar()).hide();

        textView = findViewById(R.id.textId);
        progressBar = findViewById(R.id.launcherProgressBarId);
        imageView_trophy = findViewById(R.id.imv_id);

//      creating a Thread to set progress
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setAnimation();
                threadMethod();
                intentMethod();
            }

        });
        thread.start();


    }

    private void setAnimation() {
        //set Animation to TextView
        Animation textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_animation_style);
        Animation imgAnimation = AnimationUtils.loadAnimation(this, R.anim.img_anim_style);
        imageView_trophy.startAnimation(imgAnimation);
        textView.startAnimation(textAnimation);
    }

    private void intentMethod() {
        Intent intentMainActivity = new Intent(this, SignINActivity.class);
        startActivity(intentMainActivity);
        finish();
    }

    private void threadMethod() {
        for (int progress = 1; progress <= 100; progress++)
        {
            try {
                Thread.sleep(50);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
