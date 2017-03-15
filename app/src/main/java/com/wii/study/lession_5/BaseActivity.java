package com.wii.study.lession_5;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;

/**
 * Created by wu on 2017/3/8.
 */

public class BaseActivity extends Activity {
    protected Camera mCamera;
    protected Camera.Parameters mParameters;

    protected int mDefaultBrightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDefaultBrightness = getScreenBrightness();
    }
    protected synchronized void openFlashLight() throws IOException {
        if (mCamera == null) {
            mCamera = Camera.open();
            mCamera.setPreviewTexture(new SurfaceTexture(0));
            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
            mCamera.startPreview();
        }

    }

    protected synchronized void closeFlashLight() {
        if (mCamera != null) {
            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(mParameters);

            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    protected int getScreenBrightness() {
        int value = 0;
        try {
            value = android.provider.Settings.System.getInt(
                    getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    protected void setScreenBrightness(float light) {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = light;
        getWindow().setAttributes(layout);
    }
}
