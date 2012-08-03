package com.jeannius.tallycap.Views;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;

public class CreditCardCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;
	private CalculatorsModel Model;
	private MyEditText balance, interest, creditLimit, monthlyFee, annualFee, overTheLimitFee;
	private Spinner annualFeeMonth;
	private Button calculate, whatIf;
	private TextView result;
	
	
	public CreditCardCalculatorView(Context context) {
		super(context);
		this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.credit_card_calculator, this);
		
		init();
		
	}
	
	
	private void init(){
		
		Model = new CalculatorsModel(context);
		balance = new MyEditText(context);
		interest = new MyEditText(context);
		creditLimit = new MyEditText(context);
		monthlyFee = new MyEditText(context);
		annualFee = new MyEditText(context);
		overTheLimitFee = new MyEditText(context);
		annualFeeMonth = new Spinner(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		result = new TextView(context);
		
		balance = (MyEditText) findViewById(R.id.CreditCardBalanceMyEditText);
		interest = (MyEditText) findViewById(R.id.CreditCardInterestMyEditText);
		creditLimit = (MyEditText) findViewById(R.id.CreditCardCreditLimitMyEditText);
		monthlyFee = (MyEditText) findViewById(R.id.CreditCardMonthlyFeeMyEditText);
		annualFee = (MyEditText) findViewById(R.id.CreditCardAnnualFeeMyEditText);
		overTheLimitFee = (MyEditText) findViewById(R.id.CreditCardOverTheLimitFeeMyEditText);
		annualFeeMonth = (Spinner) findViewById(R.id.CreditCardAnnualFeeMonthSpinner);
		calculate = (Button) findViewById(R.id.CreditCardCalculateButton);
		whatIf = (Button) findViewById(R.id.CreditCardWhatIfButton);
		result=(TextView) findViewById(R.id.CreditCardResultTextView);
		
		
		Resources r= getResources();
		
		balance.setName(r.getString(R.string.balance));
		interest.setName(r.getString(R.string.interest_rate));
		creditLimit.setName(r.getString(R.string.credit_limit));
		monthlyFee.setName(r.getString(R.string.monthly_fee));
		annualFee.setName(r.getString(R.string.annual_fee));
		overTheLimitFee.setName(r.getString(R.string.over_the_limit_fee));
		
		
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);		
		
		
		balance.setRequired(true);
		interest.setRequired(true);
		interest.setMax(1000);
		//test
	}


	@Override
	public void onClick(View v) {
		String s="";
		
		
		int id = v.getId();
		
		if(id==R.id.CreditCardCalculateButton){
			s+=balance.validate();
			s+=interest.validate();
			
			
			if(s.length()>0) Toast.makeText(context, s,Toast.LENGTH_LONG).show();
			else{
				
				Double bal = Double.valueOf(balance.getText().toString()); 
				Double apr = Double.valueOf(interest.getText().toString()); 
				
				Double monthlyf =0.0;
				Double overthelimitf =0.0;
				Double creditl =0.0;
				
				if(monthlyFee.getText().toString().length()>0) monthlyf = Double.valueOf(monthlyFee.getText().toString());
				if(overTheLimitFee.getText().toString().length()>0) overthelimitf = Double.valueOf(overTheLimitFee.getText().toString());
				if(creditLimit.getText().toString().length()>0) creditl = Double.valueOf(creditLimit.getText().toString());
				
				double j = Model.creditCardMinimumPaymentCalculator(bal, apr, monthlyf, overthelimitf, creditl);
				result.setText(String.format(getResources().getString(R.string.monthly_payments_format), nf.format(j)));
			}
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	

	
}
