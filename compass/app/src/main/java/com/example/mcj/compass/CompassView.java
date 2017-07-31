package com.example.mcj.compass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

class CompassView2 extends View{

    public Bitmap compassImg;

    int centerX, centerY;

    int compassX, compassY;
    public CompassView2(Context context) {
        super(context);

        WindowManager managerW = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = managerW.getDefaultDisplay();

        int screenW = display.getWidth();
        int screenH = display.getHeight();
        centerX = screenW/2;
        centerY = screenH/2;
        compassImg=BitmapFactory.decodeResource(getResources(), R.drawable.compass);
        compassImg = Bitmap.createScaledBitmap(compassImg, screenW, screenW, false);
        compassX = compassImg.getWidth()/2;
        compassY = compassImg.getHeight()/2;
        handler.sendEmptyMessage(0);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate( -heading, centerX, centerY);
        canvas.drawBitmap(compassImg, (centerX-compassX), (centerY-compassY), null);
    }

    int heading;
    public void updateUI(int heading){
        this.heading = heading;
    }

    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            invalidate();
            handler.sendEmptyMessageDelayed(0, 10);

        }
    };

}
