package com.wii.study.lession_5.light;

import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wii.study.R;
import com.wii.study.lession_5.BaseActivity;

public class BulbActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private Handler hanlder = new Handler() {
        public void handleMessage(android.os.Message msg) {
            tv.setVisibility(View.INVISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulb);
        initView();
        new Thread() {
            public void run() {
                try {
                    sleep(2500);
                    hanlder.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();
    }

    protected void initView() {
        iv = (ImageView) findViewById(R.id.iv_bulb);
        iv.setOnClickListener(this);

        iv.setTag(false);

        tv = (TextView) findViewById(R.id.tv_bulb);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_bulb) {
            TransitionDrawable drawable = (TransitionDrawable) iv.getDrawable();
            if ((Boolean) iv.getTag()) {
                drawable.reverseTransition(500);
                setScreenBrightness(mDefaultBrightness / 255f);
                iv.setTag(false);
            } else {
                drawable.startTransition(500);
                setScreenBrightness(1f);
                iv.setTag(true);
            }
        }
    }
}
