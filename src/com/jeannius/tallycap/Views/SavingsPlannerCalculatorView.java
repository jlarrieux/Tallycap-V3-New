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
		
//		String s="";
//		int id = v.getId();
//		if(id==R.id.SavingsPlannerCalculateButton){
//			
//			s+=goal.validate();
//			s+=length.validate();
//			if(interest.getText().toString().length()>0)s+=interest.validate();
//			
//
//			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
//			else{
//				
//				Double cur =0.0;
//				Double g = Double.valueOf(goal.getText().toString());
//				Double i = 0.0;
//				boolean interestState = false;
//				if(interest.getText().toString().length()>0){
//					i=Double.valueOf(interest.getText().toString());
//					interestState =true;
//				}
//				Integer l = Integer.valueOf(length.getText().toString());
//				if(currentSavings.getText().toString().length()>0) {
//					
//					currentSavings.setMax(g-1);
//					s+=currentSavings.validate();
//					
//				}
//				
//				if(s.length()>0)Toast.makeText(context, s, Toast.LENGTH_LONG).show();
//				else{					
//				
//					if(currentSavings.getText().toString().length()>0)cur = Double.valueOf(currentSavings.getText().toString());
//					
//					String amF = Model.PayfrequencyCalculatorInternal(amountFrequency.getSelectedItemPosition());
//					
//					String leF = Model.lengthFrequencyCalculator(lengthFrequency.getSelectedItemPosition());
//
//					double j = Model.SavingsPlannerCalculateTheValue(g, i, l, amF, leF, cur, interestState);
//					
//					result.setText(String.format(getResources().getString(R.string.savings_planner_statement), nf.format(j), amF));
//				}
//			}
//		}
		
		
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
