package com.jeannius.tallycap.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class MySeekBar extends SeekBar {
	
	private int mMin=0;

	private onSeekBarLongPressListener mOnSeekBarLonPressListener;
	
	public MySeekBar(Context context) {
		super(context);		
		
	}

	public MySeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

	
	@Override
	public synchronized void setProgress(int progress) {
		if(progress<mMin) progress=mMin;
		super.setProgress(progress);
	}

	
	public void setMin(int min){
		mMin =min;
	}
	
	
	public int getMin(){
		return mMin;
	}
	
	
	public void setOnSeekBarLongPressListener(onSeekBarLongPressListener l){
		mOnSeekBarLonPressListener = l;
	}
	
	public onSeekBarLongPressListener getSeekBarLongPressListener(){
		return mOnSeekBarLonPressListener;
	}
	
	
	public interface onSeekBarLongPressListener{
		
		void longPressOccured (MySeekBarWidget mySeekBarWidget);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
