package com.wii.study.lession_5.light;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.wii.study.R;
import com.wii.study.lession_5.BaseActivity;

import java.io.IOException;

public class FlashActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv;
    private ImageView iv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        initView();
    }
    protected void initView() {
        iv = (ImageView) findViewById(R.id.iv_flashlight);
        iv_cover = (ImageView) findViewById(R.id.iv_flashlight_cover);

        iv_cover.setOnClickListener(this);
        iv_cover.setTag(false);

        /*Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        ViewGroup.LayoutParams lp = iv_cover.getLayoutParams();
        lp.height = point.y * 3 / 4;
        lp.width = point.x / 3;
        iv_cover.setLayoutParams(lp);
        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_flashlight_cover:
                clickFlashLightCover();
                break;
        }
    }
    private void clickFlashLightCover() {
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(FlashActivity.this, "AA",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            if ((Boolean) iv_cover.getTag()) {
                closeFlashLight();
            } else {
                openFlashLight();
            }

        }
    }
    @Override
    protected void openFlashLight() {
        if (!(Boolean) iv_cover.getTag()) {
            TransitionDrawable drawable = (TransitionDrawable) iv.getDrawable();
            drawable.startTransition(300);

            try {
                super.openFlashLight();
            } catch (IOException e) {
                Toast.makeText(FlashActivity.this, "开",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return;
            }
            iv_cover.setTag(true);
        }
    }
    @Override
    protected void closeFlashLight() {
        if ((Boolean) iv_cover.getTag()) {
            TransitionDrawable drawable = (TransitionDrawable) iv.getDrawable();
            drawable.reverseTransition(300);
            super.closeFlashLight();
            iv_cover.setTag(false);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        closeFlashLight();
    }
}
