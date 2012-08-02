package com.jeannius.tallycap.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class myTabPageIndicator extends TabPageIndicator implements OnPageChangeListener{

	ViewPager vp;
	MyCirclePageIndicator in;
	Context context;
	
	public myTabPageIndicator(Context context, myViewPager mPager) {
		super(context);
		if (isInEditMode()) return;
		
		
		this.context =context;
	}
	
	public myTabPageIndicator(Context c, AttributeSet a){
		super(c,a);
		if (isInEditMode()) return;
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {		
		super.onPageScrollStateChanged(arg0);
		if(in!=null){
			if(arg0==ViewPager.SCROLL_STATE_IDLE){
				in.setInvisible();
			}
			else in.setVisible();
		}
	}
	
	public void setIndicatorListener(PageIndicator in){
		
		this.in= (MyCirclePageIndicator) in;
	}
	
	
	
	
	

}
