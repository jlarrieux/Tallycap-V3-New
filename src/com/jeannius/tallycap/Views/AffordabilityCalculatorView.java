package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class AffordabilityCalculatorView extends MyScrollViewWithDate {
	

	private Button calculate, whatIf;
	private TextView result;
	private Context context;
	private CalculatorsModel Model;
	
	public MySeekBarWidget amount, interest, length;

	public AffordabilityCalculatorView(Context context) {
		super(context);
		this.context = context;	
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.affordability_calculator_new, this);
		
		init();
	}
	
	private void init(){
			
		Model = new CalculatorsModel(context);
		amount = (MySeekBarWidget) findViewById(R.id.affordabilityCalculatorAmountMySeekBarWidget);
		interest =(MySeekBarWidget) findViewById(R.id.affordabilityCalculatorInterestMySeekBarWidget);
		length = (MySeekBarWidget) findViewById(R.id.affordabilityCalculatorLengthMySeekBarWidget);
		result = (TextView) findViewById(R.id.affordabilityCalculatorResultTextView);
		
		calculate =(Button) findViewById(R.id.affordabilityCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.affordabilityCalculatorWhatIfButton);
		
		calculate.setOnClickListener(this);
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		
		double p = Double.valueOf(amount.getCurrentValue());
//		g.toaster(String.format("Raw i: %f", interest.getCurrentValue()), true);
		double i= Double.valueOf(interest.getCurrentValue());
		Integer l = (int) length.getCurrentValue();
		
//		g.logCat(String.format("Amount: %f  , interest: %f, length: %d, frequency : %d",p, i, l, amount.getSpinnerSelectedPosition()));
		double j = Model.AffordabilityCalculateTheValue(p, i, l, Model.PayfrequencyCalculatorInternal(amount.getSpinnerSelectedPosition()), length.getSpinnerSelectedPosition());
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
