package com.wii.study.lession_4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.wii.study.R;

/**
*
 * 混合开发初步涉及 js调用
*
* */
public class WebHtmlActivity extends AppCompatActivity  {
    private WebView wv;

    @SuppressLint({"JavascriptInterface","SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_html);
        wv = (WebView) this.findViewById(R.id.wv1);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient() {});
        wv.addJavascriptInterface(new Account(), "register_js"); //脚本一定要在Load前
        wv.loadUrl("file:///android_asset/index.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在这里执行你想调用的js函数
                String call = "javascript:sayHello()";
                wv.loadUrl(call);
            }

        });

    }

    public  class  Account {
        @JavascriptInterface
        public void register(String userInfo) {
            Toast.makeText(WebHtmlActivity.this, "已经执行", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(WebHtmlActivity.this,RegisterActivity.class);
            intent.putExtra("userinfo", userInfo);
            startActivity(intent);
        }

    }
}
