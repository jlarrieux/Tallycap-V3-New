package com.jeannius.tallycap.util;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;

public class myTabPageIndicator extends TabPageIndicator  implements SubjectToObservers{

	ViewPager vp;
	MyCirclePageIndicator in;
	Context context;
	private ArrayList<ObserverOfSubject> additional2;
	
	
	public myTabPageIndicator(Context context, myViewPager mPager) {
		super(context);
		if (isInEditMode()) return;
		additional2 = new ArrayList<ObserverOfSubject>();
		
		this.context =context;
	}
	
	public myTabPageIndicator(Context c, AttributeSet a){
		super(c,a);
		additional2 = new ArrayList<ObserverOfSubject>();
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

	
	
	
	
	
	/**
	 * my new codes start here
	 */
	
	@Override
	public void onPageSelected(int arg0) {
		
		super.onPageSelected(arg0);
		notifyObserver();
	}
	
	
	@Override
	public void notifyObserver() {
		if(additional2!=null){
			if(additional2.size()>0){
				for(int i=0; i<additional2.size(); i++){
					additional2.get(i).update("");
				}
			}
		}
		
	}

	@Override
	public void registerObserver(ObserverOfSubject obs) {
		additional2.add(obs);
		
	}

	@Override
	public void removeObserver(ObserverOfSubject obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
