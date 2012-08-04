package com.jeannius.tallycap.Views;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ScrollView;

import com.jeannius.tallycap.util.MyUneditableDateEditText;

public abstract class MyScrollViewWithDate extends ScrollView implements OnClickListener, OnDateSetListener{
	
	private MyUneditableDateEditText dateText;
	protected DecimalFormat nf;
	
	public MyScrollViewWithDate(Context context) {
		super(context);
		dateText = new MyUneditableDateEditText(context);
		dateText.setOnClickListener(this);
		nf = (DecimalFormat) NumberFormat.getInstance(getResources().getConfiguration().locale);
	}
	
	public MyUneditableDateEditText getMyUneditableDateEditText(){
		return dateText;
	}
	
	
	public void setMyUneditableDateEditText(MyUneditableDateEditText m){
		dateText = m;		
	}
	
	@Override
	public void onClick(View v) {
		
		
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}
	

}
