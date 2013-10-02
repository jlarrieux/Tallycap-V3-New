package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;
import com.jeannius.tallycap.util.MySpinnerContainer;
import com.jeannius.tallycap.util.MyUneditableDateEditText;

public class AutoCalculatorView extends MyScrollViewWithDate {

	private Context context;
	private CalculatorsModel Model;

	private MySpinnerContainer length;
	private Button calculate;
	public TextView result;
	public MyUneditableDateEditText unDate;
	
	
	
	public MySeekBarWidget amount, interest, tradeInValue, downPayment;
	
	
	
	public AutoCalculatorView(Context context) {
		super(context);
		 this.context = context;
		
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.auto_calculator_new, this);
		
		init();
	}
	
	
	private void init(){		
			
		Model = new CalculatorsModel(context);
		amount = (MySeekBarWidget) findViewById(R.id.autoCalculatorAmountMySeekBarWidget);
		unDate = (MyUneditableDateEditText) findViewById(R.id.autoCalculatorMyUneditableDateEditText);
		interest = (MySeekBarWidget) findViewById(R.id.autoCalculatorInterestMySeekBarWidget);
		length = (MySpinnerContainer) findViewById(R.id.autoCalculatorLengthMySpinnerContainer);
		calculate = (Button) findViewById(R.id.autoCalculatorCalculateButton);
		tradeInValue = (MySeekBarWidget) findViewById(R.id.autoCalculatorTradeInValueMySeekBarWidget);
		downPayment = (MySeekBarWidget) findViewById(R.id.autoCalculatorDownPaymentMySeekBarWidget);
		result = (TextView) findViewById(R.id.autoCalculatorResultTextView);
		
		tradeInValue.current_mode = MySeekBarWidget.MAX_CODEPENDENT;
		downPayment.current_mode = MySeekBarWidget.MAX_CODEPENDENT;
		
//		amount.registerObserver(tradeInValue);
//		amount.registerObserver(downPayment);
		calculate.setOnClickListener(this);
		
		
	}

	


	@Override
	public void calculate() {
		Double i = Double.valueOf(interest.getCurrentValue());
		Double p = Double.valueOf(amount.getCurrentValue()-tradeInValue.getCurrentValue() -downPayment.getCurrentValue());
		int l = Model.numberFromStringParser(length.getSelecObject().toString());
		
		double j = Model.AutoCalculateTheValue(i, p, l, unDate.getCalendar());
		setResult(result, j);
//		g.toaster("RED", true);
		
	}


	@Override
	protected void whatif() {
		// TODO Auto-generated method stub
		
	}


	

	


	



	
	
	
	
	
}
