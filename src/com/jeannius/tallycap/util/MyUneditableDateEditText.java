package com.jeannius.tallycap.util;

import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Fragments.DatePickerFragment;

public class MyUneditableDateEditText extends LinearLayout implements View.OnClickListener{
	
	private Calendar cal;
	private TextView label;
	public EditText dateTextView;
	private Context context;
	private Global g;	
	private DatePickerFragment dateFrag;
	private FragmentManager fragMan;
	
	
	public MyUneditableDateEditText(Context context) {
		super(context);	
		this.context = context;
		if(!isInEditMode())getTodayDate();		
	}
	
	
	public MyUneditableDateEditText(Context context, AttributeSet attrs){
		super(context, attrs);
		this.context = context;
		if(!isInEditMode())getTodayDate();		
	}
	
	public MyUneditableDateEditText(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		if(!isInEditMode())getTodayDate();	
	}
	
	public void setCalendar(int year, int month, int day){
		
		cal.set(year, month, day);
		setText();
		
	}
	
	private void setText(){
		dateTextView.setText(DateFormat.format("M/d/yyy", cal));
	}
	
	
	private void getTodayDate(){
		setMinimumHeight(48);
		g = new Global(context);
		Resources res = context.getResources();
		float textSize = res.getDimension(R.dimen.text_size_medium)/res.getDisplayMetrics().density;
		
		this.setOrientation(LinearLayout.HORIZONTAL);
		 label = new TextView(context);
		 label.setText(res.getString(R.string.start_date));
		 label.setTextColor(g.textRegularColor);
		 label.setTextSize(textSize);
		 label.setPadding(0, 0, 25, 0);
		 
		 dateTextView= new EditText(context);
		 dateTextView.setTextColor(g.textRegularColor);
		 dateTextView.setTextSize(textSize);
		 dateTextView.setOnClickListener(this);
		 
		 
		 LayoutParams lay = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		 this.setMinimumWidth(400);
		 
		 addView(label, lay);
		 addView(dateTextView, lay);
		 
		cal = Calendar.getInstance();
		setText();
		dateTextView.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
		dateTextView.setInputType(InputType.TYPE_CLASS_DATETIME);
		dateTextView.setInputType(0);
		setGravity(Gravity.CENTER);
		setOnClickListener(this);
//		datePicker = new DatePickerDialog(context, dater, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
	}


	@Override
	public void onClick(View v) {
		
		
		if(dateFrag == null) dateFrag = new DatePickerFragment();
		
		if(!dateFrag.isAdded()){
			dateFrag = DatePickerFragment.newInstance(this.getId());
			dateFrag.show(fragMan, "");
		}
		
	}

	public Calendar getCalendar(){
		return cal;
	}

//NEED TO DO DATE PICKER WITH FRAGMENT:
	//http://developer.android.com/guide/topics/ui/controls/pickers.html
	//TODO

	
	public void setFragmentManager(FragmentManager frag){
		fragMan= frag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
