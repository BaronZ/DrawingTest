package com.zzb.drawingtest.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzb.drawingtest.R;

public class DrawActivity extends AppCompatActivity {
    private TouchDrawCanvasView mCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        mCanvasView = (TouchDrawCanvasView) findViewById(R.id.touch_canvas);
    }

    public void clear(View view) {
        mCanvasView.clearCanvas();
    }
}
