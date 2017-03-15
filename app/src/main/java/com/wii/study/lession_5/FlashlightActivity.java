package com.wii.study.lession_5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wii.study.R;
import com.wii.study.lession_5.light.BulbActivity;
import com.wii.study.lession_5.light.FlashActivity;
import com.wii.study.lession_5.light.MorseActivity;
import com.wii.study.lession_5.light.WarningActivity;
/**
 * 手机闪光灯调用
 * **/
public class FlashlightActivity extends AppCompatActivity implements  View.OnClickListener {

    private ImageView iv_flashlight;
    private ImageView iv_morse;
    private ImageView iv_bulb;
    private ImageView iv_warninglight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);
        initView();
    }
    private void initView() {
        iv_flashlight = (ImageView) findViewById(R.id.icon_flashlight);
        iv_warninglight = (ImageView) findViewById(R.id.icon_warninglight);
        iv_morse = (ImageView) findViewById(R.id.icon_morse);
        iv_bulb = (ImageView) findViewById(R.id.icon_bulb);

        iv_flashlight.setOnClickListener(this);
        iv_warninglight.setOnClickListener(this);
        iv_morse.setOnClickListener(this);
        iv_bulb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.icon_flashlight:
                intent=new Intent(FlashlightActivity.this,FlashActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_warninglight:
                intent=new Intent(FlashlightActivity.this,WarningActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_morse:
                intent=new Intent(FlashlightActivity.this,MorseActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_bulb:
                intent=new Intent(FlashlightActivity.this,BulbActivity.class);
                startActivity(intent);
                break;

        }


    }
}
