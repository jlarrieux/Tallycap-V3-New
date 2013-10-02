package com.jeannius.tallycap.Views;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
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
	private CheckBox PMI;
	private CalculatorsModel Model;
	
	public MySeekBarWidget amount, interest, yearlyTaxes, downPayment, HOA, HomeownersInsurance;
	private MySpinnerContainer length;
	public int InitialValuePercent =500;
	public int initialValueAmount =100;
	
	
	
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
		HomeownersInsurance= (MySeekBarWidget) findViewById(R.id.mortgageCalculatorHomeOwnersInsurance);
		HOA =(MySeekBarWidget) findViewById(R.id.mortgageCalculatorHomeOwnersAssociation);
		PMI = (CheckBox) findViewById(R.id.mortgageCalculatorPMICheckBox);
		
		
		calculate = (Button) findViewById(R.id.mortgageCalculatorCalculateButton);
		whatIf = (Button) findViewById(R.id.mortgageCalculatorWhatIfButton);
		result = (TextView) findViewById(R.id.mortgageCalculatorResultTextView);
		moreOptionSwitch = (Switch) findViewById(R.id.mortgageCalculatorOptionSwitch);
		moreOptionsLinearLayout = (LinearLayout) findViewById(R.id.mortgageCalculatorOptionLinearLayout); 
		
		calculate.setOnClickListener(this);
		Model = new CalculatorsModel(context);
		
		
		yearlyTaxes.mSpinner.setOnItemSelectedListener(this);
		downPayment.mSpinner.setOnItemSelectedListener(this);
		moreOptionSwitch.setOnCheckedChangeListener(this);
		
		addobserver();
		
		
	}


	

	
	@Override
	public void onClick(View v) {

		Resources r =getResources();

		if(((Button)v).getText().toString().equals(r.getString(R.string.calculate))) calculate();
		
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,	long id) {
	
		MySeekBarWidget m =getMySeekBarWidgetParentFromSpinner(parent);
		int p =0;
		
		if(position==1) p =m.getInitialValueAmount();
		else if(position==0){
			
			m.setMaxAbsolute(fifty);

			p= m.getInitialValuePercent();
		}

		m.progressSetter(p, false);
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}


	
	private MySeekBarWidget getMySeekBarWidgetParentFromSpinner(AdapterView<?> parent){
		
			
		return		 (MySeekBarWidget) parent.getParent().getParent().getParent();
		
		
	}


	
	//this function does the calculation
	@Override
	public void calculate() {
		double p = amount.getCurrentValue();
		double i=interest.getCurrentValue();
		int l =Model.numberFromStringParser(length.getSelecObject().toString());
		double y= yearlyTaxes.getCurrentValue();
		if(yearlyTaxes.getSpinnerSelectedPosition()==0) y=amount.getCurrentValue() *yearlyTaxes.getCurrentValue()/100;
		double down = downPayment.getCurrentValue();
		if(downPayment.getSpinnerSelectedPosition()==0) down = amount.getCurrentValue() * downPayment.getCurrentValue()/100;
		
		p -= down;
		double j = Model.MortgageCalculateTheValue(i, p, l, unDate.getCalendar());
		double virginJ= j;
		
		
//		j+=(y/12);
		
		double monthlyHOI = HomeownersInsurance.getCurrentValue()/12;
		double monthlyTax = y/12;
		double asso = HOA.getCurrentValue();
		
		
		
		double pmi=0;
		
		if(PMI.isChecked() && downPayment.getCurrentValue()<20){
			
			pmi= (p*0.0080/12);
			
			p+=pmi;

		}
		
		j += (monthlyHOI+monthlyTax+asso+pmi);
		
		g.logCat(String.format("Virgin J: %f, property taxes: %f,  insurance: %f  pmi: %f,  HOA: %f", virginJ,monthlyTax, monthlyHOI, pmi, asso));
		setResult(result, j);
		
		
	}


	
	
	
	@Override
	protected void whatif() {
		// TODO Auto-generated method stub
		
	}



	public static double updateValue(double principal) {
		
		return principal*5.75/1000;
	}

	
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {		
		super.onCheckedChanged(buttonView, isChecked);
		

		
		
		if(amount.getObserverCount()==0)addobserver();

		
	}
	


	
	
	
	private void addobserver(){
		HomeownersInsurance.current_mode=MySeekBarWidget.VALUE_DEPENDENT;		
		amount.registerObserver(HomeownersInsurance);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
