package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.FutureSavingsCalculatorView;

public class FutureSavingsCalculatorFragment extends CalculatorFragment {
	
	FutureSavingsCalculatorView futureSavingsView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		futureSavingsView = new FutureSavingsCalculatorView(getActivity());
		futureSavingsView.amount.seekBar.setOnSeekBarLongPressListener(this);
		futureSavingsView.interest.seekBar.setOnSeekBarLongPressListener(this);
		futureSavingsView.length.seekBar.setOnSeekBarLongPressListener(this);
		futureSavingsView.currentSavings.seekBar.setOnSeekBarLongPressListener(this);
		
		return futureSavingsView;
	}

	
}
