package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ScrollView;

public class AffordabilityCalculatorView extends ScrollView {

	public AffordabilityCalculatorView(Context context) {
		super(context);
				
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.affordability_calculator, this);
	}

}
