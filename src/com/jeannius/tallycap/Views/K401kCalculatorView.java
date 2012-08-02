package com.jeannius.tallycap.Views;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MyEditText;

public class K401kCalculatorView extends MyScrollViewWithDate implements OnClickListener{
	
	private MyEditText annualSalary, contribution, yearsToRetirement, rateOfReturn, annualIncrease,
	currentSavings, employerMatch, employerLimit;
	private Spinner contributionSelectionSpinner;
	private Button calculate, whatIf;
	private TextView result;
	private Context context;
	private CalculatorsModel Model;
	
	public K401kCalculatorView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(R.layout.k401k_calculator, this);
		
		init();
	}
	
	
	/**
	 * This function initialize the variables
	 */
	private void init(){
		
		annualSalary = new MyEditText(context);
		contribution  = new MyEditText(context);
		yearsToRetirement = new MyEditText(context);
		rateOfReturn = new MyEditText(context);
		annualIncrease = new MyEditText(context);
		currentSavings = new MyEditText(context);
		employerMatch = new MyEditText(context);
		employerLimit = new MyEditText(context);
		contributionSelectionSpinner = new Spinner(context);
		calculate = new Button(context);
		whatIf = new Button(context);
		result = new TextView(context);
		Model = new CalculatorsModel(context);
		
		
		annualSalary = (MyEditText) findViewById(R.id.K401kAnnualSalaryMyEditText);
		contribution  = (MyEditText) findViewById(R.id.K401kContributionMyEditText);
		yearsToRetirement  = (MyEditText) findViewById(R.id.K401kYearsToRetirementMyEditText);
		rateOfReturn  = (MyEditText) findViewById(R.id.K401kRateOfReturnMyEditText);
		annualIncrease  = (MyEditText) findViewById(R.id.K401kAnnualIncreasrMyEditText);
		currentSavings  = (MyEditText) findViewById(R.id.K401kCurrentSavingsMyEditText);
		employerMatch  = (MyEditText) findViewById(R.id.K401kEmployerMatchMyEditText);
		employerLimit  = (MyEditText) findViewById(R.id.K401kEmployerLimitMyEditText);
		contributionSelectionSpinner  = (Spinner) findViewById(R.id.K401kContributionSpinner);
		calculate  = (Button) findViewById(R.id.K401kCalculateButton);
		whatIf  = (Button) findViewById(R.id.K401kWhatIfButton);
		result  = (TextView) findViewById(R.id.K401kResultTextView);
		Resources r = getResources();
		
		annualSalary.setName(r.getString(R.string.annual_salary));
		contribution.setName(r.getString(R.string.contribution));
		yearsToRetirement.setName(r.getString(R.string.years_to_retirement));
		rateOfReturn.setName(r.getString(R.string.rate_of_return));
		annualIncrease.setName(r.getString(R.string.annual_increase));
		currentSavings.setName(r.getString(R.string.current_savings));
		employerMatch.setName(r.getString(R.string.employer_match));
		employerLimit.setName(r.getString(R.string.employer_limit));
		
		annualSalary.setRequired(true);
		contribution.setRequired(true);
		yearsToRetirement.setRequired(true);
		rateOfReturn.setRequired(true);
		
		
		yearsToRetirement.setMax(100);
		rateOfReturn.setMax(100);
		annualIncrease.setMax(1000);
		employerMatch.setMax(1000);
		employerLimit.setMax(1000);
		
		calculate.setOnClickListener(this);
		whatIf.setOnClickListener(this);
	}


	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onClick(View v) {
		String s="";
		
		int id = v.getId();
		
		if(id==R.id.K401kCalculateButton){
			
			s+=annualSalary.validate();
			s+=contribution.validate();
			s+=yearsToRetirement.validate();
			s+=rateOfReturn.validate();
			
			if(s.length()>0) Toast.makeText(context, s, Toast.LENGTH_LONG).show();
			else{
				
				String contributionType="";
				Double annualPay = Double.valueOf(annualSalary.getText().toString());
				int d =contributionSelectionSpinner.getSelectedItemPosition();
				if(d==0){
					contribution.setMax(99);
					contributionType = getResources().getString(R.string.percent);
				}
				else{
					contribution.setMax(0.99*annualPay);
					contributionType = getResources().getString(R.string.fixed);
				}
				
				s+=contribution.validate();
				
				if(s.length()>0)Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				else {
					Integer numberOfYearsBeforeRetirement = Integer.valueOf(yearsToRetirement.getText().toString());
					Double rateOfReturnPercent = Double.valueOf(rateOfReturn.getText().toString());
					Double contributionAmount = Double.valueOf(contribution.getText().toString());
					Double annualIncr =0.0;
					Double currentSav =0.0;
					Double employerM =0.0;
					Double employerL =0.0;
					
					if(annualIncrease.getText().toString().length()>0) annualIncr = Double.valueOf(annualIncrease.getText().toString());
					if(currentSavings.getText().toString().length()>0) currentSav = Double.valueOf(currentSavings.getText().toString());
					if(employerLimit.getText().toString().length()>0) employerL = Double.valueOf(employerLimit.getText().toString());
					if(employerMatch.getText().toString().length()>0) employerM = Double.valueOf(employerMatch.getText().toString());
					
					
					double j = Model.K401kCalculateTheValute(annualPay, contributionAmount, numberOfYearsBeforeRetirement, rateOfReturnPercent, annualIncr, currentSav, employerM, employerL, contributionType);
					
					result.setText(String.format(getResources().getString(R.string.k401k_final_value), nf.format(j)));
					
				}
				
				
				
			}
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
