package com.jeannius.tallycap.Fragments;

import com.actionbarsherlock.app.SherlockFragment;

public abstract class CalculatorFragment extends SherlockFragment {

	protected String mText;
	public static final String RESULTTEXT ="result";
	
	
	
	public String getText(){
		return mText;
	}
	
		
	
}
