package com.jeannius.tallycap.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.jeannius.tallycap.util.MySeekBar.onSeekBarLongPressListener;
import com.jeannius.tallycap.util.MySeekBarWidget;

public abstract class CalculatorFragment extends SherlockFragment implements onSeekBarLongPressListener, DialogInterface.OnClickListener{

	protected String mText;
	public static final String RESULTTEXT ="result";
	whatIfButtonSwitch wf;
	SeekBarNumberInputPopUp numberInputPopUp;
	private static  SeekBarNumberInputPopUp d1;
	
	public static TempInterpolatorDialogFragment d2;
	
	public String getText(){
		return mText;
	}
	
	
	/**
	 * This next interface lets the child fragments communicate with the parent (either parent activity or parent fragment)0
	 */
	
	public interface whatIfButtonSwitch{
		
		public void currentWhatIf(Button WhatIf);
	}
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		if(d2==null)d2 = new TempInterpolatorDialogFragment();
		setHasOptionsMenu(true);
		
		super.onCreate(savedInstanceState);
	}
	
	

	
	
	@Override
	public void longPressOccured(MySeekBarWidget mySeekBarWidget) {
		
		if(d1==null) d1 =new SeekBarNumberInputPopUp();
		int id = mySeekBarWidget.getId();
		
		if(!d1.isAdded()){
			d1 = SeekBarNumberInputPopUp.newInstance(mySeekBarWidget.maxAbsoluteFake, id);
			d1.show(getFragmentManager(), "");						
		}
				
	}
	

	
	
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	
	
	
}
