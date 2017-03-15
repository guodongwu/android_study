package com.wii.study.lession_3;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wii.study.R;
import com.wii.study.lession_3.model.Person;
import com.wii.study.tools.SQLiteUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wu on 2017/3/7.
 */

public class ResultActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private DbAdapter adapter;
    SQLiteUtil sqLiteUtil;
    List<Person> bookList = new ArrayList<Person>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_dbms);
        //获取ListView
        listView = (ListView)findViewById(R.id.listView1);
        bookList = queryData();
        //实例化DbAdapter
        adapter = new DbAdapter(this, bookList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    //查询数据库，将每一行的数据封装成一个person 对象，然后将对象添加到List中
    private List<Person> queryData(){
        List<Person> list = new ArrayList<Person>();
        sqLiteUtil = new SQLiteUtil(this);

        //调用query()获取Cursor
        Cursor c = sqLiteUtil.query();
        while (c.moveToNext()){
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String sex = c.getString(c.getColumnIndex("sex"));
            String number = c.getString(c.getColumnIndex("number"));
            String desc = c.getString(c.getColumnIndex("desc"));

            //用一个Person对象来封装查询出来的数据
            Person p = new Person();
            p.set_id(_id);
            p.setName(name);
            p.setSex(sex);
            p.setNumber(number);
            p.setDesc(desc);

            list.add(p);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Person p = bookList.get(position);
        final long temp = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("真的要删除该记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //调用delete（）删除某条数据
                sqLiteUtil.delete(p.get_id());
                //重新刷新适配器
                adapter.refresh(queryData());
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();


        // 关闭数据库
        sqLiteUtil.close();
    }

    //自定义DbAdapter
    public class DbAdapter extends BaseAdapter {
        private List<Person> list;
        private Context context;
        private LayoutInflater layoutInflater;

        public DbAdapter(Context context, List<Person> list){
            Log.e("T:", context.getPackageName());
            layoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.list = list;
        }

        //刷新适配器
        public void refresh(List<Person> list){
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person p = list.get(position);
            ViewHolder holder;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.item_dbms, null);
                holder.name = (TextView)convertView.findViewById(R.id.tv1);
                holder.sex = (TextView)convertView.findViewById(R.id.tv2);
                holder.number = (TextView)convertView.findViewById(R.id.tv3);
                holder.desc = (TextView)convertView.findViewById(R.id.tv4);

                convertView.setTag(holder);

            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(p.getName());
            holder.sex.setText(p.getSex());
            holder.number.setText(p.getNumber());
            holder.desc.setText(p.getDesc());

            return convertView;
        }


        public class ViewHolder {
            public TextView name;
            public TextView sex;
            public TextView number;
            public TextView desc;
            public TextView id;
        }





    }
}