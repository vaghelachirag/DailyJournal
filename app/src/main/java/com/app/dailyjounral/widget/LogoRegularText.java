package com.app.dailyjounral.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LogoRegularText extends AppCompatTextView {

    public LogoRegularText(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "JasmineUpc.ttf");
        this.setTypeface(face);
    }

    public LogoRegularText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "JasmineUpc.ttf");
        this.setTypeface(face);
    }

    public LogoRegularText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "JasmineUpc.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

    }
}
