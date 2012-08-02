package com.jeannius.tallycap.util;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class myViewPager extends ViewPager {
	
	private ArrayList<OnPageChangeListener> additional;
	
	public myViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) return;
		additional =new ArrayList<ViewPager.OnPageChangeListener>();
	}

	public void addAdditionalListeners(OnPageChangeListener listener){
		if(additional !=null) additional = new ArrayList<ViewPager.OnPageChangeListener>();
		additional.add(listener);
	}
	
	

	@Override
	protected void onPageScrolled(int arg0, float arg1, int arg2) {		
		super.onPageScrolled(arg0, arg1, arg2);
		
		if (additional!=null){		
		
			if(additional.size()>0){
				
				OnPageChangeListener monChangeListener;
				
				for(int i=0; i<additional.size(); i++){
					monChangeListener = additional.get(i);
					if(monChangeListener!=null) monChangeListener.onPageScrolled(arg0, arg1, arg2);
				}
				
			}
		}
	}
	
	
	
	
}
