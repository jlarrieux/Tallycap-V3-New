package com.jeannius.tallycap;

import java.util.Locale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import com.jeannius.tallycap.Fragments.CalculatorTabFragment;
import com.jeannius.tallycap.util.MyAbstractActivity;



public class CalculatorActivity extends MyAbstractActivity implements OnClickListener {

	public static int NUM_TABS = 8;
	public static Dialog d;
	public static String GOOD ="good";
	
	
	
	
	
	public Locale locale;
	
	
	@Override
	protected void onCreate(Bundle arg0) {		
		super.onCreate(arg0);
		
		setContentView(com.jeannius.tallycap.R.layout.plain);
		CalculatorTabFragment c = new CalculatorTabFragment();
		
	
		getSupportFragmentManager().beginTransaction().add(com.jeannius.tallycap.R.id.RED, c).commit();
		
		locale =getResources().getConfiguration().locale;				
//		TempPopUpAnimatoOption t = new TempPopUpAnimatoOption(getApplicationContext());
		
		d=new Dialog(getApplication());
		AlertDialog.Builder  b = new AlertDialog.Builder(getApplicationContext());
		b.setPositiveButton("Dismiss", this);
//		b.setView(t);
		d =b.create();
	}


//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		
//		
//		g.toaster(item.toString(), true);
//		boolean b =false;
//				if(item.getItemId()== com.jeannius.tallycap.R.id.TempShow){
//					
//					d.show();
//					b =true;
//				}
//		return super.onOptionsItemSelected(item);
//	}


	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
		
	}

	
	
	
	
	
	

}
