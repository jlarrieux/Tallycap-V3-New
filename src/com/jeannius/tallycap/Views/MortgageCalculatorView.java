package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;
import com.jeannius.tallycap.util.MySpinnerContainer;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class MortgageCalculatorView extends MyScrollViewWithDate implements  OnItemSelectedListener{
	
	private Context context;	

	private Button calculate, whatIf;
	private TextView result;
	public MyUneditableDateEditText unDate;

	private CalculatorsModel Model;
	
	public MySeekBarWidget amount, interest, yearlyTaxes, downPayment;
	private MySpinnerContainer length;
	
	
	
	public MortgageCalculatorView(Context context) {
		super(context);
		this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.mortgage_calculator_new, this);
		
		init();
	}

	
	private void init(){
		
		amount = (MySeekBarWidget) findViewById(R.id.mortgageCalculatorAmountMyseekBarWidget);
		interest = (MySeekBarWidget) findViewById(R.id.mortgageCalculatorIntertestMyseekBarWidget);
		length = (MySpinnerContainer) findViewById(R.id.mortgageCalculatorLengthSpinnerContainer);
		
		unDate = (MyUneditableDateEditText) findViewById(R.id.mortgageCalculatorStartDateMyUneditableDateEditText);
		
		yearlyTaxes = (MySeekBarWidget) findViewById(R.id.mortgageCalculatorYearlyTaxesmMySeekBarWidget);
		downPayment = (MySeekBarWidget) findViewById(R.id.mortgageCalculatorDownPaymentMySeekBarWidget);
		
		calculate = (Button) findViewById(R.id.mortgageCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.mortgageCalculatorWhatIfButton);
		result = (TextView) findViewById(R.id.mortgageCalculatorResultTextView);
		
		calculate.setOnClickListener(this);
		Model = new CalculatorsModel(context);
		
		
		yearlyTaxes.mSpinner.setOnItemSelectedListener(this);
		downPayment.mSpinner.setOnItemSelectedListener(this);
	}


	

	
	@Override
	public void onClick(View v) {

		
		double p = amount.getCurrentValue();
		double i=interest.getCurrentValue();
		int l =Model.numberFromStringParser(length.getSelecObject().toString());
		double y= yearlyTaxes.getCurrentValue();
		if(yearlyTaxes.getSpinnerSelectedPosition()==0) y=amount.getCurrentValue() *yearlyTaxes.getCurrentValue()/100;
		double down = downPayment.getCurrentValue();
		if(downPayment.getSpinnerSelectedPosition()==0) down = amount.getCurrentValue() * downPayment.getCurrentValue()/100;
		
		p -= down;
		double j = Model.MortgageCalculateTheValue(i, p, l, unDate.getCalendar(), y);
		
		setResult(result, j);
		
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,	long id) {
		
		
//		g.toaster(parent.getParent().getParent().getParent().getClass().toString(), true);
		
		MySeekBarWidget m =getMySeekBarWidgetParentFromSpinner(parent);
		
		if(position==1);
		else{
			
			m.setMaxAbsolute(fifty);
			amount.removeObserver();
			
		}
		
		m.progressSetter(0, false);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


	
	private MySeekBarWidget getMySeekBarWidgetParentFromSpinner(AdapterView<?> parent){
		
			
		return		 (MySeekBarWidget) parent.getParent().getParent().getParent();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
