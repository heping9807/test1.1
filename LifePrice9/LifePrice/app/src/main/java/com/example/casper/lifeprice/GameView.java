package com.example.casper.lifeprice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by jsjzx on 2019/11/18.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;
    DrawThread drawThread;
    ArrayList<Sprite> sprites=new ArrayList<Sprite>();

    float xTouch=-1,yTouch=-1;
    int touchnumber=0;

    public GameView(Context context) {
        super(context);
        holder=this.getHolder();

        holder.addCallback(this);

        sprites.add(new Sprite());
        sprites.add(new Sprite());
        sprites.add(new Sprite());
        sprites.add(new Sprite());
        sprites.add(new Sprite());


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xTouch=event.getX();
                yTouch=event.getY();
                return false;
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread=new DrawThread();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(null!=drawThread)
        {
            drawThread.stopThread();
            drawThread=null;
        }
    }

    private class DrawThread extends Thread {
        private Boolean beAlive=true;
        public void stopThread() {
            beAlive=false;
        }
        @Override
        public void run() {
            while(beAlive) {
                try{
                    //获得绘画的画布
                    Canvas canvas =  holder.lockCanvas();
                    //背景设成蓝色
                    canvas.drawColor(Color.WHITE);

                    //在20，40的地方画一个文本hello world!
                    Paint p = new Paint();
                    p.setTextSize(50);
                    p.setColor(Color.BLACK);
                    if(xTouch>0) {
                        canvas.drawText("你点击了"+xTouch+","+yTouch,20,40,p);
                    }
                    else
                    canvas.drawText("hello world!",20,40,p);
                    canvas.drawText("你击中了"+touchnumber+"个目标",20,100,p);
                    for (Sprite sprite:sprites) {
                        sprite.move();
                    }

                    for (Sprite sprite:sprites) {
                        canvas.drawCircle(sprite.getX(),sprite.getY(),15,p);
                    }
                    for(Sprite sprite:sprites){
                        sprite.draw(canvas);
                    }

                    for(Sprite sprite:sprites) {
                        if (xTouch-getX()>0 && yTouch-getY()>0
                                && xTouch-getX()<100&& yTouch-getY()<100)
                            touchnumber++;

                    }

                    holder.unlockCanvasAndPost(canvas);//解锁
                    sleep(30);
                }catch (Exception e){
                }
            }
        }
    }

    private class Sprite {
        int x,y;
        double directionAgle;

        public Sprite() {
            x= (int) (GameView.this.getWidth()*Math.random());
            y= (int) (GameView.this.getHeight()*Math.random());
            directionAgle=Math.random()*2*Math.PI;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void move() throws InterruptedException {
            x+=15*Math.cos(directionAgle);
            y+=15*Math.sin(directionAgle);
            if(x<0) x+=GameView.this.getWidth();
            if(x>GameView.this.getWidth())x-=GameView.this.getWidth();
            if(y<0) y+=GameView.this.getHeight();
            if(y>GameView.this.getHeight())y-=GameView.this.getHeight();

            if(Math.random()<0.05)directionAgle=Math.random()*2*Math.PI;

            if (xTouch-getX()>0 && yTouch-getY()>0
                    && xTouch-getX()<100 && yTouch-getY()<100) {
                touchnumber++;
                sleep(100);
                xTouch=yTouch=0;
                x= (int) (GameView.this.getWidth()*Math.random());
                y= (int) (GameView.this.getHeight()*Math.random());
                directionAgle=Math.random()*2*Math.PI;
            }

        }


        public void draw(Canvas canvas)
        {
            Drawable drawable=getContext().getResources().getDrawable(R.drawable.a2);
            Rect drawableRect = new Rect(x,y,x+drawable.getIntrinsicWidth(),
                    y+drawable.getIntrinsicHeight());
            drawable.setBounds(drawableRect);
            drawable.draw(canvas);
        }
    }
}
