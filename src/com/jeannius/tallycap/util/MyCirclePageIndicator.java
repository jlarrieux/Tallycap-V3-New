package com.jeannius.tallycap.util;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.viewpagerindicator.CirclePageIndicator;

public class MyCirclePageIndicator extends CirclePageIndicator implements OnPageChangeListener{
	
	private int currentVisibility ;
    private Context context;

	myTabPageIndicator mt;
	
	
	public MyCirclePageIndicator(Context context, myViewPager mPager, myTabPageIndicator mt) {
		super(context);
		if (isInEditMode()) return;
		
		this.setVisibility(View.VISIBLE);
		this.mt = mt;
		this.context = context;
		
	}
	public MyCirclePageIndicator(Context c, AttributeSet a){
		super(c,a);
		if (isInEditMode()) return;
		this.context = c;
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

	
	
}
