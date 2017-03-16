package com.wii.study.lession_10;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.wii.study.R;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button2;
    private  Button button3;
    private EditText editText;
    private EditText editText2;
    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        button = (Button)findViewById(R.id.btnDate);
        button2 = (Button)findViewById(R.id.btnTime);
        button3 = (Button)findViewById(R.id.details_date);

        editText = (EditText)findViewById(R.id.etDate);
        editText2 = (EditText)findViewById(R.id.etTime);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);


    }


    @Override
    public void onClick(final View v) {
        final int vid=v.getId();
        switch (v.getId()){
            case R.id.btnDate:
            case R.id.details_date:
                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth)
                    {
                        if(vid==R.id.details_date)
                            button3.setText("日期：" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        else
                            editText.setText("日期：" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, monthOfYear, dayOfMonth);
                datePickerDialog.show();
                break;
            case R.id.btnTime:
                /**
                 * 实例化一个TimePickerDialog的对象
                 * 第二个参数是一个TimePickerDialog.OnTimeSetListener匿名内部类，当用户选择好时间后点击done会调用里面的onTimeset方法
                 */
                TimePickerDialog timePickerDialog = new TimePickerDialog(DateActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        editText2.setText("Time: " + hourOfDay + ":" + minute);
                    }
                }, hourOfDay, minute, true);

                timePickerDialog.show();

                break;
        }
    }
}
