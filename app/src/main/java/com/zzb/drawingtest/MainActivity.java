package com.zzb.drawingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzb.drawingtest.canvas.CanvasActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_canvas:
                toActivity(CanvasActivity.class);
                break;
        }
    }

    private void toActivity(Class cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
