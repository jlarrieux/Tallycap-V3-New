package com.jeannius.tallycap.util;

import android.content.Intent;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeannius.tallycap.CalculatorActivity;
import com.jeannius.tallycap.HomeActivity;
import com.jeannius.tallycap.R;

public abstract class MyAbstractActivity extends SherlockFragmentActivity {
	public static final String CALCULATOR="Calculator";
	public static final String ACCOUNT="Account";
	public static final String REMINDER="Reminder";
	public static final String BUDGET_PAD="Budget Pad";
	Intent intent;
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) { 
	    	com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
	    	inflater.inflate(R.menu.main_overflow, menu);    	
	    	return true;	    	
	    }
	 
	 public void switchTo(String Class_To){
		 intent = new Intent();
		 
		 if(Class_To.equals(CALCULATOR))intent.setClass(getApplication(), CalculatorActivity.class);
		 else if(Class_To.equals(ACCOUNT));
		 else if(Class_To.equals(REMINDER));
		 else if(Class_To.equals(BUDGET_PAD));
		
		 startActivity(intent);
	 }
	 
	 
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case android.R.id.home:
				intent = new Intent(this, HomeActivity.class);
				intent.addFlags(
						Intent.FLAG_ACTIVITY_CLEAR_TOP|
						Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
