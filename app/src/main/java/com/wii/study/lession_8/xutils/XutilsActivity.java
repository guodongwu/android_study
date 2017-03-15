package com.wii.study.lession_8.xutils;

import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.TextView;

import com.wii.study.App;
import com.wii.study.R;
import com.wii.study.lession_8.xutils.model.ChildInfo;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_xutils)
public class XutilsActivity extends AppCompatActivity {

    @ViewInject(R.id.tvchild)
    TextView tv;
    public DbManager db = x.getDb(App.daoConfig);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils);
        x.view().inject(this);
    }


    public void add(){
        ArrayList<ChildInfo> childInfos = new ArrayList<>();
        childInfos.add(new ChildInfo("zhangsan"));
        childInfos.add(new ChildInfo("lisi"));
        childInfos.add(new ChildInfo("wangwu"));
        childInfos.add(new ChildInfo("zhaoliu"));
        childInfos.add(new ChildInfo("qianqi"));
        childInfos.add(new ChildInfo("sunba"));
        //db.save()方法不仅可以插入单个对象，还能插入集合
        try {
            db.save(childInfos);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
    /**
     * 单击事件
     * type默认View.OnClickListener.class，故此处可以简化不写，@Event(R.id.bt_main)
     */
    @Event(type = View.OnClickListener.class,value = R.id.button)
    private void testInjectOnClick(View v){
        Snackbar.make(v,"OnClickListener",Snackbar.LENGTH_SHORT).show();
        add();
    }
    /**
     * 长按事件
     */
    @Event(type = View.OnLongClickListener.class,value = R.id.button)
    private boolean testOnLongClickListener(View v){
        Snackbar.make(v,"testOnLongClickListener",Snackbar.LENGTH_SHORT).show();
        //添加查询条件进行查询
        List<ChildInfo> all = null;
        try {
            //all = db.selector(ChildInfo.class).where("id",">",2).and("id","<",4).findAll();
            all = db.selector(ChildInfo.class).findAll();
            tv.setText("");

        } catch (DbException e) {
            e.printStackTrace();
        }
        for(ChildInfo childInfo :all){
            tv.setText(tv.getText()+"|"+childInfo.getcName());
            Log.i("JAVA",childInfo.toString());
        }
        return true;
    }

}
