package com.jeannius.tallycap.Models;

import java.lang.reflect.Array;
import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.jeannius.tallycap.CalculatorActivity;
import com.jeannius.tallycap.R;

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
	
	
	public double AffordabilityCalculateTheValue(double amount, double interest, int length, int frequency){
		
		//freq 0->weekly, 1=biweekly, 2= monthly, 3-Yearly
		double am=0.0;
		double i=0.0;
		if(frequency==0){
			i= interest/5200;
			am = futureValueCalculator(amount, i, 52);
		}
		
		
		double u =Math.pow(1+interest, length);
		double top = am *(u-1);
		double bottom = interest*u;
		
		double a = top/bottom;
		long factor = (long) Math.pow(10, 2);
		a= a*factor;
		long tmp =Math.round(a);
		
		
		double r = tmp/factor;
		
		
		return r;
		
	}
	
	
	
	private double futureValueCalculator(double amount, double interest, int length){
		
		double u = Math.pow(1+interest, length);

		double top = amount*u;

	

		long factor = (long)Math.pow(10, 2);
		top = top * factor;
		long tmp = Math.round(top);
		double fine = (double)tmp/factor;
		Log.v("FUTURE", String.format("Amount: %f , top: %f , result: %f", amount, top, fine));
		return  fine;
		
	}
	
	
	
	
}
