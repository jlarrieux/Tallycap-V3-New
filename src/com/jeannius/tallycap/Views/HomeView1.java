package com.jeannius.tallycap.Views;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.MyAbstractActivity;
import com.jeannius.tallycap.util.ObserverOfSubject;
import com.jeannius.tallycap.util.SubjectToObservers;

public class HomeView1 extends RelativeLayout implements SubjectToObservers, OnClickListener {
	
	private Context context;
	private ObserverOfSubject obs;
	private String state="";
	private Button Reminder, Calculator, Account, BudgetPad;
	
	public HomeView1(Context context) {
		super(context);
		
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(R.layout.activity_main, this);
		init();
	}
	
	//this function initialize the different widgets
	private void init(){
		
		Reminder = new Button(context);
		Calculator = new Button(context);
		Account = new Button(context);
		BudgetPad = new Button(context);
		
		Reminder = (Button) findViewById(R.id.HomeReminderButton);
		Account = (Button) findViewById(R.id.HomeAccountButton);
		Calculator = (Button) findViewById(R.id.HomeCalculatorButton);
		BudgetPad = (Button) findViewById(R.id.HomeBudgetPadButton);
		
		Reminder.setOnClickListener(this);
		Calculator.setOnClickListener(this);
		Account.setOnClickListener(this);
		BudgetPad.setOnClickListener(this);
		
	}
	
//this function register the observers
	@Override
	public void registerObserver(ObserverOfSubject obs) {
		this.obs = obs;
		
	}

	//this function remove the observers
	@Override
	public void removeObserver() {
		this.obs =null;
		
	}

	@Override
	public void notifyObserver() {
		obs.update(state);
		
	}


	@Override
	public void onClick(View v) {
		Button clicked = new Button(context);
		clicked = (Button) v;
	
		if(clicked.getText().toString().contains(MyAbstractActivity.CALCULATOR)) state = MyAbstractActivity.CALCULATOR;
		notifyObserver();
		
		
		
	}

}
