package com.jeannius.tallycap;

import java.util.Locale;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.jeannius.tallycap.util.CalculatorViewPagerAdapter;
import com.jeannius.tallycap.util.MyAbstractActivity;
import com.jeannius.tallycap.util.MyCirclePageIndicator;
import com.jeannius.tallycap.util.myTabPageIndicator;
import com.jeannius.tallycap.util.myViewPager;



public class CalculatorActivity extends MyAbstractActivity {

	public static int NUM_TABS = 8;
	
	
	public static String GOOD ="good";

	public static String WEEKLY="Weekly";
	public static String BIWEEKLY="BiWeekly";
	public static String MONTHLY="Monthly";
	public static String YEARLY="Yearly";
	
	
	
	public Locale locale;
	private CalculatorViewPagerAdapter mAdapter;
	private myViewPager mPager;
	private myTabPageIndicator mIndicator;
	private MyCirclePageIndicator mIndicator2;
	private ActionBar bar;
	
	
	@Override
	protected void onCreate(Bundle arg0) {		
		super.onCreate(arg0);
		
		locale =getResources().getConfiguration().locale;
		setContentView(R.layout.calculator_tab_layout);
		mAdapter = new CalculatorViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
		mPager = (myViewPager) findViewById(R.id.myViewPager1);
		mPager.setAdapter(mAdapter);
		
		bar = getSupportActionBar();
		bar.setTitle(getResources().getString(R.string.calculator));
		bar.setDisplayHomeAsUpEnabled(true);
		
		mIndicator = new myTabPageIndicator(getApplicationContext(), mPager);
		mIndicator = (myTabPageIndicator) findViewById(R.id.myTabPageIndicator1);		
		
		
		mIndicator2 = new MyCirclePageIndicator(getApplicationContext(), mPager, (myTabPageIndicator) mIndicator);
		mIndicator2 = (MyCirclePageIndicator) findViewById(R.id.myCirclePageIndicator1);
		mIndicator2.setViewPager(mPager);
		mIndicator.setViewPager(mPager);
		mIndicator.setIndicatorListener(mIndicator2);
		
		mPager.addAdditionalListeners(mIndicator2);
		
		
		
	}

	
	
	
	
	
	

}
