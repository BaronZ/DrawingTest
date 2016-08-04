package com.zzb.drawingtest.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceHolder回调可以在底层的Surface被创建、销毁和修改的时候通知View，并给它对SurfaceHolder对像的引用，其中包含了当前有效的Surface
 * 一个典型的SurfaceView设计方式包括一个由Thread所派生的类，它可以接受对当前的SurfaceHolder的引用，并独立地更新它
 * Created by ZZB on 2016/8/4.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private MySurfaceViewThread mySurfaceViewThread;
    private boolean hasSurface;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        //创建一个新的SurfaceHolder, 并分配这个类作为它的回调
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;
    }

    public void resume() {
        //创建并启动图形更新线程
        if (mySurfaceViewThread == null) {
            mySurfaceViewThread = new MySurfaceViewThread();
            if (hasSurface) {
                mySurfaceViewThread.start();
            }
        }
    }

    public void pause() {
        //结束图形更新线程
        if (mySurfaceViewThread != null) {
            mySurfaceViewThread.requestExitAndWait();
            mySurfaceViewThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        hasSurface = true;
        if (mySurfaceViewThread != null) {
            mySurfaceViewThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int w, int h) {
        if (mySurfaceViewThread != null) {
            mySurfaceViewThread.onWindowResize(w, h);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        hasSurface = false;
        pause();
    }

    class MySurfaceViewThread extends Thread {
        private boolean done;

        public MySurfaceViewThread() {
            super();
            done = false;
        }

        @Override
        public void run() {
            SurfaceHolder surfaceHolder = holder;
            //重复绘图循环，直到线程停止
            while (!done) {
                //锁定Surface,并返回要在其上绘图的画布
                Canvas canvas = surfaceHolder.lockCanvas();
                // TODO: 2016/8/4 在画布上绘图
                //解锁画布，并渲染当前的图像
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void requestExitAndWait() {
            //把这个线程标记为完成，并合并到主应用程序线程
            done = true;
            try {
                join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        public void onWindowResize(int w, int h) {
            //处理可用Surface尺寸的改变
        }
    }
}

