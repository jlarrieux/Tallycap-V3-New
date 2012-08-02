package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeannius.tallycap.Views.CreditCardCalculatorView;

public class CreditCardCalculatorFragment extends CalculatorFragment {

	CreditCardCalculatorView creditView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		creditView = new CreditCardCalculatorView(getActivity());	
		return creditView;
	}
}
