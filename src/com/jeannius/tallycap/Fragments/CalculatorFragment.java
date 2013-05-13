package com.jeannius.tallycap.Fragments;

import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.jeannius.tallycap.util.MySeekBar;
import com.jeannius.tallycap.util.MySeekBar.onSeekBarLongPressListener;

public abstract class CalculatorFragment extends SherlockFragment implements onSeekBarLongPressListener{

	protected String mText;
	public static final String RESULTTEXT ="result";
	whatIfButtonSwitch wf;
	SeekBarNumberInputPopUp numberInputPopUp;
	
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
	public void onResume() {
		
		super.onStart();
//		Toast.makeText(getActivity(), this.getClass().toString(), Toast.LENGTH_LONG).show();
	}
	
	
//	@Override
//	public boolean onLongClick(View v) {
//		
//		numberInputPopUp = new SeekBarNumberInputPopUp();
//		numberInputPopUp.show(getFragmentManager(), "red");
//		
//		return false;
//	}
	
	
	@Override
	public void longPressOccured(MySeekBar mySeekBar) {
		numberInputPopUp = new SeekBarNumberInputPopUp();
		numberInputPopUp.show(getFragmentManager(), "red");
		
	}
	
	
	
	
	
	
	
	
	
}
