package com.zzb.drawingtest.canvas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zzb.drawingtest.R;
import com.zzb.drawingtest.canvas.drawcircle.CircleView;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        CircleView circleView = (CircleView) findViewById(R.id.circle_view);
        circleView.setBorder1(120, Color.YELLOW);
        circleView.setBorder2(40, 0x99000000);
        circleView.notifyUIChanged();
    }
}
