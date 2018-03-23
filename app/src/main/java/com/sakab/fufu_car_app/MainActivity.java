package com.sakab.fufu_car_app;

import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    RelativeLayout layout_joystick;
    JoyStickClass js;
    int X;
    int Y;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        js = new JoyStickClass(getApplicationContext(), layout_joystick, R.drawable.image_button);
        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(70);
        js.setMinimumDistance(50);

        layout_joystick.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    if(js.getX()/2 > 100){
                        X = 100;
                    }else if(js.getX()/2 < -100){
                        X = -100;
                    }else{
                        X = js.getX()/2;
                    }
                    if(js.getY()/2 > 100){
                        Y = 100;
                    }else if(js.getY()/2 < -100){
                        Y = -100;
                    }else{
                        Y = js.getY()/2;
                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    X = 0;
                    Y = 0;
                }
                if(X != 0 && Y != 0){
                    X = (int) Math.round(Math.cos(js.getAngle())*100);
                    Y = (int) Math.round(Math.sin(js.getAngle())*100);

                }
                return true;
            }
        });
    }
}
