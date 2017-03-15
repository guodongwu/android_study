package com.wii.study.lession_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wii.study.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private TextView username, password, interest, country, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.username = (TextView) this.findViewById(R.id.username);
        this.password = (TextView) this.findViewById(R.id.password);
        this.interest = (TextView) this.findViewById(R.id.interest);
        this.country = (TextView) this.findViewById(R.id.country);
        this.gender = (TextView) this.findViewById(R.id.gender);
        String userinfo = this.getIntent().getExtras().getString("userinfo");
        try {
            JSONObject json = new JSONObject(userinfo);
            username.setText(json.getString("username"));
            password.setText(json.getString("password"));
            interest.setText(json.getString("interest").replace("0", "足球")
                    .replace("1", "篮球").replace("2", "排球"));
            country.setText(json.getString("country").replace("0", "中国")
                    .replace("1", "美国").replace("2", "小日本"));
            gender.setText(json.getString("gender").replace("0", "男")
                    .replace("1", "女"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
