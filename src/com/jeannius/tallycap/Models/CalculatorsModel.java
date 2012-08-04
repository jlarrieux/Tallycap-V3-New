package com.jeannius.tallycap.Models;

import java.util.Calendar;

import android.content.Context;
import android.util.Log;

import com.jeannius.tallycap.CalculatorActivity;

public class CalculatorsModel extends CalculatorAbstractModel {
	
	private Context context;
	public CalculatorsModel(Context context){
		this.context = context;
	}
	//this is the function for the Auto Calculator
	public double AutoCalculateTheValue(double interest, double amount,int length,  Calendar StartDate) {

		String LengthFrequency = CalculatorActivity.MONTHLY;
		String Payfrequency = CalculatorActivity.MONTHLY;
		
		double r =this.CalculateLoanPayment(interest, amount, length, Payfrequency, StartDate, LengthFrequency);
		return r;
	}
	
	
	
	/**
	 * This is the function for the Mortgage calculator
	 * @param interest
	 * @param amount
	 * @param length
	 * @param StartDate
	 * @param YearlyTaxes
	 * @return
	 */
	public double MortgageCalculateTheValue(double interest, double amount,int length,  Calendar StartDate, double YearlyTaxes) {

		String LengthFrequency = CalculatorActivity.YEARLY;
		String Payfrequency = CalculatorActivity.MONTHLY;
		
		Log.v("MORTGAGE", String.format("Yearly taxes: %f", YearlyTaxes));
		
		double r =this.CalculateLoanPayment(interest, amount, length, Payfrequency, StartDate, LengthFrequency);
		Log.v("MORTGAGE", String.format("Payment initial: %f", r));
		
		if(YearlyTaxes!=0) r += (YearlyTaxes/12);
		Log.v("MORTGAGE", String.format("Payment afer taxes: %f", r));
		
		return r;
	}
	
	
	
	/**
	 * this is the function for the Loan Calculator
	 * @param interest: the interest rate
	 * @param amount: the amount financed
	 * @param length: the length in months
	 * @param StartDate : the start date
	 * @param frequency: the frequency of payments
	
	 */
	public double LoanCalculateTheValue(double interest, double amount, int length, Calendar StartDate, String frequency){
		
		String Payfrequency = CalculatorActivity.MONTHLY;
		
		double r = this.CalculateLoanPayment(interest, amount, length, Payfrequency, StartDate, frequency);
		
		return r;
	}
	
	
	
	public double K401kCalculateTheValute(double annualPay, double contributionAmount, int numberOfYearsBeforeRetirement, double rateOfReturnPercent, double annualIncrease, double currentSavings, double employerMatch, double employerLimit, String contributionType){
		
		double r=k401kCalculator(annualPay, contributionAmount, contributionType, numberOfYearsBeforeRetirement, rateOfReturnPercent, annualIncrease, currentSavings, employerMatch, employerLimit, context);
		
		return r;
		
	}
	
	
	//calculate P given A,i,n
	public double AffordabilityCalculateTheValue(double amount, double interest, int length, String frequency){
					
		Log.v("DURING1 AFFORD", String.format("amount: %f", amount));
		length= lengthCalculator(length, frequency, Calendar.getInstance(), CalculatorActivity.YEARLY);
		double i = interestCalculator(interest, frequency);
		
		double u =Math.pow(1+i, length);
		double top = amount *(u-1);
		double bottom = i*u;
		
		double a = top/bottom;
		
		a=Rounder(a);
//		Log.v("AFFORD", String.format("Initial amount: %f, interest: %f, length: %d, u: %f, final: %f", a,interest, length, u, a ));
		
		return a;
		
	}
	
	
	//calculate F given A,i,n
	public double FutureSavingsCalculateTheValue(double amount, double interest, int length, String amountFrequency, String lengthFrequency, double cur){
		
		length = lengthCalculator(length, amountFrequency, Calendar.getInstance(), lengthFrequency);
		interest = interestCalculator(interest, amountFrequency);
		
		
		double u = Math.pow(1+interest, length);

		double top = amount*(u-1);
		double bottom = interest;
		
		double F= top/bottom;
				
		double f1 =cur*u;
		
		F= F+ f1;

		F= Rounder(F);

		
		Log.v("FUTURE", String.format("Amount: %f , top: %f , interest: %f, calculated length: %d, aF: %s, lf: %s", amount, top, interest, length, amountFrequency, lengthFrequency));
		return  F;
		
	}
	
	
	
	//calculate A given F,i,n
	public double SavingsPlannerCalculateTheValue(double goal, double interest, int length, String amountFrequency, String lengthFrequency, double curr){
		
		length = lengthCalculator(length, amountFrequency, Calendar.getInstance(), lengthFrequency);
		interest = interestCalculator(interest, amountFrequency);
		
		
		
		double u = Math.pow(1+interest, length);
		curr = curr*u;
		goal = goal -curr;
		double top = interest*goal;
		double bottom = u-1;
		
		double A = top/bottom;
		
		A= Rounder(A);
		Log.v("SAVINGS3", String.format("interest: %f, GOAL:%f, length: %d, u: %f, top: %f, bottom: %f, Final: %f", interest,goal, length, u, top, bottom, A));
		return A;
	}
	
	
	
	
	
	
	
	public String lengthFrequencyCalculator(int pos){
		
		String frequency ="";
		
		if(pos==0) frequency = CalculatorActivity.WEEKLY;
		else if(pos==1) frequency = CalculatorActivity.MONTHLY;
		else if(pos==2) frequency = CalculatorActivity.YEARLY;	
				
		return frequency;
	}
	
	
	
	
	
	
	public String PayfrequencyCalculatorInternal(int pos){
		
		String frequency ="";
		
		if(pos==0) frequency = CalculatorActivity.WEEKLY;
		else if(pos==1) frequency = CalculatorActivity.BIWEEKLY;
		else if(pos==2) frequency = CalculatorActivity.MONTHLY;
		else if(pos==3) frequency = CalculatorActivity.YEARLY;
		
		return frequency;
	}
	
	

	
	
	
	
	
	
	
}
