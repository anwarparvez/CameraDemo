package com.example.camerademo;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v4.app.NavUtils;

public class CameraActivity extends Activity {

	Camera mCamera;
	CameraPreview mPreview;
	MySurfaceView mySurfaceView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_camera);
       // mCamera=getCameraInstance();
      //  mPreview=new CameraPreview(this, mCamera);
        
        //FrameLayout frameLayout=(FrameLayout) findViewById(R.id.camera_preview);
        
        //frameLayout.addView(mPreview);
        //setContentView(mPreview);
      //  mySurfaceView =new MySurfaceView(context, attrs)
        //setContentView(n)
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_camera, menu);
		return true;
	}

	static Camera getCameraInstance() {
		Camera c = null;

		try {
			c = Camera.open();
		} catch (Exception e) {

		}

		return c;
	}

}
