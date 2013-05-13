package com.jeannius.tallycap;

import java.util.Locale;

import android.os.Bundle;

import com.jeannius.tallycap.Fragments.CalculatorMainFragment;
import com.jeannius.tallycap.util.MyAbstractActivity;



public class CalculatorActivity extends MyAbstractActivity {

	public static int NUM_TABS = 8;
	
	
	public static String GOOD ="good";

	public static String WEEKLY="Weekly";
	public static String BIWEEKLY="BiWeekly";
	public static String MONTHLY="Monthly";
	public static String YEARLY="Yearly";
	
	
	
	public Locale locale;
	
	
	@Override
	protected void onCreate(Bundle arg0) {		
		super.onCreate(arg0);
		
		setContentView(R.layout.plain);
		CalculatorMainFragment c = new CalculatorMainFragment();
		
	
		getSupportFragmentManager().beginTransaction().add(R.id.RED, c).commit();
		
		locale =getResources().getConfiguration().locale;		
	}

	
	
	
	
	
	

}
