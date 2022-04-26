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
    int[] MesRds = {R.id.r1,R.id.r2,R.id.r3};
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
                    case R.id.r1: im.setImageResource(R.drawable.img1); pos=0; break;
                    case R.id.r2: im.setImageResource(R.drawable.img2); pos=1; break;
                    case R.id.r3: im.setImageResource(R.drawable.img3); pos=2; break;
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
                            pos++;
                        }else
                            pos--;

                        if(pos<0)
                            pos= MesRds.length-1;
                        else if(pos >= MesRds.length)
                            pos = 0;

                        rg.check(MesRds[pos]);
                        break;
                }
                return true;
            }
        });
    }
}