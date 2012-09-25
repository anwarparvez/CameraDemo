package com.example.camerademo;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback {
	

	Camera mCamera;
	SurfaceHolder mHolder;
	
	DrawingThread	mThread;
	int	mRed	=	0;
	int	mGreen	=	0;
	int	mBlue	=	127;
	float[]	mVertices	=	new	float[6];
	int[]	mColors	=	{
		0xFFFFFFFF,	0xFFFFFFFF,	0xFFFFFFFF,
		0xFFFFFFFF,	0xFFFFFFFF,	0xFFFFFFFF};
	Paint	mPaint	=	new	Paint();	
	float	mAngle	=	0;
	float	mCenterX	=	0;
	float	mCenterY	=	0;

	public CameraPreview(Context context, Camera camera) {
		super(context);
		
		/*mThread	=	new	DrawingThread();*/
		//mPaint.setColor(0xFFFFFFFF);
		//mPaint.setStyle(Paint.Style.FILL);
		
		mCamera = camera;

		mHolder = getHolder();

		mHolder.addCallback(this);

		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		
		if (mHolder.getSurface() == null)
			return;
		
/*		mVertices[0] = width/2;
		mVertices[1] = height/2;
		mVertices[2] = width/2 + width/5;
		mVertices[3] = height/2 + width/5;
		mVertices[4] = width/2;
		mVertices[5] = height/2 + width/5;
		mCenterX = width/2 + width/10;
		mCenterY = height/2 + width/10;*/

		mCamera.stopPreview();

		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		//mThread.keepRunning	=	true;
		//mThread.start();
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
		
/*		mThread.keepRunning	=	false;
		boolean	retry	=	true;
		while	(retry)	{
			try	{
				mThread.join();
				retry	=	false;
			}	catch	(InterruptedException	e)	{}
		}*/

	}
	
/*	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawRGB(mRed, mGreen, mBlue);
		canvas.rotate(mAngle, mCenterX, mCenterY);
		canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 6,  mVertices, 0, null, 0, mColors, 0, null, 0, 0, mPaint);
	}*/

	class DrawingThread extends Thread {

		boolean keepRunning = true;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Canvas c;
			while (keepRunning) {
				c = null;
				try {
					c = mHolder.lockCanvas();
					synchronized (mHolder) {
						mAngle += 1;
						onDraw(c);
					}
				} finally {
					if (c != null)
						mHolder.unlockCanvasAndPost(c);
				}
				// Run the draw loop at 50 fps
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
			}
		}

	}

}
