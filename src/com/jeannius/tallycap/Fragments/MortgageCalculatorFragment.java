package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.MortgageCalculatorView;

public class MortgageCalculatorFragment extends CalculatorFragment {
	
	MortgageCalculatorView mortgageView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mortgageView = new MortgageCalculatorView(getActivity());
		mortgageView.unDate.setFragmentManager(getChildFragmentManager());
		
		mortgageView.amount.seekBar.setOnSeekBarLongPressListener(this);
		mortgageView.interest.seekBar.setOnSeekBarLongPressListener(this);
		mortgageView.yearlyTaxes.seekBar.setOnSeekBarLongPressListener(this);
		mortgageView.downPayment.seekBar.setOnSeekBarLongPressListener(this);
		
		
		return mortgageView;
	}

}
