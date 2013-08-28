package com.jeannius.tallycap.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class myFrameLayout2 extends FrameLayout {
	
	
public myFrameLayout2(Context context) {
		super(context);
		init();
	}

public myFrameLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public myFrameLayout2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private Canvas c;
	
	
	private void init(){
		c = new Canvas();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		this.c = canvas;
	}
	
	public Canvas returnCanvas(){
		return c;
	}
	
}

