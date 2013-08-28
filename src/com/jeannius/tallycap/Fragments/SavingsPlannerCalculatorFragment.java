package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.SavingsPlannerCalculatorView;

public class SavingsPlannerCalculatorFragment extends CalculatorFragment {

	SavingsPlannerCalculatorView savingsPlannerView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		savingsPlannerView = new SavingsPlannerCalculatorView(getActivity());
		
		savingsPlannerView.goal.seekBar.setOnSeekBarLongPressListener(this);
		savingsPlannerView.length.seekBar.setOnSeekBarLongPressListener(this);
		savingsPlannerView.interestRate.seekBar.setOnSeekBarLongPressListener(this);
		savingsPlannerView.currentSavings.seekBar.setOnSeekBarLongPressListener(this);
		
		return savingsPlannerView;
	}
	
}
