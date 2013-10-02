package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.CalculatorViewPagerAdapter;
import com.jeannius.tallycap.util.Global;
import com.jeannius.tallycap.util.MyCirclePageIndicator;
import com.jeannius.tallycap.util.ObserverOfSubject;
import com.jeannius.tallycap.util.myTabPageIndicator;
import com.jeannius.tallycap.util.myViewPager;

public class CalculatorTabFragment extends SherlockFragment implements ObserverOfSubject{

	
	public CalculatorViewPagerAdapter mAdapter;
	private myViewPager mPager;
	private myTabPageIndicator mTabIndicator;
	private MyCirclePageIndicator mCircleIndicator2;
	public Global g;
	public static TempInterpolatorDialogFragment d2;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {

		View root = inflater.inflate(com.jeannius.tallycap.R.layout.calculator_tab_layout, null);
		mAdapter = new CalculatorViewPagerAdapter(getChildFragmentManager(), getActivity());
		mPager = (myViewPager) root.findViewById(com.jeannius.tallycap.R.id.myViewPager1);
		mPager.setAdapter(mAdapter);
		
		
		mTabIndicator = new myTabPageIndicator(getActivity(), mPager);
		mTabIndicator = (myTabPageIndicator) root.findViewById(com.jeannius.tallycap.R.id.myTabPageIndicator1);		
		
		
		mCircleIndicator2 = new MyCirclePageIndicator(getActivity(), mPager, (myTabPageIndicator) mTabIndicator);
		mCircleIndicator2 = (MyCirclePageIndicator) root.findViewById(com.jeannius.tallycap.R.id.myCirclePageIndicator1);
		mCircleIndicator2.setViewPager(mPager);
		
		mTabIndicator.setViewPager(mPager);
		mTabIndicator.setIndicatorListener(mCircleIndicator2);
		
		mPager.registerObserver(mCircleIndicator2);
		mTabIndicator.registerObserver(this);
		setHasOptionsMenu(true);
		g = new Global(getActivity());
		
		d2 = new TempInterpolatorDialogFragment();
		
		return root;
	}
	
	

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String s) {
		// TODO Auto-generated method stub
		
	
		
		
	}
	

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		g.toaster(String.valueOf(item.getTitle()), true);
		
		if(item.getItemId()== R.id.TempShow){
			
			d2.show(getFragmentManager(), "");
		}
		return super.onOptionsItemSelected(item);
	}



	@Override
	public int getCurrentMode() {
		// TODO Auto-generated method stub
		return 0;
	}


	
}


