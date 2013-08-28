package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.Global;
import com.jeannius.tallycap.util.MySeekBarWidget;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class LoanCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;	
	
	private Button calculate, whatIf;
	private Global g;
	

	private CalculatorsModel Model;
	private TextView result;
	
		
	
	//NEW LOAN LAYOUT
	public MySeekBarWidget amount, interest, length;
	public MyUneditableDateEditText unDate;
	
	
	public LoanCalculatorView(Context context) {
		super(context);
		this.context = context;		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		
		in.inflate(com.jeannius.tallycap.R.layout.loan_calculator_new, this);
		init2();

	}
	
	
	
	private void init2(){
		this.setClipChildren(false);
		setClipToPadding(false);

		Model = new CalculatorsModel(context);
		g= new Global(context);
		
		
		amount=(MySeekBarWidget) findViewById(R.id.loanCalculatorAmoutMySeekBarWidget);
		interest = (MySeekBarWidget) findViewById(R.id.loanCalculatorInterestMySeekBarWidget);
		length = (MySeekBarWidget) findViewById(R.id.loanCalculatorLengthMySeekBarWidget);
		unDate = (MyUneditableDateEditText) findViewById(R.id.LoanMyUneditableDateEditText);		
		result = (TextView) findViewById(R.id.loanCalculatorResultTextView);
		calculate = (Button) findViewById(R.id.loanCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.loanCalculatorWhatIfButton);
		
		
		calculate.setOnClickListener(this);

	}
	


	
	@Override
	public void onClick(View v) {
		

		
		Double i = Double.valueOf(interest.getCurrentValue());
		Double p = Double.valueOf(amount.getCurrentValue());
		Integer l = (int) length.getCurrentValue();
		
		g.logCat(String.format("Amount: %f  Interest: %f  length: %d   frequency: %s", p, i, l, length.getFrequency()));
		
		double j = Model.LoanCalculateTheValue(i, p, l, unDate.getCalendar(), length.getFrequency());
		setResult(result, j);
	}



	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
