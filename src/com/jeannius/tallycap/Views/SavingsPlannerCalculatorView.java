package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;
import com.jeannius.tallycap.util.MySpinnerContainer;

public class SavingsPlannerCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;
	private CalculatorsModel Model;
	private TextView result;
	private Button calculate, whatIf;
	
	public MySeekBarWidget goal, length, interestRate, currentSavings;
	private MySpinnerContainer savingsFrequency;
	

	public SavingsPlannerCalculatorView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.savings_planner_layout_new, this);
		
		init();
	}

	
	
	private void init(){
		
		Model = new CalculatorsModel(context);
		
		goal = (MySeekBarWidget) findViewById(R.id.savingsPlannerCalculatorGoalMySeekBarWidget);
		length = (MySeekBarWidget) findViewById(R.id.savingsPlannerCalculatorLengthMySeekBarWidget);
		interestRate = (MySeekBarWidget) findViewById(R.id.savingsPlannerCalculatorIntertestRateMySeekBarWidget);
		currentSavings = (MySeekBarWidget) findViewById(R.id.savingsPlannerCalculatorCurrentSavingsMySeekBarWidget);
		
		result =(TextView) findViewById(R.id.savingsPlannerCalculatorResultTextView);
		
		savingsFrequency = (MySpinnerContainer) findViewById(R.id.savingsPlannerCalculatorFrequencyMySpinnerContainer);
		calculate = (Button) findViewById(R.id.savingsPlannerCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.savingsPlannerCalculatorWhatIfButton);
		
		goal.registerObserver(currentSavings);
		
		calculate.setOnClickListener(this);
		
	}
	
	
	
	@Override
	public void onClick(View v) {
				
		double curr = currentSavings.getCurrentValue();
		double g = goal.getCurrentValue();
		double i = interestRate.getCurrentValue();
		int l = (int) length.getCurrentValue();
		
		boolean interestState =false;
		if(i>0) interestState =true;
		
		String amF = Model.PayfrequencyCalculatorInternal(savingsFrequency.getSelectChildPosition());
		String leF= Model.lengthFrequencyCalculator(length.getSpinnerSelectedPosition());
		
		
		double j =Model.SavingsPlannerCalculateTheValue(g, i, l, amF, leF, curr, interestState);
		
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
