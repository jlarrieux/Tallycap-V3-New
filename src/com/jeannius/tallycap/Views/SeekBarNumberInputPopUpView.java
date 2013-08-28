package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeannius.tallycap.R;
import com.jeannius.tallycap.util.Global;

public class SeekBarNumberInputPopUpView extends RelativeLayout implements OnClickListener{
	
	private Context context;
	public EditText editText;
	private LinearLayout mother;
	private Global g;

	private ImageButton clearOne;
	public int mMaxAbsolute=0;
	public TextView range;
	
	
	public SeekBarNumberInputPopUpView(Context context) {
		super(context);
		this.context = context;
		LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		in.inflate(com.jeannius.tallycap.R.layout.seekbar_number_input_popup, this);
		init();
	}
	
	
	
	
	private void init(){
		
		editText = new EditText(context);
		mother = new LinearLayout(context);
		g = new Global(context);
		clearOne = new ImageButton(context);
		range = new TextView(context);
		
		editText = (EditText) findViewById(R.id.seekBarNumberInputPopUpEditText);
		mother = (LinearLayout) findViewById(R.id.seekBarNumberInputPopUpLinearLayoutNumberContainer);
		clearOne = (ImageButton) findViewById(R.id.seekBarNumberInputPopUpClearOneImageButton);
		range = (TextView) findViewById(R.id.seekbarNumberInputPopUpRangeTextView);
		
		
		
		editText.setGravity(Gravity.RIGHT);
//		g.toaster("Number of child: "+ String.valueOf(mother.getChildCount()), classs, "init", true);
		listen();
	}

	
	
	private void listen(){
		
		
		
		for(int i =0;  i<mother.getChildCount(); i++){
			
			LinearLayout l = (LinearLayout) mother.getChildAt(i);
			for(int j=0; j<l.getChildCount(); j++){
				Button b = (Button) l.getChildAt(j);
				b.setOnClickListener(this);
			}
		}
		clearOne.setOnClickListener(this);
	}
	


	@Override
	public void onClick(View v) {
		
		if(v instanceof Button){
			Button b1 = (Button) v;
	
			numberAdder(b1.getText().toString());
		}
		else if(v instanceof ImageButton) clearOne();
	}
	
	
	/**
	 * 
	 * @param charSequence, the text from the textview of numbers
	 */
	private void numberAdder(String s){
		
		
		double temp =0;
		double cTemp = 0;
		
		//if edittext has text in it, than convert it to ctemp double (after purging)
		if(editText.getText().toString().length()>0){
			//if uf uits a number
			if(Character.isDigit(editText.getText().toString().charAt(0))) cTemp = Double.valueOf(purger());
		}
		
		//if the character selected is a digit, than add it to the value
		if(Character.isDigit(s.charAt(0)))temp = Double.valueOf(purger()+s);
		
		
		if(s.contains(getResources().getString(R.string.clear))) editText.setText("");
		else{		
			if(cTemp <= mMaxAbsolute) {
				if(editText.getText().toString().contains(".") ){
					int i = 0;
					CharSequence c = editText.getText().toString();
					for(i=0; i<c.length(); i++){
						if (String.valueOf(c.charAt(i)).equals(".")) break;
					}
					
					if(c.length()-i<=2 && (!s.equals("."))){
						g.logCat(String.format("Decimal in place i:%d  length: %d number: %s   temp:%s   max: %s", i,c.length(),s, g.formatNumber(temp), g.formatNumber(mMaxAbsolute) ));
						addToEditText(s);
					}
					
				}
				else {
									
					g.logCat(String.format("About to add temp: %s   max absolute: %s   s:%s", g.formatNumber((int) temp), g.formatNumber(mMaxAbsolute), s));
					addToEditText(s);
						
					
				}
			}
		}
	}
	
	private void addToEditText(String k){
		
		String w= "";
		if(k.contains(".")) w=editText.getText()+k;
		else if(!k.contains("CL")) w = g.formatNumber(purger()+k);
		if(Double.valueOf(g.purgerAny(w))<= mMaxAbsolute)		editText.setText(w);
			
				
		
	}

	
	
	private void clearOne(){
		
		if(editText.getText().length()>0) editText.getText().replace(editText.getText().length()-1, editText.getText().length(), "");
	}
	
	
	
	//takes out the formating character like ","
	private String purger(){
		
		String e="";
		CharSequence c = editText.getText().toString();
		for(int i=0; i<c.length(); i++){
			if (!String.valueOf(c.charAt(i)).equals(",")) e+=c.charAt(i);
		}
		
		
		return e;
	}
	

	
	
	

}
