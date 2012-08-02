package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ScrollView;

public class K401kCalculatorView extends ScrollView{

	public K401kCalculatorView(Context context) {
		super(context);
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.k401k_calculator, this);
	}

}
