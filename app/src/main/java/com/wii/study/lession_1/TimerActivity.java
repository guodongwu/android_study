package com.wii.study.lession_1;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wii.study.R;
import com.wii.study.tools.ColorUtil;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("");
        setContentView(R.layout.activity_timer);


        //(一)3.在需要启动线程的地方加入下面语句：
        new Thread(new MyThread()).start();
        //（二）
        handler.postDelayed(runnable, 2000);
        //handler.removeCallbacks(runnable);
        //（三）
        timer.schedule(task, 1000, 3000);
    }

    //(一)1. 定义一个Handler类，用于处理接受到的Message。
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            // 要做的事情
            Bundle b = msg.getData();
            String color = b.getString("color");
            Log.i("Color",color);
            switch(msg.what) {
                case 1 :
                   RelativeLayout rl= (RelativeLayout)findViewById(R.id.activity_timer);
                   rl.setBackgroundColor(Color.parseColor(color));
                case 2:
                    Button btn=(Button)findViewById(R.id.btnColor);
                    btn.setBackgroundColor(Color.parseColor(color));

            }
            super.handleMessage(msg);
        }
    };
    //(一)2. 新建一个实现Runnable接口的线程类，如下：
    public class MyThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                try {
                    Thread.sleep(5000);// 线程暂停10秒，单位毫秒
                    Message message = new Message();
                    message.what = 1;
                    Bundle b = new Bundle();// 存放数据
                    b.putString("color", "#"+ColorUtil.getRandColorCode());
                    message.setData(b);
                    handler.sendMessage(message);// 发送消息
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //（二） timer
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            TextView tv= (TextView)findViewById(R.id.tvColor);
            tv.setTextColor(Color.parseColor("#"+ColorUtil.getRandColorCode()));
            handler.postDelayed(this, 2000);
        }
    };
    private final Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Message message = new Message();
            message.what = 2;
            Bundle b = new Bundle();// 存放数据
            b.putString("color", "#"+ColorUtil.getRandColorCode());
            message.setData(b);
            handler.sendMessage(message);
        }
    };
}

