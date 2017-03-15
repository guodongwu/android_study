package com.wii.study.lession_5.light;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wii.study.R;
import com.wii.study.lession_5.BaseActivity;

public class WarningActivity extends BaseActivity implements Runnable{

    private ImageView iv1;
    private ImageView iv2;
    private boolean isFlash;
    private boolean index;
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        initView();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (index) {
                iv1.setImageResource(R.drawable.warning_light_off);
                iv2.setImageResource(R.drawable.warning_light_on);
            } else {
                iv1.setImageResource(R.drawable.warning_light_on);
                iv2.setImageResource(R.drawable.warning_light_off);
            }
            index = !index;
        }
    };
    protected void initView() {

        iv1 = (ImageView) findViewById(R.id.iv_warninglight1);
        iv2 = (ImageView) findViewById(R.id.iv_warninglight2);
    }
    @Override
    public void run() {
        while (isFlash) {
            try {
                Thread.sleep(1000);
                if (handler != null)
                    handler.sendEmptyMessage(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        isFlash = true;
        setScreenBrightness(1f);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFlash = false;
    }
}
