package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.LoanCalculatorView;

public class LoanCalculatorFragment extends CalculatorFragment {

	LoanCalculatorView loanView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		loanView = new LoanCalculatorView(getActivity());
		loanView.amount.seekBar.setOnSeekBarLongPressListener(this);
		loanView.interest.seekBar.setOnSeekBarLongPressListener(this);
		loanView.length.seekBar.setOnSeekBarLongPressListener(this);
		loanView.unDate.setFragmentManager(getChildFragmentManager());
		
		return loanView;
	}

	
	
		
	

}
