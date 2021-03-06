package com.jeannius.tallycap.Fragments;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.MySeekBar.onSeekBarLongPressListener;
import com.jeannius.tallycap.util.MySeekBarWidget;

public abstract class CalculatorFragment extends SherlockFragment implements onSeekBarLongPressListener, DialogInterface.OnClickListener, OnNavigationListener{

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
//		setHasOptionsMenu(true);
		
		
		getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter ms = ArrayAdapter.createFromResource(getActivity(), R.array.calculator_tab_fragment_groups, android.R.layout.simple_spinner_dropdown_item);
		getActivity().getActionBar().setListNavigationCallbacks(ms, this);
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
	
	
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
