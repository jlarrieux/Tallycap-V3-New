package com.jeannius.tallycap.Views;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.DatePicker;

public class SavingsPlannerCalculatorView extends MyScrollViewWithDate implements OnClickListener, OnDateSetListener {

	public SavingsPlannerCalculatorView(Context context) {
		super(context);
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.savings_planner_layout, this);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub

	}

}