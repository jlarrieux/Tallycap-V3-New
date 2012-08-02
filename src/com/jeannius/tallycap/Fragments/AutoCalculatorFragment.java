package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.AutoCalculatorView;

public class AutoCalculatorFragment extends CalculatorFragment {
	
		AutoCalculatorView autoView;
		
			
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			autoView = new AutoCalculatorView(getActivity());	
			
			if(savedInstanceState!=null) 
				if(savedInstanceState.containsKey(CalculatorFragment.RESULTTEXT))
					autoView.result.setText(savedInstanceState.getString(CalculatorFragment.RESULTTEXT));
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
