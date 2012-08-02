package com.jeannius.tallycap.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jeannius.tallycap.CalculatorActivity;
import com.jeannius.tallycap.R;

public class CalculatorViewPagerAdapter extends FragmentPagerAdapter {
	
	private int mCount = CalculatorActivity.NUM_TABS;
	private Context context;
	
	public CalculatorViewPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
		
	}

	@Override
	public Fragment getItem(int arg0) {
		
		return CalculatorFragmentFactoryBuilder.Build(arg0 % CalculatorActivity.NUM_TABS);
	}

	@Override
	public int getCount() {		
		return mCount;
	}
	
	public void setCount(int count){
		if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
		
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		String f="";
		int red =position % CalculatorActivity.NUM_TABS;
		Resources r = context.getResources();
		switch(red){
		
		case 0: f = r.getString(R.string.auto);
				break;
				
		case 1: f = r.getString(R.string.mortgage);
				break;
		
		case 2: f = r.getString(R.string.loan);
				break;
			
		case 3: f = r.getString(R.string.credit_card);
				break;
				
		case 4: f = r.getString(R.string.k401K);
				break;
				
		case 5: f = r.getString(R.string.affordability);
				break;	
				
		case 6: f = r.getString(R.string.savings_future);
				break;
		
		case 7: f = r.getString(R.string.savings_planner);
				break;
	
		}
		
		return f;
	}

}
