package com.jeannius.tallycap.util;

import java.util.Calendar;

import android.content.Context;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyUneditableDateEditText extends EditText{
	
	public Calendar c;
	
	public MyUneditableDateEditText(Context context) {
		super(context);		
		getTodayDate();
		init();
	}
	
	
	public MyUneditableDateEditText(Context context, AttributeSet attrs){
		super(context, attrs);		
		getTodayDate();
		init();

	}
	
	private void init(){
		setInputType(0);
	}
	
	public void setCalendar(int year, int month, int day){
		
		c.set(year, month, day);
		setText();
		
	}
	
	private void setText(){
		this.setText(DateFormat.format("M/d/yyy", c));
	}
	
	
	private void getTodayDate(){
		
		c = Calendar.getInstance();
		setText();
		setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		this.setInputType(InputType.TYPE_CLASS_NUMBER);
	}


	
}
