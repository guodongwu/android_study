package com.wii.study.lession_2;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wii.study.R;
import com.wii.study.lession_2.adapter.CommonAdapter;
import com.wii.study.lession_2.adapter.ViewHolder;
import com.wii.study.lession_2.model.Joke;
import com.wii.study.tools.HttpUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * API调用
 *
 *
 * **/

public class JokeActivity extends AppCompatActivity {

    GridView gvshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        gvshow=(GridView)findViewById(R.id.gvshow);
        new Thread(networkTask).start();

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("result");
            Log.i("mylog", "请求结果为-->" + result);
            // TODO
            // UI界面的更新等相关操作
                if(result!=""){
                    Joke joke=new Joke();
                    Gson g=new Gson();
                    Type lt=new TypeToken<List<Joke>>(){}.getType();
                    List<Joke> jokeList=g.fromJson(result,lt);
                    gvshow.setAdapter(new CommonAdapter<Joke>(JokeActivity.this,jokeList, R.layout.item_joke) {
                        @Override
                        protected void fillData(ViewHolder holder, Joke joke) {
                            holder.setText(R.id.tvTitle,joke.getTitle()).setHtml(R.id.tvContent,joke.getContent());
                            if(joke.getPoster()!=null && joke.getPoster()!="")
                                holder.setImageByUrl(JokeActivity.this,R.id.ivPic,joke.getPoster());

                        }
                    });
                }


        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作

            Bundle data = new Bundle();
            Message msg = new Message();
            String result=HttpUtil.httpGet("http://api.laifudao.com/open/xiaohua.json");
            data.putString("result", result);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

}
