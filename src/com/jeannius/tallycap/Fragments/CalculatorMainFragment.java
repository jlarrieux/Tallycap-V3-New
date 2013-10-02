package com.jeannius.tallycap.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Fragments.CalculatorFragment.whatIfButtonSwitch;

public class CalculatorMainFragment extends SherlockFragment implements whatIfButtonSwitch{
	
	

	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root =inflater.inflate(R.layout.plain, null);	
		
		CalculatorTabFragment t = new CalculatorTabFragment();
		FragmentTransaction  ft= getChildFragmentManager().beginTransaction();
		ft.add(getId(), t, "red");
		ft.commit();
		setHasOptionsMenu(true);
		return root;
	}
	
	
	
	


	@Override
	public void currentWhatIf(Button WhatIf) {
	
		
	}

	



		
	



}
