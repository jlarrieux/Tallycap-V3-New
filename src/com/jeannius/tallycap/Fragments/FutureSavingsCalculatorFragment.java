package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.FutureSavingsCalculatorView;

public class FutureSavingsCalculatorFragment extends CalculatorFragment {
	
	FutureSavingsCalculatorView futureSavingsView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			futureSavingsView = new FutureSavingsCalculatorView(getActivity());
		
		
		return futureSavingsView;
	}
}
