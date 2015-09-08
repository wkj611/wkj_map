package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2015/8/29.
 */
public class MyView extends View {
    public MyView(Context context){
        super(context);
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.RED);// 设置红色
        p.setTextSize(20);
        //canvas.drawText("画圆：", 10, 20, p);// 画文本
        canvas.drawCircle(100, 100, 20, p);// 小圆
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(200, 200, 20, p);// 大圆
    }
}
