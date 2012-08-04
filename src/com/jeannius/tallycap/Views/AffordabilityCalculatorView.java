package com.jeannius.tallycap.Views;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;

public class AffordabilityCalculatorView extends MyScrollViewWithDate {
	
	private MyEditText amount, interestRate, length;
	private Button calculate, whatIf;
	private TextView result;
	private Context context;
	private Spinner amountFrequency;
	private CalculatorsModel Model;

	public AffordabilityCalculatorView(Context context) {
		super(context);
		this.context = context;	
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.affordability_calculator, this);
		
		init();
	}
	
	private void init(){
		
		amount = new MyEditText(context);
		interestRate = new MyEditText(context);
		length = new MyEditText(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		result = new TextView(context);
		amountFrequency = new Spinner(context);
		Model = new CalculatorsModel(context);
		
		amount = (MyEditText) findViewById(R.id.AffordabilityAmountMyEditText);
		interestRate = (MyEditText) findViewById(R.id.AffordibilityInterestMyEditText);
		length = (MyEditText) findViewById(R.id.AffordabilityLengthMyEditText);
		calculate = (Button) findViewById(R.id.AffordabilityCalculateButton);
		whatIf = (Button) findViewById(R.id.AffordabilityWhatIfButton);
		result = (TextView) findViewById(R.id.AffordabilityResultTextView);
		amountFrequency = (Spinner) findViewById(R.id.AffordabilityAmountFrequencySpinner);
		
		Resources r = getResources();
		amount.setName(r.getString(R.string.amount));
		interestRate.setName(r.getString(R.string.interest_rate));
		length.setName(r.getString(R.string.length));
		
		interestRate.setMax(1000);
		length.setMax(500);
		interestRate.setMin(0.0001);
		
		amount.setRequired(true);
		interestRate.setRequired(true);
		length.setRequired(true);
		
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
		
		
	}
	
	
	@Override
	public void onClick(View v) {
//		Model.futureValueCalculator(100, 0.1, 10);
		String s="";
		
		int id =v.getId();
		if(id == R.id.AffordabilityCalculateButton){
			
			s+= amount.validate();
			
			s+= length.validate();
			
				s+=interestRate.validate();
			
			
			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			else{
				
				Double am = Double.valueOf(amount.getText().toString());
				Integer l = Integer.valueOf(length.getText().toString());
				Double in =0.0;
				if(interestRate.getText().toString().length()>0)in = Double.valueOf(interestRate.getText().toString());
							
				String frequency = Model.PayfrequencyCalculatorInternal(amountFrequency.getSelectedItemPosition());
				
				Log.v("BEFORE AFFORD", String.format("amount: %f, interest: %f, length: %d", am, in, l));
				double j = Model.AffordabilityCalculateTheValue(am, in, l, frequency);
				
				result.setText(String.format(getResources().getString(R.string.affordability_statement), nf.format(j)));
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
