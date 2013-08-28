package com.jeannius.tallycap.util;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jeannius.tallycap.CalculatorActivity;

public abstract class MyAbstractActivity extends SherlockFragmentActivity {
	public static final String CALCULATOR="Calculator";
	public static final String ACCOUNT="Account";
	public static final String REMINDER="Reminder";
	public static final String BUDGET_PAD="Budget Pad";
	Intent intent;
//	private final static boolean debug = true;
	public Global g;
	

	 
	 public void switchTo(String Class_To){
		 intent = new Intent();
		 
		 if(Class_To.equals(CALCULATOR))intent.setClass(getApplication(), CalculatorActivity.class);
		 else if(Class_To.equals(ACCOUNT));
		 else if(Class_To.equals(REMINDER));
		 else if(Class_To.equals(BUDGET_PAD));
		
		 startActivity(intent);
	 }
	 
	 

	 
	 
	 @Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		g = new Global(getApplicationContext());
		ViewServer.get(this).addWindow(this);
	}
	 
	 
	 @Override
	protected void onDestroy() {
		
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}
	 
	 
	 @Override
	protected void onResume() {
		
		super.onResume();
		ViewServer.get(this).setFocusedWindow(this);
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
