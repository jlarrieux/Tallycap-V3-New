package com.jeannius.tallycap.Fragments;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
	
	private DatePickerDialog pick;
	private static MyUneditableDateEditText e;
	
	
	public static DatePickerFragment newInstance(int id){
		DatePickerFragment frag = new DatePickerFragment();
		Bundle args = new Bundle();
		
		args.putInt("id", id);
		frag.setArguments(args);
		
		return frag;
		
	}
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
			
		e = (MyUneditableDateEditText) getActivity().findViewById(getArguments().getInt("id"));
		
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
		pick = new DatePickerDialog(getActivity(), this, year, month, day);
		
		return pick;
	}
	
	

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(year, monthOfYear, dayOfMonth);
		e.dateTextView.setText(DateFormat.format("M/d/yyy", cal2));		
		
	}
	
	

}
