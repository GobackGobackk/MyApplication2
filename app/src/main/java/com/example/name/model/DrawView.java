package com.example.name.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 建立畫筆
        Paint p = new Paint();
        p.setColor(Color.RED);// 設定紅色

        canvas.drawText("畫圓：", 10, 20, p);// 畫文字
        canvas.drawCircle(60, 20, 10, p);// 小圓
        p.setAntiAlias(true);// 設定畫筆的鋸齒效果。 true是去除，大家一看效果就明白了

    }
}
