package com.wii.study.lession_5.light;

import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wii.study.R;
import com.wii.study.lession_5.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class MorseActivity extends BaseActivity implements View.OnClickListener,
        Runnable {
    private static int TIME = 300;
    private static int DOT_TIME = TIME;
    private static int LINE_TIME = TIME * 3;
    private static int DOT_LINE_TIME = TIME;
    private static int CHAR_TIME = TIME * 3;
    private static int WORD_TIME = TIME * 7;

    private static final int MORSE_SUCCESS = 0;
    private static final int MORSE_NOT_EMPTY = 1;
    private static final int MORSE_NOT_VERIFY = 2;

    private Map<Character, String> morseCodeMap = new HashMap<Character, String>();

    private ImageView iv;
    private EditText et;

    private boolean isClick;
    private Thread thread;

    private String morseCodeStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);
        // 初始化摩尔斯密码
        initMorseCode();

        // 初始化界面
        initView();
    }
    protected void initView() {
        iv = (ImageView) findViewById(R.id.iv_morse);
        et = (EditText) findViewById(R.id.edit_morse);

        iv.setOnClickListener(this);
    }
    // 初始化摩尔斯密码
    private void initMorseCode() {
        morseCodeMap.put('a', ".-");
        morseCodeMap.put('b', "-...");
        morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-..");
        morseCodeMap.put('e', ".");
        morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--.");
        morseCodeMap.put('h', "....");
        morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---");
        morseCodeMap.put('k', "-.-");
        morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--");
        morseCodeMap.put('n', "-.");
        morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--.");
        morseCodeMap.put('q', "--.-");
        morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "...");
        morseCodeMap.put('t', "-");
        morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-");
        morseCodeMap.put('w', ".--");
        morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--");
        morseCodeMap.put('z', "--..");

        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MORSE_SUCCESS:
                    Toast.makeText(MorseActivity.this, "摩尔斯密码已经发送完成!",
                            Toast.LENGTH_LONG).show();
                    break;
                case MORSE_NOT_EMPTY:
                    Toast.makeText(MorseActivity.this, "请输入摩尔斯密码!",
                            Toast.LENGTH_LONG).show();
                    break;
                case MORSE_NOT_VERIFY:
                    Toast.makeText(MorseActivity.this, "摩尔斯密码只能是数字或字母!",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_morse) {
            if (!getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                Toast.makeText(MorseActivity.this, "当前设备没有闪光灯",
                        Toast.LENGTH_LONG).show();
                return;
            }
            if (!isClick) {
                isClick = true;
                thread = new Thread(this);
                thread.start();
            }
        }
    }
    // 发送点
    private void sendDot() throws Exception {
        openFlashLight();
        sleepTime(DOT_TIME);
        closeFlashLight();
    }

    // 发送线
    private void sendLine() throws Exception {
        openFlashLight();
        sleepTime(LINE_TIME);
        closeFlashLight();
    }

    // 发送字符
    private void sendChar(char c) throws Exception {
        String morseCode = morseCodeMap.get(c);
        if (morseCode != null) {
            char lastChar = ' ';
            for (int i = 0; i < morseCode.length(); i++) {
                char code = morseCode.charAt(i);
                if (code == '.') {
                    sendDot();
                } else if (code == '-') {
                    sendLine();
                }
                if (i > 0 && i < morseCode.length() - 1) {
                    if (lastChar == '.' && code == '-') {
                        sleepTime(DOT_LINE_TIME);
                    }
                }
                lastChar = code;
            }
        }
    }

    // 发送单词
    private void sendWord(String word) throws Exception {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            sendChar(c);
            if (i < word.length() - 1) {
                sleepTime(CHAR_TIME);
            }
        }
    }

    // 发送句子
    private void sendSentense(String s) throws Exception {
        String[] words = s.split(" +");
        for (int i = 0; i < words.length; i++) {
            sendWord(words[i]);
            if (i < words.length - 1) {
                sleepTime(WORD_TIME);
            }
        }
        handler.sendEmptyMessage(MORSE_SUCCESS);
        isClick = false;
    }

    // 验证方法,验证文本输入框的信息
    private boolean verifyMorseCode() {
        morseCodeStr = et.getText().toString().toLowerCase();
        if ("".equals(morseCodeStr)) {
            handler.sendEmptyMessage(MORSE_NOT_EMPTY);
            return false;
        }
        for (int i = 0; i < morseCodeStr.length(); i++) {
            char c = morseCodeStr.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9') && c != ' ') {
                handler.sendEmptyMessage(MORSE_NOT_VERIFY);
                return false;
            }
        }
        return true;
    }

    // 休眠时间
    private void sleepTime(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (thread != null) {
                thread.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread = null;
        closeFlashLight();
    }

    @Override
    public void run() {
        if (verifyMorseCode()) {
            try {
                sendSentense(morseCodeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
