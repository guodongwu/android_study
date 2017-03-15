package com.wii.study;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wii.study.lession_1.TimerActivity;
import com.wii.study.lession_2.JokeActivity;
import com.wii.study.lession_3.DBMSActivity;
import com.wii.study.lession_4.WebHtmlActivity;
import com.wii.study.lession_5.FlashlightActivity;
import com.wii.study.lession_6.LoginActivity;
import com.wii.study.lession_6.PopupActivity;
import com.wii.study.lession_7.CustomizeMenu;
import com.wii.study.lession_7.DefaultMenu;
import com.wii.study.lession_7.MenuActivity;
import com.wii.study.lession_8.xutils.XutilsActivity;
import com.wii.study.lession_9.PagerActivity;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 入口
 * **/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout llv=(LinearLayout)findViewById(R.id.llv);
        int l=1;
        for(int i=0;i<10;i++){
            LinearLayout llh=new LinearLayout(this);
            llh.setOrientation(LinearLayout.HORIZONTAL);
            llh.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            for (int j=0;j<5;j++){
                Button btn=new Button(this);
                btn.setId(generateViewId());
                btn.setText("L-"+String.format("%02d", l));
                btn.setOnClickListener(this);
                l++;
                llh.addView(btn);
            }
            llv.addView(llh);
        }

    }
    /**
     * 动态生成View ID
     * API LEVEL 17 以上View.generateViewId()生成
     * API LEVEL 17 以下需要手动生成
     */
    public static int generateViewId() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case 1:
               intent=new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(MainActivity.this, JokeActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(MainActivity.this, DBMSActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent=new Intent(MainActivity.this, WebHtmlActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent=new Intent(MainActivity.this, FlashlightActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent=new Intent(MainActivity.this, PopupActivity.class);
               // intent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                break;
            case 7 :
                //intent=new Intent(MainActivity.this, MenuActivity.class);
                intent=new Intent(MainActivity.this, CustomizeMenu.class);
                startActivity(intent);
                break;
            case 8:
                intent=new Intent(MainActivity.this, XutilsActivity.class);
                startActivity(intent);
            case 9:
                intent=new Intent(MainActivity.this, PagerActivity.class);
                startActivity(intent);
                break;

        }

        //Toast.makeText(this,"您点击了Button"+v.getId(),Toast.LENGTH_SHORT).show();
    }
}
