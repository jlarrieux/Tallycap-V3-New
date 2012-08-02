package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.AffordabilityCalculatorView;

public class AffordabilityCalculatorFragment extends CalculatorFragment {
	
	AffordabilityCalculatorView affordView;
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		affordView = new AffordabilityCalculatorView(getActivity());
		
		return affordView;
	}
}
