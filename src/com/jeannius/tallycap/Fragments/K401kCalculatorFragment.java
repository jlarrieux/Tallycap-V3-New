package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.K401kCalculatorView;

public class K401kCalculatorFragment extends CalculatorFragment{
	
	K401kCalculatorView k401kView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		k401kView = new K401kCalculatorView(getActivity());
		
		k401kView.annualSalary.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.contribution.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.yearToRetirement.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.rateOfReturn.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.annualIncrease.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.currentSavings.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.employerMatch.seekBar.setOnSeekBarLongPressListener(this);
		k401kView.employerLimit.seekBar.setOnSeekBarLongPressListener(this);
		
		
		
		return k401kView;
	}

}
