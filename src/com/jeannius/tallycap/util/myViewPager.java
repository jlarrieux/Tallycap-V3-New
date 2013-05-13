package com.jeannius.tallycap.util;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 * 
 * @author Jeannius
 *This class extends viewPager with the ability to have multiple listeners
 */


public class myViewPager extends ViewPager implements SubjectToObservers{
	
	private ArrayList<OnPageChangeListener> additional;
	
	int a0, a2;
	float a1;
	
	public myViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) return;
		additional =new ArrayList<ViewPager.OnPageChangeListener>();
		
	}
	
	//this next function create an array of listener and/or add an additional listener to the array
//	public void addAdditionalListeners(OnPageChangeListener listener){
//		if(additional !=null) additional = new ArrayList<ViewPager.OnPageChangeListener>();
//		additional.add(listener);
//	}
	
	
	
	//this function iterates though the array and make sure the additional listeners get the event
	@Override
	protected void onPageScrolled(int arg0, float arg1, int arg2) {		
		super.onPageScrolled(arg0, arg1, arg2);
		a0= arg0;
		a1 = arg1;
		a2 = arg2;
		notifyObserver();	
		
	}
	
	


	@Override
	public void notifyObserver() {
		if (additional!=null){		
			
			if(additional.size()>0){
				
				OnPageChangeListener monChangeListener;
				
				for(int i=0; i<additional.size(); i++){
					monChangeListener = additional.get(i);
					if(monChangeListener!=null) monChangeListener.onPageScrolled(a0, a1, a2);
				}
				
			}
		}
		
		
		
	}

	@Override
	public void registerObserver(ObserverOfSubject obs) {
		additional.add((OnPageChangeListener) obs);
		
	}
	
	


	@Override
	public void removeObserver() {
		
		
	}

	@Override
	public void removeObserver(ObserverOfSubject obs) {
		additional.remove((OnPageChangeListener)obs);
		
	}
	
	
	
	
}
