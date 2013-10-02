package com.jeannius.tallycap.util;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.jeannius.tallycap.R;
import com.viewpagerindicator.CirclePageIndicator;

public class MyCirclePageIndicator extends CirclePageIndicator implements ObserverOfSubject{
	
	private int currentVisibility ;
    private Context context;
   
//    private OurView v;
   

	myTabPageIndicator mt;
	
	
	public MyCirclePageIndicator(Context context, myViewPager mPager, myTabPageIndicator mt) {
		super(context);
		if (isInEditMode()) return;

		this.setVisibility(View.VISIBLE);
		this.mt = mt;
		this.context = context;
		init();
		
	}
	public MyCirclePageIndicator(Context c, AttributeSet a){
		super(c,a);
		if (isInEditMode()) return;
		this.context = c;
		init();
	}
	
	
	@Override
	public void onPageScrollStateChanged(int state) {
		
		super.onPageScrollStateChanged(state);
		
			if(state== ViewPager.SCROLL_STATE_IDLE){
				setInvisible();
				
			}
			else setVisible();
		
		
	}
	
	
	public void setInvisible(){
	
			this.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out));
			currentVisibility = View.INVISIBLE;
			this.setVisibility(currentVisibility);	
		
	}
	
	
	public void setVisible(){
		currentVisibility = View.VISIBLE;
		this.setVisibility(currentVisibility);
	}
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	//for testing
	private void init(){
//		int[] fillMove = getResources().getIntArray(R.array.android_color_palette_orange);
		
		this.setBackgroundColor(Color.TRANSPARENT);
		int[] fillAll = getResources().getIntArray(R.array.android_color_palette_blue);
		setPageColor(0x99555555);
		setFillColor(fillAll[13]);
	}
	@Override
	public int getCurrentMode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
		
		
	
	

	
}
