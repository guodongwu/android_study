package com.wii.study.lession_3;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wii.study.R;
import com.wii.study.tools.SQLiteUtil;

public class DBMSActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteUtil sqLiteUtil;
    private EditText name,number,desc;
    private Button submit,look;
    private RadioGroup radioGroup;
    private String nameStr, numberStr, descStr;
    private String sexStr = "男";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbms);
        sqLiteUtil = new SQLiteUtil(this);
        name=(EditText)findViewById(R.id.etName);
        number=(EditText)findViewById(R.id.etPhone);
        desc=(EditText)findViewById(R.id.etDesc);
        submit = (Button)findViewById(R.id.btnSubmit);
        look = (Button)findViewById(R.id.btnLook);
        radioGroup = (RadioGroup)findViewById(R.id.rgSex);
        submit.setOnClickListener(this);
        look.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio0){
                    RadioButton r = (RadioButton) group.findViewById(checkedId);
                    sexStr = r.getText().toString();
                }
                if(checkedId == R.id.radio1){
                    RadioButton r = (RadioButton) group.findViewById(checkedId);
                    sexStr = r.getText().toString();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                if(name.getText().toString().length() != 0){
                    nameStr = name.getText().toString();
                }else{
                    Toast.makeText(getApplication(), "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(number.getText().toString().length() != 0){
                    numberStr = number.getText().toString();
                }else{
                    Toast.makeText(getApplication(), "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(desc.getText().toString().length() != 0){
                    descStr = desc.getText().toString();
                }else{
                    Toast.makeText(getApplication(), "备注不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //实例化一个ContentValues， ContentValues是以键值对的形式，键是数据库的列名，值是要插入的值
                ContentValues values = new ContentValues();
                values.put("name", nameStr);
                values.put("sex", sexStr);
                values.put("number", numberStr);
                values.put("desc", descStr);
                sqLiteUtil.insert(values);
                //将三个输入框重置下
                reset();
                break;
            case R.id.btnLook:
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setClass(this, ResultActivity.class);
                startActivity(intent);
                break;

        }
    }
    //重置edittext
    private void reset(){
        name.setText("");
        number.setText("");
        desc.setText("");
    }
}
