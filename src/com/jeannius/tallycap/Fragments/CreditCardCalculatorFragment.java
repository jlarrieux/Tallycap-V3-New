package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.CreditCardCalculatorView;

public class CreditCardCalculatorFragment extends CalculatorFragment {

	CreditCardCalculatorView creditView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		creditView = new CreditCardCalculatorView(getActivity());	
		
		creditView.balance.seekBar.setOnSeekBarLongPressListener(this);
		creditView.interest.seekBar.setOnSeekBarLongPressListener(this);
		creditView.creditLimit.seekBar.setOnSeekBarLongPressListener(this);
		creditView.monthlyFee.seekBar.setOnSeekBarLongPressListener(this);
		creditView.annualFee.seekBar.setOnSeekBarLongPressListener(this);
		creditView.overTheLimitFee.seekBar.setOnSeekBarLongPressListener(this);
		
		return creditView;
	}

	
}
