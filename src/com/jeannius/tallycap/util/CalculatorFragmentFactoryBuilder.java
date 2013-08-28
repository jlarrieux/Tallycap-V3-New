package com.jeannius.tallycap.util;

import com.jeannius.tallycap.Fragments.AffordabilityCalculatorFragment;
import com.jeannius.tallycap.Fragments.AutoCalculatorFragment;
import com.jeannius.tallycap.Fragments.CalculatorFragment;
import com.jeannius.tallycap.Fragments.CreditCardCalculatorFragment;
import com.jeannius.tallycap.Fragments.FutureSavingsCalculatorFragment;
import com.jeannius.tallycap.Fragments.K401kCalculatorFragment;
import com.jeannius.tallycap.Fragments.LoanCalculatorFragment;
import com.jeannius.tallycap.Fragments.MortgageCalculatorFragment;
import com.jeannius.tallycap.Fragments.SavingsPlannerCalculatorFragment;

public class CalculatorFragmentFactoryBuilder {
	
	
	
	
	public static CalculatorFragment Build(int fragmentid){
		CalculatorFragment f= null;
		
		switch(fragmentid){
			
		case 0: f = new AutoCalculatorFragment();
				break;
				
		case 1: f = new MortgageCalculatorFragment();
				break;
		
		case 2: f = new LoanCalculatorFragment();
				break;
				
		case 3: f = new CreditCardCalculatorFragment();
				break;
				
		case 4: f = new K401kCalculatorFragment();
				break;
				
		case 5: f = new AffordabilityCalculatorFragment();
				break;
		
		case 6: f = new FutureSavingsCalculatorFragment();
				break;
		
		case 7: f = new SavingsPlannerCalculatorFragment();
				break;
	
			
		}
		
		
		return f;
		
		
	}
}
