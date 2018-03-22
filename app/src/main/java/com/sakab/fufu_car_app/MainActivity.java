package com.sakab.fufu_car_app;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import static java.lang.Math.toIntExact;

public class MainActivity extends Activity {
    RelativeLayout layout_joystick;
    ImageView image_joystick, image_border;
    TextView textView1, textView2, textView3, textView4, textView5, textView6;

    JoyStickClass js;
    Moteur moteur = new Moteur();
    int X;
    int Y;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);

        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);

        js = new JoyStickClass(getApplicationContext(), layout_joystick, R.drawable.image_button);
        js.setStickSize(200, 200);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(100);
        js.setStickAlpha(100);
        js.setOffset(0);
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
                        textView2.setText("Y : 100");
                        Y = 100;
                    }else if(js.getY()/2 < -100){
                        textView2.setText("Y : -100");
                        Y = -100;
                    }else{
                        Y = js.getY()/2;
                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                    textView1.setText("X :");
                    textView2.setText("Y :");
                }

                X = (int) Math.round(Math.cos(js.getAngle())*100);
                Y = (int) Math.round(Math.sin(js.getAngle())*100);
                moteur.calculVitesse(moteur,X,-Y);
                textView1.setText(String.valueOf("Moteur Gauche: "+moteur.getVitesseGauche()));
                textView2.setText(String.valueOf("Moteur Droit: "+moteur.getVitesseDroite()));
                textView3.setText(String.valueOf("X: "+ X));
                textView4.setText(String.valueOf("Y: "+ Y));
                textView5.setText(String.valueOf("Distance: "+ js.getDistance()));
                textView6.setText(String.valueOf("Angle: "+ js.getAngle()));
                return true;
            }
        });
    }
}
