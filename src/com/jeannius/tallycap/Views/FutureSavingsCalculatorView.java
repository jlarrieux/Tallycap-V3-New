package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class FutureSavingsCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;
	private CalculatorsModel Model;
//	private MyEditText amount, interest, length, currentSavings;
//	private Spinner amountSpinner, lengthSpinner;
	private Button calculate, whatIf;
	private TextView result;
	
	public MySeekBarWidget amount, interest, length, currentSavings;
	

	public FutureSavingsCalculatorView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.future_savings_new, this);
		
		init();
	}
	
	
	
	private void init(){
		
//		amount = new MyEditText(context);
//		interest = new MyEditText(context);
//		length = new MyEditText(context);
//		currentSavings = new MyEditText(context);
//		Model = new CalculatorsModel(context);
//		amountSpinner = new Spinner(context);
//		lengthSpinner = new Spinner(context);
//		calculate = new Button(context);
//		whatIf = new Button(context);
//		result = new TextView(context);
//		
//		
//		amount = (MyEditText) findViewById(R.id.FutureSavingsAmountEditText);
//		interest = (MyEditText) findViewById(R.id.FutureSavingsInterestMyEditText);
//		length = (MyEditText) findViewById(R.id.FutureSavingsLengthMyEditText);
//		currentSavings= (MyEditText) findViewById(R.id.FutureSavingsCurrentSavingsMyEditText);
//		amountSpinner = (Spinner) findViewById(R.id.FutureSavingsAmountSpinner);
//		lengthSpinner = (Spinner) findViewById(R.id.FutureSavingsLengthSpinner);
//		calculate = (Button) findViewById(R.id.FutureSavingsCalculateButton);
//		whatIf = (Button) findViewById(R.id.FutureSavingsWhatIfButton);
//		result = (TextView) findViewById(R.id.FutureSavingsResultTextView);
//		
//		Resources r = getResources();
//		amount.setName(r.getString(R.string.amount));
//		interest.setName(r.getString(R.string.interest_rate));
//		length.setName(r.getString(R.string.length));
//		currentSavings.setName(r.getString(R.string.current_savings));
//		
//		amount.setRequired(true);
//		interest.setRequired(false);
//		length.setRequired(true);
//		
//		interest.setMax(1000);
//
//		length.setMax(1000);
//		length.setMin(1);
//		
//		calculate.setOnClickListener(this);
//		whatIf.setOnClickListener(this);
		
		amount = (MySeekBarWidget) findViewById(R.id.futureSavingsCalculatorAmountMySeekBarWidget);
		interest = (MySeekBarWidget) findViewById(R.id.futureSavingsCalculatorInterestMySeekBarWidget);
		length = (MySeekBarWidget) findViewById(R.id.futureSavingsCalculatorLengthMySeekBarWidget);
		currentSavings = (MySeekBarWidget) findViewById(R.id.futureSavingsCalculatorCurrentSavingsMySeekBarWidget);
		
		result = (TextView) findViewById(R.id.futureSavingsCalculatorResultTextView);
		calculate = (Button) findViewById(R.id.futureSavingsCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.futureSavingsCalculatorWhatIfButton);
		
		Model = new CalculatorsModel(context);
		
		calculate.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
//		String s="";
//		
//		int id= v.getId();
//		
//		if(id==R.id.FutureSavingsCalculateButton){
//			
//			s+=amount.validate();
//			if(interest.getText().toString().length()>0) s+=interest.validate();
//			
//			s+=length.validate();
//			
//			
//			if(currentSavings.getText().toString().length()>0)	s+=currentSavings.validate();
//				
//			
//			
//			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
//			else{
//				
//				Double cur =0.0;
//				if(currentSavings.getText().toString().length()>0)
//					cur = Double.valueOf(currentSavings.getText().toString());
//				Double am = Double.valueOf(amount.getText().toString());
//				
//				boolean interestState =false;
//				Double in = 0.0;
//				if(interest.getText().toString().length()>0){
//					in =Double.valueOf(interest.getText().toString());
//					interestState =true;
//				}
//				Integer l = Integer.valueOf(length.getText().toString());
//				
//				String amountFrequency=Model.PayfrequencyCalculatorInternal(amountSpinner.getSelectedItemPosition());
//				String lengthFrequency=Model.lengthFrequencyCalculator(lengthSpinner.getSelectedItemPosition());
//				
//				
//				double j = Model.FutureSavingsCalculateTheValue(am, in, l, amountFrequency, lengthFrequency, cur, interestState);
//				
//				result.setText(String.format(getResources().getString(R.string.future_savings_statement), nf.format(j)));
//			}
//			
//			
//		}
		
		
		double am = amount.getCurrentValue();
		double i = interest.getCurrentValue();
		int l = (int) length.getCurrentValue();
		
		double cur = currentSavings.getCurrentValue();
		boolean interestState =false;
		if(i>0) interestState = true;
		
		double j= Model.FutureSavingsCalculateTheValue(am, i, l, Model.PayfrequencyCalculatorInternal(amount.getSpinnerSelectedPosition()), length.getSpinnerSelectedPosition(), cur, interestState);	
		
		setResult(result, j);
		
		
		
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
