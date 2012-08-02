package com.jeannius.tallycap.Views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import com.jeannius.tallycap.util.MyUneditableDateEditText;

public abstract class MyScrollViewWithDate extends ScrollView implements OnClickListener{
	
	private MyUneditableDateEditText dateText;

	public MyScrollViewWithDate(Context context) {
		super(context);
		dateText = new MyUneditableDateEditText(context);
		dateText.setOnClickListener(this);
	}
	
	public MyUneditableDateEditText getMyUneditableDateEditText(){
		return dateText;
	}
	
	
	public void setMyUneditableDateEditText(MyUneditableDateEditText m){
		dateText = m;		
	}
	
	@Override
	public void onClick(View v) {
		
		
	}
	

}
