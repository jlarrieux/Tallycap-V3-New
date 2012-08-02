package com.jeannius.tallycap.Views;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.CalculatorActivity;
import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class LoanCalculatorView extends MyScrollViewWithDate implements  OnClickListener, OnDateSetListener {
	
	private Context context;	
	private MyEditText amount, interest, length;	
	private Button calculate, whatIf;
	private MyUneditableDateEditText unDate;
	private DatePickerDialog datePicker;
	private Spinner lenghtSelector;
	private CalculatorsModel Model;
	private TextView result;
	
	
	public LoanCalculatorView(Context context) {
		super(context);
		this.context = context;		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.loan_calculator, this);
		
		init();
	}
	
	private void init(){
		amount = new MyEditText(context);
		interest = new MyEditText(context);
		length = new MyEditText(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		unDate = new MyUneditableDateEditText(context);
		lenghtSelector = new Spinner(context);
		Model = new CalculatorsModel(context);
		result = new TextView(context);
		
		amount = (MyEditText) findViewById(R.id.LoanAmountMyEditText);
		length =  (MyEditText) findViewById(R.id.LoanLengthMyEditText);
		interest = (MyEditText) findViewById(R.id.LoanInterestMyEditText);
		unDate = (MyUneditableDateEditText) findViewById(R.id.LoanMyUneditableDateEditText);
		lenghtSelector = (Spinner) findViewById(R.id.LoanLengthSelectorSpinner);
		result = (TextView) findViewById(R.id.LoanResultTextView);
		calculate = (Button) findViewById(R.id.LoanCalculateButton);
		
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
		
		Resources r = getResources();
		
		amount.setName(r.getString(R.string.amount));
		length.setName(r.getString(R.string.length));
		interest.setName(r.getString(R.string.interest_rate));
		
		
		Calendar al = unDate.c ;
		datePicker = new DatePickerDialog(context, this, al.get(Calendar.YEAR), al.get(Calendar.MONTH), al.get(Calendar.DAY_OF_MONTH));
		amount.setRequired(true);
		length.setRequired(true);
		interest.setRequired(true);
		interest.setMax(1000);
		length.setMax(500);
		
		lenghtSelector.setSelection(2);
		unDate.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		
		String s="";
		int id = v.getId();
		if(id==R.id.LoanMyUneditableDateEditText)	datePicker.show();
		else if(id==R.id.LoanCalculateButton){
			
			s+=amount.validate();
			s+=interest.validate();
			s+=length.validate();
			
			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			else{
				Double i =Double.valueOf(interest.getText().toString());
				Double p =Double.valueOf(amount.getText().toString());
				Integer l =Integer.valueOf(length.getText().toString());
				String f="";
				if(lenghtSelector.getSelectedItemPosition()==0) f= CalculatorActivity.WEEKLY;
				else if(lenghtSelector.getSelectedItemPosition()==1) f= CalculatorActivity.MONTHLY;
				else if(lenghtSelector.getSelectedItemPosition()==2) f= CalculatorActivity.YEARLY;
				
				double j = Model.LoanCalculateTheValue(i, p, l, unDate.c, f);
				
				result.setText(String.format(getResources().getString(R.string.monthly_payments_format), j));
			}
			
		}
			
		
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
		unDate.setCalendar(year, monthOfYear, dayOfMonth);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
