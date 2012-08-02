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
		return k401kView;
	}

}
