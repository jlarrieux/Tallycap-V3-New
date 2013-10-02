package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.Models.CalculatorsModel;
import com.jeannius.tallycap.util.MySeekBarWidget;

public class K401kCalculatorView extends MyScrollViewWithDate implements OnItemSelectedListener {

	
	private Button calculate, whatIf;
	private TextView result;
	private Context context;
	private CalculatorsModel Model;
	
	
	public MySeekBarWidget annualSalary, contribution, yearToRetirement, rateOfReturn, annualIncrease, currentSavings, employerMatch, employerLimit; 
	
	
	public K401kCalculatorView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(R.layout.k401k_calculator_new, this);
		
		init();
	}
	
	
	/**
	 * This function initialize the variables
	 */
	private void init(){
		
		Model = new CalculatorsModel(context);
		
		annualSalary = (MySeekBarWidget) findViewById(R.id.k401kCalculatorAnnualSalaryMySeekBarWidget);
		contribution =(MySeekBarWidget) findViewById(R.id.k401kCalculatorContributionMySeekBarWidget);
		yearToRetirement = (MySeekBarWidget) findViewById(R.id.k401kCalculatorYearsToRetirementMySeekBarWidget);
		rateOfReturn = (MySeekBarWidget) findViewById(R.id.k401kCalculatorRateOfReturnMySeekBarWidget);
		annualIncrease = (MySeekBarWidget) findViewById(R.id.k401kCalculatorAnnualIncreaseMySeekBarWidget);
		currentSavings = (MySeekBarWidget) findViewById(R.id.k401kCalculatorCurrentSavingsMySeekBarWidget);
		employerLimit = (MySeekBarWidget) findViewById(R.id.k401kCalculatorEmployersLimitMySeekBarWidget);
		employerMatch = (MySeekBarWidget) findViewById(R.id.k401kCalculatorEmployersMatchMySeekBarWidget);
		
		calculate = (Button) findViewById(R.id.K401kCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.K401kCalculatorWhatIfButton);
		
		result = (TextView) findViewById(R.id.k401KCalculatorResultTextView);
		moreOptionSwitch = (Switch) findViewById(R.id.k401kCalculatorMoreOptionSwitch);
		moreOptionsLinearLayout = (LinearLayout) findViewById(R.id.k401kCalculatorMoreOptionLinearLayout);
		
		calculate.setOnClickListener(this);
		contribution.mSpinner.setOnItemSelectedListener(this);
		annualSalary.registerObserver(contribution);
		moreOptionSwitch.setOnCheckedChangeListener(this);
		
	}


	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onClick(View v) {

		double annualP = annualSalary.getCurrentValue();
		int d =contribution.getSpinnerSelectedPosition();
		
		double Rate = rateOfReturn.getCurrentValue();
		double contr= contribution.getCurrentValue();
		int yearsTo = (int) yearToRetirement.getCurrentValue();
		double annualinc =annualIncrease.getCurrentValue();
		double currentsav = currentSavings.getCurrentValue();
		double employerM = employerMatch.getCurrentValue();
		double employerL = employerLimit.getCurrentValue();
		
		String contributionType="";
		
		if(d==0)contributionType = getResources().getString(R.string.percent);
		else contributionType = getResources().getString(R.string.fixed);
		
		
		double j =Model.K401kCalculateTheValute(annualP, contr, yearsTo, Rate, annualinc, currentsav, employerM, employerL, contributionType);
		
		setResult(result, j);
		
		
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,	long id) {
		
		if(position==1){
			annualSalary.registerObserver(contribution);
			
		}
		else{
			contribution.setMaxAbsolute(fifty);
			annualSalary.removeObserver();
			
		}
		
		contribution.progressSetter(0, false);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
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
