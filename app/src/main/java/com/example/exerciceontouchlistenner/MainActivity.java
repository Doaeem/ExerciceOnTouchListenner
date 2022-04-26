package com.example.exerciceontouchlistenner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    ImageView im;
    RadioGroup rg;
    float xInitial;
    int[] mesImages = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg=findViewById(R.id.rgp);
        im = findViewById(R.id.img);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.r1: im.setImageResource(R.drawable.img1);break;
                    case R.id.r2: im.setImageResource(R.drawable.img2);break;
                    case R.id.r3: im.setImageResource(R.drawable.img3);break;
                }
            }
        });
        im.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        xInitial = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float xFinal = motionEvent.getX();

                        if(xInitial<xFinal){
                            pos--;
                        }else
                            pos++;

                        if(pos<0)
                            pos=0;
                        else if(pos == mesImages.length)
                            pos = mesImages.length-1;
                    case MotionEvent.ACTION_MOVE:
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();

                        im.setImageResource(mesImages[pos]);
                        im.setX(x);
                        im.setY(y);
                        break;
                }
                return true;
            }
        });
    }
}