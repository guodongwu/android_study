package com.wii.study.lession_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.wii.study.R;
/**
 * 菜单神马的 但是还是有很多不是很懂
 *
 * **/
public class MenuActivity extends AppCompatActivity {
    private TabHost tabHost;
    private RadioGroup radiogroup;
    private int menuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("main").setIndicator("main")
                .setContent(R.id.fragment_main));
        tabHost.addTab(tabHost.newTabSpec("mycenter").setIndicator("mycenter")
                .setContent(R.id.fragment_mycenter));
        tabHost.addTab(tabHost.newTabSpec("search").setIndicator("search")
                .setContent(R.id.fragment_search));
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                menuid=checkedId;
                int currentTab=tabHost.getCurrentTab();
                switch (checkedId){
                    case R.id.radio_main:
                        //tabHost.setCurrentTabByTag("main");
                        //如果需要动画效果就使用
                        setCurrentTabWithAnim(currentTab, 0, "main");
                        getSupportActionBar().setTitle("首页");
                        break;
                    case R.id.radio_mycenter:
                        //tabHost.setCurrentTabByTag("mycenter");
                        setCurrentTabWithAnim(currentTab, 1, "mycenter");
                        getSupportActionBar().setTitle("个人中心");

                        break;
                    case R.id.radio_search:
                        //tabHost.setCurrentTabByTag("search");
                        setCurrentTabWithAnim(currentTab, 2, "search");
                        getSupportActionBar().setTitle("搜索");
                        break;
                }
                // 刷新actionbar的menu
                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (menuid) {
            case R.id.radio_main:
                getMenuInflater().inflate(R.menu.main, menu);
                break;
            case R.id.radio_mycenter:
                menu.clear();
                break;
            case R.id.radio_search:
                menu.clear();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // 这个方法是关键，用来判断动画滑动的方向
    private void setCurrentTabWithAnim(int now, int next, String tag) {
        if (now > next) {
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            tabHost.setCurrentTabByTag(tag);
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
        } else {
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            tabHost.setCurrentTabByTag(tag);
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        }
    }

}
