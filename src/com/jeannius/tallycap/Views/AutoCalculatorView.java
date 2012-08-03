package com.jeannius.tallycap.Views;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class AutoCalculatorView extends MyScrollViewWithDate {

	private Context context;
	private CalculatorsModel Model;
	private MyEditText amount, interest, tradeInValue, downPayment;
	private Spinner length, downSelection;
	private Button calculate, whatIf;
	public TextView result;
	private MyUneditableDateEditText unDate;
	private DatePickerDialog datePicker;
	
	public AutoCalculatorView(Context context) {
		super(context);
		 this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.auto_calculator, this);
		
		init();
	}
	
	
	private void init(){		
		
		result = new TextView(context);
		amount = new MyEditText(context);
		interest = new MyEditText(context);
		length = new Spinner(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		Model = new CalculatorsModel(context);
		unDate = new MyUneditableDateEditText(context);
		tradeInValue = new MyEditText(context);
		downPayment = new MyEditText(context);
		downSelection = new Spinner(context);
		
		
		result = (TextView) findViewById(R.id.AutoResultTextView);
		amount = (MyEditText) findViewById(R.id.AutoAmountEditText);
		interest = (MyEditText) findViewById(R.id.AutoInterestEditText);
		length = (Spinner) findViewById(R.id.AutoLengthSpinner);		
		calculate = (Button) findViewById(R.id.AutoCalculateButton);
		unDate = (MyUneditableDateEditText) findViewById(R.id.AutoDateEditText);
		tradeInValue = (MyEditText) findViewById(R.id.AutoTradeInValueMyEditText);
		downPayment = (MyEditText) findViewById(R.id.AutoDownPaymentMyEditText);
		downSelection = (Spinner) findViewById(R.id.AutoDownPaymentSelectionSpinner);
		
		Calendar al = unDate.c;
		
		datePicker = new DatePickerDialog(context, this, al.get(Calendar.YEAR), al.get(Calendar.MONTH), al.get(Calendar.DAY_OF_MONTH));
		Resources r = getResources();
		
		amount.setName(r.getString(R.string.amount));
		interest.setName(r.getString(R.string.interest_rate));
		tradeInValue.setName(r.getString(R.string.trade_in_value));
		downPayment.setName(r.getString(R.string.down_payment));
		amount.setRequired(true);
		interest.setRequired(true);
		tradeInValue.setRequired(false);
		downPayment.setRequired(false);
		interest.setMax(1000);
		
		
		length.setSelection(2);
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
		unDate.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {

		String s="";
		
		int id = v.getId();
		if(id == R.id.AutoCalculateButton){
			s+=amount.validate();
			s+= interest.validate();
			
			
			if(s.length()>0) Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
			else{
				
				Double t =0.0;
				Double d=0.0;
				Double i = Double.valueOf(interest.getText().toString());
				Double p = Double.valueOf(amount.getText().toString());
				int l = Model.numberFromStringParser(length.getSelectedItem().toString());
				tradeInValue.setMax(p);
				if(tradeInValue.getText().toString().length()>0) t = Double.valueOf(tradeInValue.getText().toString());
				
				
				switch(downSelection.getSelectedItemPosition()){
				case 0:
					downPayment.setMax(99);
					break;
				case 1:
					downPayment.setMax(p-t);
					break;
			}
				
				s+=tradeInValue.validate();
				s+=downPayment.validate();
				Log.v("AUTO DOWNPAYMENT MAX", String.valueOf(downPayment.getMax()));
				
				if(s.length()>0) Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
				else{
					
					if(downPayment.getText().toString().length()>0){
						switch(downSelection.getSelectedItemPosition()){
							case 0:
								d = p*Double.valueOf(downPayment.getText().toString())/100;
								break;
							case 1:
								d = Double.valueOf(downPayment.getText().toString());
								break;
						}
						
					}
					
					Log.v("AUTO DOWNPAYMENT Value", String.valueOf(d));
					Log.v("PAYMENT BEFORE", String.valueOf(p));
					p=p-t-d;
					
					Log.v("FINANCED AMOUNT", String.valueOf(p));
					double j = Model.AutoCalculateTheValue(i, p, l, unDate.c);
					result.setText(String.format(getResources().getString(R.string.monthly_payments_format), nf.format(j)));
				}
			}
		}
		else if(id== R.id.AutoDateEditText) datePicker.show();
		
		


		
		

	}


	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		unDate.setCalendar(year, monthOfYear, dayOfMonth);
		
	}


	


	



	
	
	
	
	
}
