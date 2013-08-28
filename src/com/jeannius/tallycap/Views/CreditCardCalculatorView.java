package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class CreditCardCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;
	private CalculatorsModel Model;
//	private MyEditText balance, interest, creditLimit, monthlyFee, annualFee, overTheLimitFee;
//	private Spinner annualFeeMonth;
	private Button calculate, whatIf;
	private TextView result;
	
	public MySeekBarWidget balance, interest, creditLimit, monthlyFee, annualFee, overTheLimitFee;
	
	
	public CreditCardCalculatorView(Context context) {
		super(context);
		this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.credit_card_calculator_new, this);
		
		init();
		
	}
	
	
	private void init(){
		
		balance = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorBalanceMySeekBarWidget);
		interest = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorIntertestMySeetBarWidget);
		creditLimit = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorCreditLimitMySeekBarWidget);
		monthlyFee = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorMonthlyFeeMySeekBarWidget);
		annualFee = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorAnnualFeeMySeekBarWidget);
		overTheLimitFee = (MySeekBarWidget) findViewById(R.id.creditCardCalculatorOverTheLimitFeeMySeekBarWidget);
		
		result = (TextView) findViewById(R.id.creditCardCalculatorResultTextView);
		calculate = (Button) findViewById(R.id.creditCardCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.creditCardCalculatorWhatIfButton);
		
		Model = new CalculatorsModel(context);
		
		calculate.setOnClickListener(this);
		
		
		
		
		
		
	}


	@Override
	public void onClick(View v) {
		
		
//		String s="";		
//		
//		int id = v.getId();
//		
//		if(id==R.id.CreditCardCalculateButton){
//			s+=balance.validate();
//			s+=interest.validate();
//			
//			
//			if(s.length()>0) Toast.makeText(context, s,Toast.LENGTH_LONG).show();
//			else{
//				
//				Double bal = Double.valueOf(balance.getText().toString()); 
//				Double apr = Double.valueOf(interest.getText().toString()); 
//				
//				Double monthlyf =0.0;
//				Double overthelimitf =0.0;
//				Double creditl =0.0;
//				
//				if(monthlyFee.getText().toString().length()>0) monthlyf = Double.valueOf(monthlyFee.getText().toString());
//				if(overTheLimitFee.getText().toString().length()>0) overthelimitf = Double.valueOf(overTheLimitFee.getText().toString());
//				if(creditLimit.getText().toString().length()>0) creditl = Double.valueOf(creditLimit.getText().toString());
//				
//				double j = Model.creditCardMinimumPaymentCalculator(bal, apr, monthlyf, overthelimitf, creditl);
//				result.setText(String.format(getResources().getString(R.string.monthly_payments_format), nf.format(j)));
//			}
//			
//		}
		
		double bal = balance.getCurrentValue();
		double i = interest.getCurrentValue();
		
		double creditL = creditLimit.getCurrentValue();
		double monthlyF= monthlyFee.getCurrentValue();
		double annualF = annualFee.getCurrentValue();
		double overtheLimitF = overTheLimitFee.getCurrentValue();
		
		double j = Model.creditCardMinimumPaymentCalculator(bal, i, monthlyF, overtheLimitF, creditL);
		setResult(result, j);
		
		
		
	}


	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void whatif() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	

	
}
