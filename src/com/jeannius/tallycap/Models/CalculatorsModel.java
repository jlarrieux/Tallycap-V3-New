package com.jeannius.tallycap.Models;

import java.util.Calendar;

import android.util.Log;

import com.jeannius.tallycap.CalculatorActivity;

public class CalculatorsModel extends CalculatorAbstractModel {
	
	
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
	
	
	
//	public double CreditCardCalculateTheValue(double balance, double apr, double monthFee, double overThelimFee, double creditLim){
//		
//		double r = this.creditCardMinimumPaymentCalculator(balance, apr, monthFee, overThelimFee, creditLim);
//		return r;
//	}
	
	
	
	
	
	
	
	
	
	
	
}
