package com.jeannius.tallycap.Views;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class MortgageCalculatorView extends MyScrollViewWithDate implements  OnClickListener, OnDateSetListener{
	
	private Context context;	
	private MyEditText amount, interest, yearlyTaxes, downPayment;
	private Spinner length, downSelector;
	private Button calculate, whatIf;
	private TextView result;
	private MyUneditableDateEditText unDate;
	private DatePickerDialog datePicker;
	private CalculatorsModel Model;
	
	public MortgageCalculatorView(Context context) {
		super(context);
		this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.mortgage_calculator, this);
		
		init();
	}

	
	private void init(){
		amount = new MyEditText(context);
		interest = new MyEditText(context);
		downPayment = new MyEditText(context);
		yearlyTaxes = new MyEditText(context);
		length = new Spinner(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		result = new TextView(context);
		Model = new CalculatorsModel(context);
		unDate = new MyUneditableDateEditText(context);
		downSelector = new Spinner(context);
		
		amount = (MyEditText) findViewById(R.id.MortgageAmountEditText);
		length = (Spinner) findViewById(R.id.MortgageLengthSpinner);
		interest = (MyEditText) findViewById(R.id.MortgageInterestMyEditText);
		yearlyTaxes = (MyEditText) findViewById(R.id.MortgageYearlyTaxesMyEditText);
		downPayment = (MyEditText) findViewById(R.id.MortgageDownPaymentMyEditText);
		calculate = (Button) findViewById(R.id.MortgageCalculateButton);
		result = (TextView) findViewById(R.id.MortgageResultTextView);
		unDate = (MyUneditableDateEditText) findViewById(R.id.MortgageDateEditText);
		downSelector = (Spinner) findViewById(R.id.MortgageDownPaymentSelectorSpinner);
		Resources r = getResources();
		
		amount.setName(r.getString(R.string.amount));
		interest.setName(r.getString(R.string.interest_rate));
		yearlyTaxes.setName(r.getString(R.string.yearly_taxes));
		downPayment.setName(r.getString(R.string.down_payment));
		
		Calendar al = unDate.c;
		
		datePicker = new DatePickerDialog(context, this, al.get(Calendar.YEAR), al.get(Calendar.MONTH), al.get(Calendar.DAY_OF_MONTH));
		amount.setRequired(true);
		interest.setRequired(true);
		interest.setMax(1000);
		yearlyTaxes.setRequired(false);
		downPayment.setRequired(false);
		
		length.setSelection(2);
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
		unDate.setOnClickListener(this);
		
		
	}


	

	
	@Override
	public void onClick(View v) {
		
		String s="";
		

		
		int id = v.getId();
		if(id == R.id.MortgageCalculateButton){
			

			s+=amount.validate();
			s+=interest.validate();

			
			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			else {
				
				Double y=0.0;
				Double down=0.0;
				
				
				Double i = Double.valueOf(interest.getText().toString());
				Double p =Double.valueOf(amount.getText().toString());
				int l = Model.numberFromStringParser(length.getSelectedItem().toString());
								
				yearlyTaxes.setMax(p*.99);
				
				
				switch(downSelector.getSelectedItemPosition()){
					case 0:
						downPayment.setMax(99);
						break;
						
					case 1:
						downPayment.setMax(p-1);
						break;
				}
				s+= yearlyTaxes.validate();
				s+= downPayment.validate();
				
				if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				else{								
				
					if(yearlyTaxes.getText().toString().length()>0) y =Double.valueOf(yearlyTaxes.getText().toString());
					if(downPayment.getText().toString().length()>0){
						switch(downSelector.getSelectedItemPosition()){
							case 0:
								down = p* Double.valueOf(downPayment.getText().toString())/100;
								break;
								
							case 1:
								down = Double.valueOf(downPayment.getText().toString());
								break;
						}
					}
					Log.v("DOWN PAYMENT", String.valueOf(down));
					Log.v("PRINCIPAL", String.valueOf(p));
					p = p-down;
					Log.v("AMOUNT FINANCED", String.valueOf(p));
					double j = Model.MortgageCalculateTheValue(i, p, l, unDate.c, y);
					result.setText(String.format(getResources().getString(R.string.monthly_mortgage_payments_format), j));
					
				}
			}
			
		}
		else if(id==R.id.MortgageDateEditText){
			Log.v("MORTGAGE", "Date picker should show");
			datePicker.show();
		}
		
	}


	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
		
		unDate.setCalendar(year, monthOfYear, dayOfMonth);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
