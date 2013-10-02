package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.AutoCalculatorView;

public class AutoCalculatorFragment extends CalculatorFragment {
	
		AutoCalculatorView autoView;
		
			
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
			autoView = new AutoCalculatorView(getActivity());				
			autoView.unDate.setFragmentManager(getChildFragmentManager());
			autoView.amount.seekBar.setOnSeekBarLongPressListener(this);
			autoView.interest.seekBar.setOnSeekBarLongPressListener(this);
			autoView.tradeInValue.seekBar.setOnSeekBarLongPressListener(this);
			autoView.downPayment.seekBar.setOnSeekBarLongPressListener(this);
			autoView.amount.progressSetter(10000, false);
			autoView.interest.progressSetter(500, false);
			
		return autoView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {	
	super.onSaveInstanceState(outState);
	if(autoView!=null)
		if(autoView.result!=null)
			if(autoView.result.getText().toString().length()>0)
				outState.putString(CalculatorFragment.RESULTTEXT, autoView.result.getText().toString());
			
		
	}


}
