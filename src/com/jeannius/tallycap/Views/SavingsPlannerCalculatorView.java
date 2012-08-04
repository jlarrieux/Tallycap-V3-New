package com.jeannius.tallycap.Views;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SavingsPlannerCalculatorView extends MyScrollViewWithDate  {
	
	private Context context;
	private CalculatorsModel Model;
	private MyEditText goal, length, interest, currentSavings;
	private Spinner lengthFrequency, amountFrequency;
	private TextView result;
	private Button calculate, whatIf;
	

	public SavingsPlannerCalculatorView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.savings_planner_layout, this);
		
		init();
	}

	
	
	private void init(){
		
		Model = new CalculatorsModel(context);
		goal = new MyEditText(context);
		length = new MyEditText(context);
		interest = new MyEditText(context);
		currentSavings = new MyEditText(context);
		lengthFrequency = new Spinner(context);
		amountFrequency = new Spinner(context);
		result = new TextView(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		
		goal = (MyEditText) findViewById(R.id.SavingsPlannerGoalMyEditText);
		length = (MyEditText) findViewById(R.id.SavingsPlanerLengthMyEditText);
		interest = (MyEditText) findViewById(R.id.SavingPlannerInterestMyEditText);
		currentSavings = (MyEditText) findViewById(R.id.SavingsPlannerCurrentSavingsMyEditText);
		lengthFrequency = (Spinner) findViewById(R.id.SavingsPlannerLengthSpinner);
		amountFrequency = (Spinner) findViewById(R.id.SavingsPlannerAmountSpinner);
		calculate = (Button) findViewById(R.id.SavingsPlannerCalculateButton);
		whatIf = (Button) findViewById(R.id.SavingsPlannerWhatIfButton);
		result = (TextView) findViewById(R.id.SavingsPlannerResultTextView);
		
		Resources r = getResources();
		goal.setName(r.getString(R.string.goal));
		length.setName(r.getString(R.string.length));
		interest.setName(r.getString(R.string.interest_rate));
		currentSavings.setName(r.getString(R.string.current_savings));
		
		goal.setRequired(true);
		length.setRequired(true);
		interest.setRequired(true);
		
		length.setMax(1000);
		length.setMin(1);
		interest.setMax(1000);
		interest.setMin(0.0001);
		goal.setMin(1);
		
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
		
		
	}
	
	
	
	@Override
	public void onClick(View v) {
		
		String s="";
		int id = v.getId();
		if(id==R.id.SavingsPlannerCalculateButton){
			
			s+=goal.validate();
			s+=length.validate();
			s+=interest.validate();
			
//			Log.v("SAVINGS PLANNER", "Calculate started");
			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			else{
				
				Double cur =0.0;
				Double g = Double.valueOf(goal.getText().toString());
				Double i = Double.valueOf(interest.getText().toString());
				Integer l = Integer.valueOf(length.getText().toString());
				if(currentSavings.getText().toString().length()>0) {
					
					currentSavings.setMax(g-1);
					s+=currentSavings.validate();
					
				}
				
				if(s.length()>0)Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				else{					
				
					if(currentSavings.getText().toString().length()>0)cur = Double.valueOf(currentSavings.getText().toString());
					
					String amF = Model.PayfrequencyCalculatorInternal(amountFrequency.getSelectedItemPosition());
					
					String leF = Model.lengthFrequencyCalculator(lengthFrequency.getSelectedItemPosition());
	//				Log.v("SAVINGS", String.format("Af: %s, lf: %s, afspi: %d,lfspi: %d", amF, leF, amountFrequency.getSelectedItemPosition(), lengthFrequency.getSelectedItemPosition()));
	//				Log.v("SAVINGS2", String.format("Amsp: %s, Lgthsp: %s", amountFrequency.getSelectedItem().toString(), lengthFrequency.getSelectedItem().toString()));
					double j = Model.SavingsPlannerCalculateTheValue(g, i, l, amF, leF, cur);
					
					result.setText(String.format(getResources().getString(R.string.savings_planner_statement), nf.format(j), amF));
				}
			}
		}
	}
	
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	
	
	
	
	
	

}
