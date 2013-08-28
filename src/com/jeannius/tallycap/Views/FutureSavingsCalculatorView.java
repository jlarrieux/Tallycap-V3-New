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

		
		double am = amount.getCurrentValue();
		double i = interest.getCurrentValue();
		int l = (int) length.getCurrentValue();
		
		double cur = currentSavings.getCurrentValue();
		boolean interestState =false;
		if(i>0) interestState = true;
		
		double j= Model.FutureSavingsCalculateTheValue(am, i, l, Model.PayfrequencyCalculatorInternal(amount.getSpinnerSelectedPosition()), length.getSpinnerSelectedPosition(), cur, interestState);	
		
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
