package com.thismayhem.labs.a.app;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class CameraActivity extends Activity {

    private Camera camera;
    private View view;

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setResult(RESULT_CANCELED);
        // Camera may be in use by another activity or the system or not available   at all
        try {
            camera = getCameraInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

            initCameraPreview();

    }

    // Show the camera view on the activity and show overlay animation
    private void initCameraPreview() {
        CameraPreview cameraPreview = (CameraPreview) findViewById(R.id.view);
        cameraPreview.init(camera);

        ImageView animatedImage = (ImageView) findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animatedImage.startAnimation(animation);
    }


}